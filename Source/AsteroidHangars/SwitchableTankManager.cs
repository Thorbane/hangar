﻿using System;
using System.Linq;
using System.Collections.Generic;
using UnityEngine;

namespace AtHangar
{
	public class SwitchableTankManager : ConfigNodeObject
	{
		new public const string NODE_NAME = "TANKMANAGER";
		public const string TANK_NODE = "TANK";
		public const string MANAGED = "MANAGED";

		readonly Part part;
		readonly List<HangarSwitchableTank> tanks = new List<HangarSwitchableTank>();
		public int TanksCount { get { return tanks.Count; } }
		public float TotalVolume { get { return tanks.Aggregate(0f, (v, t) => v+t.Volume); } }
		public IEnumerable<float> TanksVolumes { get { return tanks.Select(t => t.Volume); } }

		public SwitchableTankManager(Part part) 
		{ 
			this.part = part; 
			tank_types_list.Items = SwitchableTankType.TankTypeNames.ToList();
		}

		public override void Save(ConfigNode node)
		{
			base.Save(node);
			if(tanks.Count == 0) return;
			tanks.ForEach(t => t.Save(node.AddNode(TANK_NODE)));
		}

		public override void Load(ConfigNode node)
		{
			base.Load(node);
			tanks.Clear();
			foreach(var n in node.GetNodes(TANK_NODE))
			{
				n.AddValue("name", typeof(HangarSwitchableTank).Name);
				n.AddValue(MANAGED, true);
				var tank = part.AddModule(n) as HangarSwitchableTank;
				if(tank != null) tanks.Add(tank);
				else Utils.Log("SwitchableTankManager: unable to load module from config:\n{0}", n.ToString());
			}
			//no need to OnStart tanks here, as Part.ModulesOnStart will do it for us
		}

		/// <summary>
		/// Adds a tank of the provided type and value to the part, if possible.
		/// </summary>
		/// <returns><c>true</c>, if tank was added, <c>false</c> otherwise.</returns>
		/// <param name="tank_type">Tank type.</param>
		/// <param name="volume">Tank volume.</param>
		public bool AddTank(string tank_type, float volume)
		{
			if(!SwitchableTankType.TankTypes.ContainsKey(tank_type))
			{
				Utils.Log("SwitchableTankManager: no such tank type: {0}", tank_type);
				return false;
			}
			var tank = part.AddModule(typeof(HangarSwitchableTank).Name) as HangarSwitchableTank;
			if(tank == null) return false;
			tank.Volume = volume;
			tank.TankType = tank_type;
			tank.OnStart(part.StartState());
			tanks.ForEach(t => t.RegisterOtherTank(tank));
			tanks.Add(tank);
			return true;
		}

		/// <summary>
		/// Removes the tank from the part, if possible. Removed tank is destroyed immidiately, 
		/// so the provided reference becomes invalid.
		/// </summary>
		/// <returns><c>true</c>, if tank was removed, <c>false</c> otherwise.</returns>
		/// <param name="tank">Tank to be removed.</param>
		public bool RemoveTank(HangarSwitchableTank tank)
		{
			if(!tanks.Contains(tank)) return false;
			if(!tank.TryRemoveResource()) return false;
			tanks.Remove(tank);
			tanks.ForEach(t => t.UnregisterOtherTank(tank));
			part.RemoveModule(tank);
			return true;
		}

		/// <summary>
		/// Multiplies the Volume property of each tank by specified value.
		/// Amounts of resources are not rescaled.
		/// </summary>
		/// <param name="relative_scale">Relative scale. Should be in [0, +inf] interval.</param>
		public void RescaleTanks(float relative_scale)
		{
			if(relative_scale <= 0) return;
			foreach(var t in tanks)	
				t.Volume *= relative_scale;
		}

		#region GUI
		const int scroll_width  = 550;
		const int scroll_height = 200;
		const string eLock      = "SwitchableTankManager.EditingTanks";
		Vector2 tanks_scroll    = Vector2.zero;
		Rect eWindowPos = new Rect(Screen.width/2-scroll_width/2, scroll_height, scroll_width, scroll_height);
		DropDownList tank_types_list = new DropDownList();
		Func<string, float, float> add_tank_delegate;
		Action<HangarSwitchableTank> remove_tank_delegate;
		string volume_field = "0.0";

		public bool Closed { get; private set; }

		void close_button()
		{ 	
			Closed = GUILayout.Button("Close", Styles.normal_button, GUILayout.ExpandWidth(true));
			if(Closed) Utils.LockIfMouseOver(eLock, eWindowPos, false);
		}

		static void tank_type_gui(HangarSwitchableTank tank)
		{
			var choice = HangarGUI.LeftRightChooser(tank.TankType, 120);
			SwitchableTankType new_type = null;
			if(choice < 0) new_type = SwitchableTankType.TankTypes.Prev(tank.TankType);
			else if(choice > 0) new_type = SwitchableTankType.TankTypes.Next(tank.TankType);
			if(new_type != null) tank.TankType = new_type.name;
		}

