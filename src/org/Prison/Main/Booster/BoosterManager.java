package org.Prison.Main.Booster;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Files;
import org.bukkit.entity.Player;

public class BoosterManager {
	
	public static void attemptBoosterCommand(Player p, String args[], String Label){
		if (Label.equalsIgnoreCase("Booster")){
		if (p.isOp()){
		if (args.length < 2 || args.length > 3){
			p.sendMessage(ChatColor.RED + "Invalid args: " + ChatColor.AQUA + "/booster (Booster) (Minutes)");
		}else{
			double booster = 0;
			int minutes = 0;
			
			try{
				booster = Double.parseDouble(args[0]);
			}catch (Exception e){
				p.sendMessage(ChatColor.RED + "Invalid args: " + ChatColor.AQUA + "/booster (Booster) (Minutes)");
				return;
			}
			
			try{
				minutes = Integer.parseInt(args[1]);
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + "Invalid args: " + ChatColor.AQUA + "/booster (Booster) (Minutes)");
				return;
			}
			
			if (booster <= 1 || minutes <= 0){
				p.sendMessage(ChatColor.RED + "Booster/Minutes is lower then possible, you nub.");
				return;
			}
		
			if (BoosterAPI.isActivated()){
				p.sendMessage(ChatColor.RED + "Booster already active, use /removebooster to remove, you nub.");
				return;
			}
			
			activateBooster(booster, minutes);
		}
	}else{
		p.sendMessage(ChatColor.RED + "This command is for admins only.");
	}
	}
		
		if (Label.equalsIgnoreCase("RemoveBooster")){
			if (p.isOp()){
				if (BoosterAPI.isActivated()){
				p.sendMessage(ChatColor.GREEN + "Booster removed.");
				Files.getDataFile().set("BoosterEnabled", false);
				Files.saveDataFile();
				}else{
					p.sendMessage(ChatColor.RED + "No booster active.");
				}
			}else{
				p.sendMessage(ChatColor.RED + "This command is for admins only.");
			}
		}
		
		if (Label.equalsIgnoreCase("BoosterTime")){
			if (BoosterAPI.isActivated()){
				p.sendMessage(ChatColor.GREEN + "The current booster will last for " + BoosterCooldown.getTimeLeft("Booster"));
			}else{
				p.sendMessage(ChatColor.RED + "There is no booster activated.");
			}
		}
	}

	public static void activateBooster(double booster, int minutes){
		Files.getDataFile().set("BoosterEnabled", true);
		Files.getDataFile().set("BoosterMultiply", booster);
		BoosterCooldown.setCooldown(minutes, "Booster");
		Files.saveDataFile();
	}
	
	public static void checkBooster(){
		BoosterCooldown.checkCooldown("Booster");
		if (!BoosterCooldown.hasCooldown("Booster")){
			Files.getDataFile().set("BoosterEnabled", false);
			Files.saveDataFile();
		}
	}
}
