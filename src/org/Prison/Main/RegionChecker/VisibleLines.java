package org.Prison.Main.RegionChecker;

import java.util.ArrayList;
import java.util.List;

import org.Prison.Main.Main;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class VisibleLines {
	
	public static List<String> in = new ArrayList<String>();

	public static void checkForPlayer(final Player p, PlayerMoveEvent event){
		if (in.contains(p.getName())){
			if (!CellBlockLines.ifPlayerIsIn(p.getLocation(), "Visible") && !Main.Tutorialint.containsKey(p.getName())){
				in.remove(p.getName());
				if (!Main.Vanish.contains(p.getName())){
					for (Player p1 : Bukkit.getOnlinePlayers()){
						if (OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
							p1.showPlayer(p);
						}
					}
				}
			}
		}else{
		if (CellBlockLines.ifPlayerIsIn(p.getLocation(), "Visible") && !Main.Vanish.contains(p.getName())){
			in.add(p.getName());
			for (Player p1 : Bukkit.getOnlinePlayers()){
				p1.hidePlayer(p);
			}
		}
		}
	}		
}
