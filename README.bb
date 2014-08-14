[SIZE=5][B]Hangar[/B] - [B][I]store your vessels until they are needed[/I][/B][/SIZE]

[COLOR=#FF0000][SIZE=4][B]WARNING: this is still BETA. There should be bugs.[/B][/SIZE][/COLOR]
While fixing them and implementing new features I'll try as hard as I can to maintain backward compatibility, [I]but I can't guarantee it[/I]. So if you plan to use it in your main game, [SIZE=5][COLOR=#FF0000][B]backup your saves[/B][/COLOR][/SIZE].

[SIZE=3][B]Known Issues[/B][/SIZE]
[LIST]
[*]Rovers:
[LIST]
[*]Rovers moving on hangar's floor are sliding. Like cows on ice. That's because all hangars (and other parts, I presume) have the [B]default[/B] PhysicMaterial with friction coefficient set to 0.4. Don't know why, but changing that coefficient on the colliders in runtime does not change the friction. So I can't fix that. If anyone has an idea on this, PM me, please, on forum.
[*]If a rover is built in VAB with its wheels "down", it will be launched from a hangar rotated by 90 degrees, because in VAB the "up" axis is actually the forward one. Build rovers in SPH to workaround; its more convenient anyway.
[*]Rovers stored in KSC have somewhat smaller dimensions due to inactive suspension of the wheels. So if you pack several rovers [B]tightly[/B] into a hangar, and than launch one of them, the launched rover sometimes cannot be stored again into that same hangar with the "No room ..." message. Again: it's no bug, calculations are performed correctly, the rover's just got bigger.
[/LIST]
[/LIST]
[SIZE=3][B]ChangeLog[/B][/SIZE]
[LIST]
[*]v1.1.0
[LIST]
[*]Added Rover Lander Hangar to easily land rovers on planetary bodies
[*]Added (proper) support for TAC Life Support, RemoteTech2 and DeadlyReentry (a heatshield for Rover Lander is included)
[*]Added recalculation of the amounts of resources on part resize, as well as several other properties of some modules. Unfortunately, I have to replicate some of TweakScale's functionality here; my part resizer is more specialized and TweakScale can't replace it, as much as want it to.
[*][URL="https:/github.com/allista/hangar/issues?q=is%3Aissue+is%3Aclosed"]Fixed issues[/URL]: 1, 5, 6, 7, 8, 9, 11, 13, 14, 16, 17
[*] Hotfixed Spaceport tech tree position (1:34, 7 Aug 14)
[/LIST] 
[*]v1.0.5324 -- Initial release 
[/LIST]
[SIZE=3][B][URL="https://github.com/allista/hangar/milestones"]See what's comming[/URL][/B][/SIZE]

[SIZE=3][B]NOTE:[/B][/SIZE]
[B]Before using a hangar, study the list of modules that are integrated into it [B](RMB on part's icon)[/B].[/B] Many of the hangars have plenty of modules (like batteries, command modules, fuel tanks, etc.) to reduce part count. Don't worry, all is balanced by weight and cost, no cheating. 

[SIZE=3][B]Introduction[/B][/SIZE]

Have you ever wanted to launch 5 satellites in one go? You did so? Then how about 10? Does your maintenance ship orbit Kerbal without work for the third month with that lonely Kerberonaut on board? Do you wish to build a giant carrier filled with scouts, landing modules and so on, but [I]with the part count below a hundred?[/I]Are you tired of the rovers standing here and there now that your colony is fully functional?

Our hangars is the answer to all these questions and to many more! Using a hangar you can store any vessel indefinitely, safe from the harsh conditions of open space or dusty moons. There you can refill it and change its crew. You can also pack some vessels into the hangar right at KSC and launch them to orbit. You can even live in some hangars alongside with your ship or rover! Clean your orbit, colony and CPU from the burden of precious but rarely needed vessels, use AT Industries(TM) Hangars now!

[imgur]Z916l[/imgur]

[SIZE=3][B]Downloads:[/B][/SIZE]
All releases are published on [URL="https://github.com/allista/hangar/releases"]GitHub[/URL].
[URL="https://github.com/allista/hangar/tree/master"]Source code[/URL] may be obtained there as well.

[SIZE=3][B]Features[/B][/SIZE]
[LIST]
[*]Hangars are fit for any application:
[LIST]
[*]small light and cheap as well as huge, packed with all needed modules 
[*][B]most may be rescaled[/B] to the needed size and proportions [B]via tweakables[/B] (mass, volume and cost are changed accordingly) 
[/LIST]
  
[*] There are several types of hangars:
[LIST]
[*][B]In-line hangars[/B] (simple and habitable) for spaceships 
[*][B]Ground hangars[/B] (simple and habitable) for colonies 
[*][B]Rover Lander[/B] hangar that has all needed modules and fuel to autonomously land on a planet or moon, bringing some rovers along the way 
[*]there's also the [B]Spaceport[/B] that combines a huge hangar with a cockpit; as such, the Spaceport has only a single stack node at its bottom 
[/LIST]
  
[*]In-line hangars are equipped with internal docking port for easy targeting. If the hangar is inactive, this port may be used for normal docking 
[*]Ground hangars have anchoring modules for comfort use on low-gravity worlds and integrated probe cores with antennas for autonomous operation 
[*]Crew and resources can be transferred between a vessel with a hangar and stored vessels 
[*]Smart internal machinery ensures optimal filling of a hangar and mass distribution, while preventing attempts to store objects that do not fit in 
[*]A hangar can be filled with vessels at construction time (NOTE: a vessel with a filled hangar will stutter for a second or two upon launch; that's normal) 
[*]An asteroid can also be stored in a hangar. If it fits, of course. Interface:
[LIST]
[*]Hangars are controlled with a dedicated GUI 
[*]For the vessels that do not have any hangars the GUI shows their volume and dimensions 
[*]A vessel can have multiple hangars. Provided GUI allows easy switching between them by highlighting the hangar that is currently selected 
[/LIST]
  
[*] In addition, several other parts are provided:
[LIST]
[*]Powerfull 5-way RCS thrusters for Spaceport 
[*]Heatshild with space for engines for Rover Lander. Especially helpful if you're playing with DeadlyReentry 
[*]Two adapters for Size4 stack nodes 
[/LIST]
    
[/LIST]
[SIZE=3][B]Requirements[/B][/SIZE]
[LIST]
[*]Hangar uses [URL="http://forum.kerbalspaceprogram.com/threads/81496"]KSPAPIExtensions[/URL] by [URL="http://forum.kerbalspaceprogram.com/members/100707-swamp_ig"]swamp_ig[/URL]. This plugin is bundled with the Hangar as it should be. 
[*]The [URL="http://forum.kerbalspaceprogram.com/threads/55219"]ModuleManager[/URL], of course. 
[*]The [URL="http://forum.kerbalspaceprogram.com/threads/60863"]Toolbar[/URL] is required for now. 
[/LIST]
[SIZE=3][B]Recommended mods[/B][/SIZE]
There are many great mods out there that I love and use myself. But the one mode that I strongly recommend to use with the Hangar to improve game experience is the [URL="http://forum.kerbalspaceprogram.com/threads/59545"][B]Extraplanetary Launchpads[/B][/URL] by [URL="https://github.com/taniwha-qf"]Taniwha[/URL]. For one thing: big ground hangars are not suitable as parts for vessel construction and are too heavy to launch anyway. So the only meaningful way to use them is to build them on site.

[B]Supported mods[/B] 

Hangar supports [URL="http://forum.kerbalspaceprogram.com/threads/79745-0-24-2-KSP-AVC-Add-on-Version-Checker-Plugin-1-0-4-KSP-AVC-Online"]KSP Addon Version Checker[/URL]. 

And some functionality is added to hangars if the following mods are installed:
[LIST]
[*][URL="http://forum.kerbalspaceprogram.com/threads/40667?p=1281444&viewfull=1#post1281444"]TAC Life Support [B]beta[/B][/URL] adds life support resources to inhabitable hangars 
[*][URL="http://forum.kerbalspaceprogram.com/threads/83305"]RemoteTech2[/URL] adds RT antennas and SPUs to controllable hangars 
[*][URL="http://forum.kerbalspaceprogram.com/threads/54954"]Deadly Reentry[/URL] adds integrated heatshield to lander hangars 
[/LIST]
[SIZE=3][B]Usage details[/B][/SIZE]

[SIZE=2][B]Hangars in general[/B][/SIZE]
All hangars are parts and thus may be added to the vessel at construction. Hangars have gate(s) which may be open or closed; in addition, internal machinery of a hangar may be deactivated or activated again.All controls and information about a hangar are located in the dedicated GUI window that may be summoned through the context menu of any hangar (menu entry "Show Controls") or through the Toolbar button.

[B]Storing a vessel[/B]

[B]Normal operation[/B]
Inside a hangar near the docking port there is a region of space controlled by the machinery. Every object intersecting with that region is automatically proccessed by the hangar and is stored if:
[LIST]
[*]the hangar is activated and its gates are opened 
[*]a vessel fits into the hangar and the hangar has enough free space inside 
[*]the relative speed of the hangar and the vessel is less than 1 m/s and the vessel is not accelerated 
[/LIST]
Otherwise an on-screen message is displayed explaining the conditions that were not met.

[B]Store a vessel during ship construction[/B]
Select "Edit contents" entry in hangar's context menu to summon vessel selection window. There you select the type of a vessels to choose from (VAB, SPH or Subassemblies) and push the "Select Vessel" button. All stored vessels appear in the same window in a list below. To remove stored vessel from the hangar push the "X" button corresponding the that vessel. To completely clear the hangar push "Clear" button. The vessel should also fit into the hangar which should also have enough free space. If a hangar with some vessels already stored is resized and there's not enough room for all the vessels anymore, some vessels are removed from the hangar to free enough space while maintaining optimal filling.

Mass and cost of stored vessels are added to that of the hangar.

[B]Launching a vessel[/B]

A vessel can be launched from a hangar if:
[LIST]
[*]the hangar is activated and its gates are opened 
[*]the hangar is not accelerated, does not rotate, move over the surface or fly in atmosphere 
[*]nothing is docked to the internal docking port of the hangar 
[/LIST]
Otherwise an on-screen message is displayed explaining the conditions that were not met.

To launch storred vessel first select a hagar (if the vessel has several) and a vessel in that hangar from corresponding dropdown lists in the plugin's window. After that resources and/or crew may be transferred. Then perform the launch by pressing "Launch Vessel" button.

[SIZE=2][B]Ground hangars[/B][/SIZE]
Despite being parts, ground hangars are meant to be used as separate self-sufficient colony buildings. They have an additional context menu entry "Attach anchor". It allows to pin the hangar to the ground, provided it is landed and not moving faster than 1 m/s.

[SIZE=2][B]Spaceport[/B][/SIZE]
The Spaceport is meant to be used as a command module of a big ship. It has 10 crew cabins without IVA and 4 command seats in the C&C located in the observation dome. Several modules specially designed to match the requirements of that huge part are integrated:
[LIST]
[*]radioisotopic generator 
[*]reaction wheel 
[*]central computer with a data transceiver 
[*]monopropellent tank 
[*]electric batteries 
[/LIST]
[SIZE=2][B]Rover Lander[/B][/SIZE]
This hangar is a small lander on its own. It has:
[LIST]
[*]liquid fuel / oxydizer fuel tanks 
[*]monopropellent tanks 
[*]reaction wheel 
[*]integrated probe core 
[*]electric batteries 
[*]4 sides to mount radial engines or RCS thrusters, and 
[*]4 bottom nodes for stack engines 
[*]4 panels acting like hangar doors and landing legs at the same time! No suspension though. 
[/LIST]
Add four radial or stack engines to the mix, RCS thrusters if you need them, solar panels or a generator, a docking port or a decoupler to couple with the rest of a mission ship, a rover inside... and off you go!

[SIZE=3][B]Possible use cases[/B][/SIZE]

[SIZE=2][B]Launch a satellite network[/B][/SIZE]
Launching a network of small satellites was never easier: just pack them into an in-line hangar, build a ship around it and launch. In orbit launch a satellite, change its orbit as desired; repeat. The benefit of this solution is that your satellites may be as small and simple as possible, carrying just enough fuel for orbit correction. No struts, no complex carrier designs. Just one hangar.

[SIZE=2][B]Orbital station[/B][/SIZE]
If you're planning to build a station that acts as a hub for many small operations, a big hangar (or even the Spaceport) is a good choice to include in this plan.

[B]Docks for smaller ships[/B]
With a hangar you can spare the station a dozen of docking ports, not to mention the headache of frequent docking maneuvers. It also enables you to store rarely used ships clearing the orbit.

[B]Fast crew transfer with orbit-to-orbit shuttles[/B]
One station is not enough? Then include a hangar into each of your stations and crew transfers between them become fast and easy. Just use the simplest shuttles with enough fuel to go from one to another and refill them after the trip.

[SIZE=2][B]Exploration ship[/B][/SIZE]
What is better for science: a series of unmanned probes, or a full-scale mission carrying light scouts and landers packed with scientific equipment, that is able to process all the data on site? If you prefer the latter, use a hangar. It will automatically balance the payload and provide the ease of refilling of scientific vessels.

[SIZE=2][B]Bringing a rover to the moon or other planet[/B][/SIZE]
If you want to get your rovers to other planet or moon easily, without complex vessel designs for its transportation, use Rover Lander. Bring it with you, as a part of a ship with a rover in its belly. Undock it, and... land! Just like that. 

[SIZE=2][B]Rover storage for colony[/B][/SIZE]
When establishing a colony rovers are often needed. They help to find a good spot, move colony modules around, tug a not-so-perfectly landed supply ship... But as colony grows and matures they become less and less used. Some of them may be disassembled for spare parts, but some are better to have around that one time when something suddenly goes wrong. To preserve them better while clearing the area use ground hangar, so when the need arise they were filled, fixed and fully operational.

[B]And so on...[/B]

[SIZE=3][B]Acknowledgements[/B][/SIZE]
First of, I want to thank my beloved wife for her support and understanding. This work takes much time... 
I also want to thank [URL="https://github.com/taniwha-qf"]Taniwha[/URL] for inspiration and advice.

And here're the mods which sources provided me with an understanding of how KSP API works. And with working solutions in some cases. In no particular order:
[LIST]
[*][URL="http://forum.kerbalspaceprogram.com/threads/59545"]Extraplanetary Launchpads[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/53134-Kerbal-Attachment-System-%28KAS%29-0-4-7-Pipes-as-fuel-lines-and-even-fewer-explosions!"]Kerbal Attach System[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/39512"]Procedural Fairings[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/12384"]MechJeb2[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/23979"]Kethane[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/50077-0-23-5-Fusebox-electric-charge-tracker-and-build-helper-1-0-released-12-07-14"]Fusebox[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/60936"]CrewManifest[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/80234"]TweakScale[/URL] 
[*][URL="http://forum.kerbalspaceprogram.com/threads/24786"]Kerbal Alarm Clock[/URL]
[*][URL="http://forum.kerbalspaceprogram.com/threads/57988"]RealChutes[/URL]
[*][URL="http://forum.kerbalspaceprogram.com/threads/83305"]RemoteTech2[/URL] 
[/LIST]

[URL="http://creativecommons.org/licenses/by/4.0/"][IMG]http://i.creativecommons.org/l/by/4.0/88x31.png[/IMG][/URL]
This work is licensed under a [URL="http://creativecommons.org/licenses/by/4.0/"]Creative Commons Attribution 4.0 International License[/URL].