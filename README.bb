[SIZE=5][b]Hangar[/b] - [b][i]store your vessels until they are needed[/i][/b][/SIZE]

[COLOR="#FF0000"][SIZE=4][b]WARNING: this is still BETA. There should be bugs.[/b][/SIZE][/COLOR]
While fixing them and implementing new features I'll try as hard as I can to maintain backward compatibility, [i]but I can't guarantee it[/i]. So if you plan to use it in your main game, [COLOR="#FF0000"][b]backup your saves[/b][/COLOR].

[SIZE=3][b]Introduction[/b][/size]

Have you ever wanted to launch 5 satellites in one go? You did so? Then how about 10? Does your maintenance ship orbit Kerbal without work for the third month with that lonely Kerberonaut on board?Do you wish to build a giant carrier filled with scouts, landing modules and so on, but [i]with the part count below a hundred?[/i]Are you tired of the rovers standing here and there now that your colony is fully functional?

Our hangars is the answer to all these questions and to many more! Using a hangar you can store any vessel indefinitely, safe from the harsh conditions of open space or dusty moons. There you can refill it and change its crew. You can also pack some vessels into the hangar right at KSC and launch them to orbit. You can even live in some hangars alongside with your ship or rover! Clean your orbit, colony and CPU from the burden of precious but rarely needed vessels, use AT Industries(TM) Hangars now!

[i]***screenshots are coming soon***[/i]

