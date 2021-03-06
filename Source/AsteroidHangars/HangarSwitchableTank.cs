﻿using System;
using System.Linq;
using System.Collections.Generic;
using KSPAPIExtensions;
using KSPAPIExtensions.PartMessage;
using UnityEngine;

namespace AtHangar
{
	/// <summary>
	/// This is a different approach than in ModularFuelTanks, more suitable for "cargo" resources than fuels:
	/// Such tank may contain only one type of resources, but this type may be switched in-flight, 
	/// if the part has zero amount of the current resource.
	/// </summary>
	public class HangarSwitchableTank : PartModule, IPartCostModifier
	{
		const string   MANAGED = "RES";
		const string UNMANAGED = "N/A";

		/// <summary>
		/// If a tank type can be selected in editor.
		/// </summary>
		[KSPField] public bool ChooseTankType;

		/// <summary>
		/// The type of the tank. Types are defined in separate config nodes. Cannot be changed in flight.
		/// </summary>
		[KSPField(isPersistant = true, guiActiveEditor = true, guiName = "Tank Type")]
		[UI_ChooseOption(scene = UI_Scene.Editor)]
		public string TankType;
		SwitchableTankType tank_type;
		public SwitchableTankType Type { get { return tank_type; } }

		/// <summary>
		/// The volume of a tank in m^3. It is defined in a config or calculated from the part volume in editor.
		/// Cannot be changed in flight.
		/// </summary>
		[KSPField(isPersistant = true)] public float Volume = -1f;

		/// <summary>
		/// The initial partial amount of the CurrentResource.
		/// Should be in the [0, 1] interval.
		/// </summary>
		[KSPField] public float InitialAmount;

		/// <summary>
		/// The name of a currently selected resource. Can be changed in flight if resource amount is zero.
		/// </summary>
		[KSPField(isPersistant = true, guiActive = true, guiActiveEditor = true, guiName = MANAGED)]
		[UI_ChooseOption]
		public string CurrentResource = string.Empty;
		PartResource current_resource;
		string previous_resource = string.Empty;
		public float Usage { get { return current_resource != null? (float)(current_resource.amount/current_resource.maxAmount) : 0; } }
		public string ResourceInUse { get { return current_resource != null? CurrentResource : string.Empty; } }

		readonly List<HangarSwitchableTank> other_tanks = new List<HangarSwitchableTank>();
		UIPartActionWindow part_menu;

		public ConfigNode ModuleSave;

		public override string GetInfo()
		{
			var info = "";
			if(ChooseTankType)
			{
				info += "Available Tank Types:\n";
				info += SwitchableTankType.TankTypeNames.Aggregate("", (i, t) => i+"- "+t+"\n");
			}
			if(!init_tank_type()) return info;
			info += tank_type.Info;
			init_tank_volume();
			info += "Tank Volume: " + Utils.formatVolume(Volume);
			return info;
		}

		public float GetModuleCost(float default_cost) 
		{ 
			return current_resource == null? 0f 
					: (float)current_resource.maxAmount*current_resource.info.unitCost;
		}

		public override void OnAwake()
		{
			base.OnAwake();
			PartMessageService.Register(this);
		}

		public override void OnStart(StartState state)
		{
			base.OnStart(state);
			//get other tanks in this part
			other_tanks.AddRange(from t in part.Modules.OfType<HangarSwitchableTank>()
								 where t != this select t);
			//get part menu
			part_menu = part.FindActionWindow();
			//initialize tank type chooser
			HangarGUI.EnableField(Fields["TankType"], false);
			if( ChooseTankType &&
				state == StartState.Editor &&
				SwitchableTankType.TankTypes.Count > 1)
			{
				var names = SwitchableTankType.TankTypeNames;
				var tank_types = names.ToArray();
				var tank_names = names.Select(HangarGUI.ParseCamelCase).ToArray();
				HangarGUI.SetupChooser(tank_names, tank_types, Fields["TankType"]);
				HangarGUI.EnableField(Fields["TankType"]);
			}
			init_tank_type();
			init_tank_volume();
			switch_resource();
			StartCoroutine(slow_update());
		}

		public override void OnLoad(ConfigNode node)
		{
			//if the tank is managed, save its config
			if(node.HasValue(SwitchableTankManager.MANAGED)) ModuleSave = node;
			//if the nod is not from a TankManager, but we have a saved config, reload it
			else if(ModuleSave != null && 
			        ModuleSave.HasValue(SwitchableTankManager.MANAGED))	Load(ModuleSave);
			//deprecated config conversion
			if(node.HasNode(SwitchableTankType.NODE_NAME))
			{
				var tn = node.GetNode(SwitchableTankType.NODE_NAME);
				if(tn.HasValue("name")) TankType = tn.GetValue("name");
			}
		}

		#region KAE Message Bus
		[PartMessageDelegate]
		public delegate void TankResourceChanged(string resource);

		[PartMessageEvent]
		public event TankResourceChanged ResourceChanged;

		[PartMessageListener(typeof(TankResourceChanged))]
		void other_tank_changed_resource(string resource)
		{
			if(current_resource != null) return;
			switch_resource(false);
		}
		#endregion

		/// <summary>
		/// Adds the given SwitchableTank to the list of all tanks 
		/// whose CurrentResource is checked upon resource switching.
		/// </summary>
		public void RegisterOtherTank(HangarSwitchableTank tank)
		{ if(!other_tanks.Contains(tank)) other_tanks.Add(tank); }