		static void tank_resource_gui(HangarSwitchableTank tank)
		{
			var choice = HangarGUI.LeftRightChooser(tank.CurrentResource, 120);
			TankResource new_res = null;
			if(choice < 0) new_res = tank.Type.Resources.Prev(tank.CurrentResource);
			else if(choice > 0) new_res = tank.Type.Resources.Next(tank.CurrentResource);
			if(new_res != null) tank.CurrentResource = new_res.Name;
		}

		void tank_gui(HangarSwitchableTank tank)
		{
			GUILayout.BeginHorizontal();
			tank_type_gui(tank);
			tank_resource_gui(tank);
			GUILayout.FlexibleSpace();
			GUILayout.Label(Utils.formatVolume(tank.Volume), GUILayout.ExpandWidth(true));
			var usage = tank.Usage;
			GUILayout.Label("Filled: "+usage.ToString("P1"), Styles.fracStyle(usage), GUILayout.Width(95));
			if(tank.Usage > 0) GUILayout.Label("X", Styles.grey, GUILayout.Width(20));
			else if(GUILayout.Button("X", Styles.red_button, GUILayout.Width(20)))
				remove_tank_delegate(tank);
			GUILayout.EndHorizontal();
		}

		void AddTankGUI_start(Rect windowPos)
		{
			tank_types_list.styleListBox  = Styles.list_box;
			tank_types_list.styleListItem = Styles.list_item;
			tank_types_list.windowRect    = windowPos;
			tank_types_list.DrawBlockingSelector();
		}

		void AddTankGUI()
		{
			GUILayout.BeginVertical();
			//tank properties
			GUILayout.BeginHorizontal();
			GUILayout.Label("Tank Type:", GUILayout.Width(70));
			tank_types_list.DrawButton();
			var tank_type = tank_types_list.SelectedValue;
			GUILayout.Label("Volume:", GUILayout.Width(50));
			volume_field = GUILayout.TextField(volume_field, GUILayout.ExpandWidth(true), GUILayout.MinWidth(50));
			GUILayout.Label("m3", GUILayout.Width(20));
			float volume = -1;
			var volume_valid = float.TryParse(volume_field, out volume); 
			volume = add_tank_delegate(tank_type, volume);
			GUILayout.EndHorizontal();
			//warning label
			GUILayout.BeginHorizontal();
			if(volume_valid) volume_field = volume.ToString("n1");
			else GUILayout.Label("Volume should be a number.", Styles.red);
			GUILayout.EndHorizontal();
			GUILayout.EndVertical();
		}

		void AddTankGUI_end()
		{
			//finish the dropdown list
			tank_types_list.DrawDropDown();
			tank_types_list.CloseOnOutsideClick();
		}

		/// <summary>
		/// Draws TanksGUI. This GUI includes tank list and AddTankGUI block.
		/// </summary>
		public void TanksGUI(int windowId)
		{
			AddTankGUI_start(eWindowPos);
			GUILayout.BeginVertical();
			AddTankGUI();
			tanks_scroll = GUILayout.BeginScrollView(tanks_scroll, 
				GUILayout.Width(scroll_width), 
				GUILayout.Height(scroll_height));
			GUILayout.BeginVertical();
			tanks.ToArray().ForEach(tank_gui);
			GUILayout.EndVertical();
			GUILayout.EndScrollView();
			close_button();
			GUILayout.EndVertical();
			AddTankGUI_end();
			GUI.DragWindow(new Rect(0, 0, Screen.width, 20));
		}

		/// <summary>
		/// Draws the add tank GUI in a separate window.
		/// </summary>
		/// <returns>New window position.</returns>
		/// <param name="windowId">Window ID.</param>
		/// <param name="add_tank">This function should take selected tank type and value, 
		/// check them and if appropriate add new tank by calling AddTank method.</param>
		/// <param name="remove_tank">This function should take selected tank 
		/// and if possible remove it using RemoveTank method.</param>
		public void DrawTanksWindow(int windowId, string title, Func<string, float, float> add_tank, Action<HangarSwitchableTank> remove_tank)
		{
			add_tank_delegate = add_tank;
			remove_tank_delegate = remove_tank;
			Utils.LockIfMouseOver(eLock, eWindowPos, !Closed);
			eWindowPos = GUILayout.Window(windowId, 
				eWindowPos, TanksGUI, title,
				GUILayout.Width(scroll_width),
				GUILayout.Height(scroll_height));
			HangarGUI.CheckRect(ref eWindowPos);
		}

		public void UnlockEditor()
		{ Utils.LockIfMouseOver(eLock, eWindowPos, false); }
		#endregion
	}
}