[SIZE=3][b]Downloads:[/b][/size]
All releases are published on [url=https://github.com/allista/hangar/releases]GitHub[/url].
[url=https://github.com/allista/hangar/tree/master]Source code[/url] may be obtained there as well.

[SIZE=3][b]Features[/b][/size]
[list][*]Hangars are fit for any application: [list]small light and cheap as well as huge, packed with all needed modules[*]most may be rescaled to the needed size and proportions via tweakables (mass, volume and cost are changed accordingly)[/list][*]There are several types of hangars:[list]in-line hangars for spaceships [*]ground hangars for colonies[*]each type has inhabitable variant with integrated crew cabins[*]there's also the Spaceport part that combines a huge hangar with a cockpit; as such, the Spaceport has only a single stack node at its bottom[/list][*]All hangars are equipped with internal docking port for easy targeting. If the hangar is inactive, this port may be used for normal docking[*]Ground hangars have anchoring modules for comfort use on low-gravity worlds and integrated probe cores with antennas for autonomous operation[*]Crew and resources can be transferred between a vessel with a hangar and stored vessels[*]Smart internal machinery ensures optimal filling of a hangar and mass distribution, while preventing attempts to store objects that do not fit in[*]A hangar can be filled with vessels at construction time (NOTE: a vessel with a filled hangar will stutter for a second or two upon launch; that's normal)[*]An asteroid can also be stored in a hangar. If it fits, of course.[*]Interface:[list]Hangars are controlled with a dedicated GUI[*]For the vessels that do not have any hangars the GUI shows their volume and dimensions[*]A vessel can have multiple hangars. Provided GUI allows easy switching between them by highlighting the hangar that is currently selected[/list][/list]
[SIZE=3][b]Requirements[/b][/size]
[list][*]Hangar uses [url=http://forum.kerbalspaceprogram.com/threads/81496]KSPAPIExtensions[/url] by [url=http://forum.kerbalspaceprogram.com/members/100707-swamp_ig]swamp_ig[/url]. This plugin is bundled with the Hangar as it should be.[*]The [url=http://forum.kerbalspaceprogram.com/threads/55219]ModuleManager[/url], of course.[*]The [url=http://forum.kerbalspaceprogram.com/threads/60863]Toolbar[/url] is required for now.[/list]
[SIZE=3][b]Recommended mods[/b][/size]
There are many great mods out there that I love and use myself. But the one mode that I strongly recommend to use with the Hangar to improve game experience is the [url=http://forum.kerbalspaceprogram.com/threads/59545][b]Extraplanetary Launchpads[/b][/url] by [url=https://github.com/taniwha-qf]Taniwha[/url]. For one thing: big ground hangars are not suitable as parts for vessel construction and are too heavy to launch anyway. So the only meaningful way to use them is to build them on site.
Also the [url=http://forum.kerbalspaceprogram.com/threads/79745-0-24-2-KSP-AVC-Add-on-Version-Checker-Plugin-1-0-4-KSP-AVC-Online]KSP Addon Version Checker[/url] is supported, so you'll benefit from having it installed.

[SIZE=3][b]Usage details[/b][/size]

[SIZE=2][b]Hangars in general[/b][/size]
All hangars are parts and thus may be added to the vessel at construction. Hangars have gate(s) which may be open or closed; in addition, internal machinery of a hangar may be deactivated or activated again.All controls and information about a hangar are located in the dedicated GUI window that may be summoned through the context menu of any hangar (menu entry "Show Controls") or through the Toolbar button.

[b]Storing a vessel[/b]

[b]Normal operation[/b]
Inside a hangar near the docking port there is a region of space controlled by the machinery. Every object intersecting with that region is automatically proccessed by the hangar and is stored if:[list][*]the hangar is activated and its gates are opened[*]a vessel fits into the hangar and the hangar has enough free space inside[*]the relative speed of the hangar and the vessel is less than 1 m/s and the vessel is not accelerated[/list]
Otherwise an on-screen message is displayed explaining the conditions that were not met.

[b]Store a vessel during ship construction[/b]
Select "Edit contents" entry in hangar's context menu to summon vessel selection window. There you select the type of a vessels to choose from (VAB, SPH or Subassemblies) and push the "Select Vessel" button. All stored vessels appear in the same window in a list below. To remove stored vessel from the hangar push the "X" button corresponding the that vessel. To completely clear the hangar push "Clear" button. The vessel should also fit into the hangar which should also have enough free space. If a hangar with some vessels already stored is resized and there's not enough room for all the vessels anymore, some vessels are removed from the hangar to free enough space while maintaining optimal filling.

Mass and cost of stored vessels are added to that of the hangar.

[b]Launching a vessel[/b]

A vessel can be launched from a hangar if:[list][*]the hangar is activated and its gates are opened[*]the hangar is not accelerated, does not rotate, move over the surface or fly in atmosphere[*]nothing is docked to the internal docking port of the hangar[/list]
Otherwise an on-screen message is displayed explaining the conditions that were not met.

To launch storred vessel first select a hagar (if the vessel has several) and a vessel in that hangar from corresponding dropdown lists in the plugin's window. After that resources and/or crew may be transferred. Then perform the launch by pressing "Launch Vessel" button.

[SIZE=2][b]Ground hangars[/b][/size]
Despite being parts, ground hangars are meant to be used as separate self-sufficient colony buildings. They have an additional context menu entry "Attach anchor". It allows to pin the hangar to the ground, provided it is landed and not moving faster than 1 m/s.

[SIZE=2][b]Spaceport[/b][/size]
The Spaceport is meant to be used as a command module of a big ship. It has 10 crew cabins without IVA and 4 command seats in the C&C located in the observation dome. Several modules specially designed to match the requirements of that huge part are integrated:[list][*]radioisotopic generator [*]reaction wheel[*]central computer with a data transceiver[*]monopropellent tank[*]electric batteries[/list]
[SIZE=3][b]Possible use cases[/b][/size]

[SIZE=2][b]Launch a satellite network[/b][/size]
Launching a network of small satellites was never easier: just pack them into an in-line hangar, build a ship around it and launch. In orbit launch a satellite, change its orbit as desired; repeat. The benefit of this solution is that your satellites may be as small and simple as possible, carrying just enough fuel for orbit correction. No struts, no complex carrier designs. Just one hangar.

[SIZE=2][b]Orbital station[/b][/size]
If you're planning to build a station that acts as a hub for many small operations, a big hangar (or even the Spaceport) is a good choice to include in this plan.

[b]Docks for smaller ships[/b]
With a hangar you can spare the station a dozen of docking ports, not to mention the headache of frequent docking maneuvers. It also enables you to store rarely used ships clearing the orbit.

[b]Fast crew transfer with orbit-to-orbit shuttles[/b]
One station is not enough? Then include a hangar into each of your stations and crew transfers between them become fast and easy. Just use the simplest shuttles with enough fuel to go from one to another and refill them after the trip.

[SIZE=2][b]Exploration ship[/b][/size]
What is better for science: a series of unmanned probes, or a full-scale mission carrying light scouts and landers packed with scientific equipment, that is able to process all the data on site? If you prefer the latter, use a hangar. It will automatically balance the payload and provide the ease of refilling of scientific vessels.

[SIZE=2][b]Rover storage for colony[/b][/size]
When establishing a colony rovers are often needed. They help to find a good spot, move colony modules around, tug a not-so-perfectly landed supply ship... But as colony grows and matures they become less and less used. Some of them may be disassembled for spare parts, but some are better to have around that one time when something suddenly goes wrong. To preserve them better while clearing the area use ground hangar, so when the need arise they were filled, fixed and fully operational.

[b]And so on...[/b]

[SIZE=3][b]Acknowledgements[/b][/size]
First of, I want to thank [url=https://github.com/taniwha-qf]Taniwha[/url] for inspiration and advice.

And here're the mods which sources provided me with an understanding of how KSP API works. And with working solutions in some cases. In no particular order:[list][*][url=http://forum.kerbalspaceprogram.com/threads/59545]Extraplanetary Launchpads[/url][*][url=http://forum.kerbalspaceprogram.com/threads/53134-Kerbal-Attachment-System-%28KAS%29-0-4-7-Pipes-as-fuel-lines-and-even-fewer-explosions!]Kerbal Attach System[/url][*][url=http://forum.kerbalspaceprogram.com/threads/39512]Procedural Fairings[/url][*][url=http://forum.kerbalspaceprogram.com/threads/12384]MechJeb2[/url][*][url=http://forum.kerbalspaceprogram.com/threads/23979]Kethane[/url][*][url=http://forum.kerbalspaceprogram.com/threads/50077-0-23-5-Fusebox-electric-charge-tracker-and-build-helper-1-0-released-12-07-14]Fusebox[/url][*][url=http://forum.kerbalspaceprogram.com/threads/60936]CrewManifest[/url][*][url=http://forum.kerbalspaceprogram.com/threads/80234]TweakScale[/url][/list]

[url=http://creativecommons.org/licenses/by/4.0/][img]http://i.creativecommons.org/l/by/4.0/88x31.png[/img][/url]
This work is licensed under a [url=http://creativecommons.org/licenses/by/4.0/]Creative Commons Attribution 4.0 International License[/url].