		/// <summary>
		/// Remoes the given SwitchableTank from the list of all tanks 
		/// whose CurrentResource is checked upon resource switching.
		/// </summary>
		public bool UnregisterOtherTank(HangarSwitchableTank tank)
		{ return other_tanks.Remove(tank); }

		/// <summary>
		/// If some resource is currently managed by the tank, checks 
		/// if its amount is zero and, if so, removes the resource from the part.
		/// </summary>
		/// <returns><c>true</c>, if resource was removed or was not present, 
		/// <c>false</c> otherwise.</returns>
		public bool TryRemoveResource()
		{
			if(current_resource == null) return true;
		   	if(current_resource.amount > 0)
			{ 
				ScreenMessager.showMessage("Tank is in use");
				CurrentResource = current_resource.resourceName;
				if(tank_type != null) TankType = tank_type.name;
				return false;
			}
			part.Resources.list.Remove(current_resource); 
			Destroy(current_resource);
			current_resource = null;
			return true;
		}

		void update_part_menu()
		{ 
			part_menu = part.FindActionWindow();
			if(part_menu != null)
				part_menu.displayDirty = true;
		}

		void init_tank_volume()
		{
			if(Volume > 0) return;
			if(tank_type != null) Volume = Metric.Volume(part)*tank_type.UsefulVolumeRatio;
			else this.Log("WARNING: init_tank_volume is called before init_tank_type");
		}

		bool init_tank_type()
		{
			//check if the tank is in use
			if( tank_type != null && 
				current_resource != null &&
				current_resource.amount > 0)
				{ 
					ScreenMessager.showMessage("Cannot change tank type while tank is in use");
					TankType = tank_type.name;
					return false;
				}
			//setup new tank type
			tank_type = null;
			//if tank type is not provided, use the first one from the library
			if(string.IsNullOrEmpty(TankType))
			{ TankType = SwitchableTankType.TankTypeNames[0]; }
			//select tank type from the library
			if(!SwitchableTankType.TankTypes.TryGetValue(TankType, out tank_type))
				ScreenMessager.showMessage(6, "Hangar: No \"{0}\" tank type in the library. Configuration of \"{1}\" is INVALID.", 
					TankType, this.Title());
			//switch off the UI
			HangarGUI.EnableField(Fields["CurrentResource"], false);
			if(tank_type == null) return false;
			//initialize new tank UI if needed
			if(tank_type.Resources.Count > 1)
			{
				var res_values = tank_type.ResourceNames.ToArray();
				var res_names  = tank_type.ResourceNames.Select(HangarGUI.ParseCamelCase).ToArray();
				HangarGUI.SetupChooser(res_names, res_values, Fields["CurrentResource"]);
				HangarGUI.EnableField(Fields["CurrentResource"]);
			}
			//initialize current resource
			if(CurrentResource == string.Empty || 
				!tank_type.Resources.ContainsKey(CurrentResource)) 
				CurrentResource = tank_type.DefaultResource.Name;
			return true;
		}

		/// <summary>
		/// Check if resource 'res' is managed by any other tank.
		/// </summary>
		/// <returns><c>true</c>, if resource is used, <c>false</c> otherwise.</returns>
		/// <param name="res">resource name</param>
		bool resource_in_use(string res)
		{
			bool in_use = false;
			foreach(var t in other_tanks)
			{
				in_use |= t.ResourceInUse == res;
				if(in_use) break;
			}
			return in_use;
		}

		bool switch_resource(bool update_menu = true)
		{
			if(tank_type == null) return false;
			//remove the old resource, if any
			if(!TryRemoveResource()) return false;
			//check if this is tank initialization or real switching
			var initializing = previous_resource == string.Empty;
			//now the state is already changed
			previous_resource = CurrentResource;
			//check if the resource is in use by another tank
			if(resource_in_use(CurrentResource)) 
			{
				ScreenMessager.showMessage(6, "A part cannot have more than one resource of any type");
				Fields["CurrentResource"].guiName = UNMANAGED;
				update_part_menu();
				return false;
			}
			Fields["CurrentResource"].guiName = MANAGED;
			//get definition of the next not-managed resource
			var res = tank_type[CurrentResource];
			//calculate maxAmount
			var maxAmount = Volume*res.UnitsPerLiter*1000f;
			//if there is such resource already, just plug it in
			var part_res = part.Resources[res.Name];
			if(part_res != null) 
			{ 
				current_resource = part_res;
				current_resource.maxAmount = maxAmount;
				if(current_resource.amount > current_resource.maxAmount)
					current_resource.amount = current_resource.maxAmount;
			}
			else //create the new resource
			{
				var node = new ConfigNode("RESOURCE");
				node.AddValue("name", res.Name);
				node.AddValue("amount", initializing? maxAmount*InitialAmount : 0);
				node.AddValue("maxAmount", maxAmount);
				current_resource = part.Resources.Add(node);
			}
			ResourceChanged(CurrentResource);
			if(update_menu) update_part_menu();
			return true;
		}

		IEnumerator<YieldInstruction> slow_update()
		{
			while(true)
			{
				if(HighLogic.LoadedSceneIsEditor)
				{
					if(tank_type == null || tank_type.name != TankType)
						init_tank_type();
				}
				else if(tank_type != null && tank_type.name != TankType)
				{
					ScreenMessager.showMessage("Cannot change the type of the already constructed tank");
					TankType = tank_type.name;
				}
				if(CurrentResource != previous_resource)
					switch_resource();
				yield return new WaitForSeconds(0.1f);
			}
		}
	}
}

