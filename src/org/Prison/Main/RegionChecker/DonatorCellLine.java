package org.Prison.Main.RegionChecker;

import java.util.ArrayList;
import java.util.List;

import org.Prison.Main.Files;
import org.Prison.Main.Ranks.RankType;
import org.PrisonMain.Achievement.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class DonatorCellLine {
	
	public static List<String> Messages = new ArrayList<String>();

	public static void checkForPlayer(final Player p, PlayerMoveEvent event){
		if (ifPlayerIsIn(event.getTo(), "DonatorLine")){
			if (RankType.getRank(p).equals(RankType.NONE)){
				p.eject();
				event.setTo(event.getFrom());
				if (!Messages.contains(p.getName())){
				p.sendMessage("§8§l[§4§lGUARD§8§l]: §c§lThis area is for donators only");
				Messages.add(p.getName());
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
						Messages.remove(p.getName());
					}
				}, 20l);
			}
		}
	}
	}
	
	public static boolean ifPlayerIsIn(Location loc, String region){
		int x1 = Math.min(Files.config().getInt(region + ".x1"), Files.config().getInt(region + ".x2"));
		int z1 = Math.min(Files.config().getInt(region + ".z1"), Files.config().getInt(region + ".z2"));
		int y1 = Math.min(Files.config().getInt(region + ".y1"), Files.config().getInt(region + ".y2"));
		int y2 = Math.max(Files.config().getInt(region + ".y1"), Files.config().getInt(region + ".y2"));
		int x2 = Math.max(Files.config().getInt(region + ".x1"), Files.config().getInt(region + ".x2"));
		int z2 = Math.max(Files.config().getInt(region + ".z1"), Files.config().getInt(region + ".z2"));
		if (x1 <= loc.getBlockX() && x2 >= loc.getBlockX() && y1 <= loc.getBlockY() && y2 >= loc.getBlockY() &&  z1 <= loc.getBlockZ() && z2 >= loc.getBlockZ()){
			return true;
		}else{
			return false;
		}
	}
}
