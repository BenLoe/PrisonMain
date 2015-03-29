package org.Prison.Main.RegionChecker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.Prison.Main.Files;
import org.Prison.Main.Letter.LetterType;
import org.PrisonMain.Achievement.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class CellBlockLines {
	
	public static HashMap<String,Location> place1 = new HashMap<String,Location>();
	public static HashMap<String,Location> place2 = new HashMap<String,Location>();
	public static List<String> Messages = new ArrayList<String>();
	
	public static void setRegion(String name, Player p){
		if (place1.containsKey(p.getName()) && place2.containsKey(p.getName())){
			Location loc1 = place1.get(p.getName());
			Location loc2 = place2.get(p.getName());
			Files.config().set(name + ".x1", loc1.getX());
			Files.config().set(name + ".y1", loc1.getY());
			Files.config().set(name + ".z1", loc1.getZ());
			Files.config().set(name + ".x2", loc2.getX());
			Files.config().set(name + ".y2", loc2.getY());
			Files.config().set(name + ".z2", loc2.getZ());
			Files.saveConfig();
		}else{
			p.sendMessage(ChatColor.RED + "Nope");
		}
	}
	public static void checkForPlayer(final Player p, PlayerMoveEvent event){
		for (LetterType l : LetterType.values()){
			String name = l.getName();
			if (Files.config().contains(name + "Line.x1")){
				if (ifPlayerIsIn(event.getTo(), name + "Line")){
					if (LetterType.getPlayerLetter(p).getInt() < l.getInt()){
						p.eject();
						event.setTo(event.getFrom());
						if (!Messages.contains(p.getName())){
						p.sendMessage("§8§l[§4§lGUARD§8§l]: §c§lWhat are you doing! You can't go there yet, you're not in the right cell block.");
						Messages.add(p.getName());
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
							public void run(){
								Messages.remove(p.getName());
							}
						}, 20l);
						}
						break;
					}else{
						break;
					}
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
