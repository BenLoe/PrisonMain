package org.Prison.Main.Currency;

import org.Prison.Main.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrystalCommands {

	@SuppressWarnings("unused")
	public static void execute(CommandSender sender, String[] args){
			CommandSender p = sender;
		if (p.hasPermission("Crystals.Staff") || !(p instanceof Player)){
			if (args.length == 0){
				p.sendMessage(ChatColor.AQUA + "---------" + ChatColor.GREEN + "Crystal Help" + ChatColor.AQUA + "---------");
				p.sendMessage(ChatColor.GREEN + "/Crystal" + ChatColor.YELLOW + " - Shows this text");
				p.sendMessage(ChatColor.GREEN + "/Crystal Crystals {name}" + ChatColor.YELLOW + " - Shows personal or other players Crystal");
				p.sendMessage(ChatColor.GREEN + "/Crystal take {name} {Crystal}" + ChatColor.YELLOW + " - Takes Crystal from a player");
				p.sendMessage(ChatColor.GREEN + "/Crystal give {name} {Crystal}" + ChatColor.YELLOW + " - Gives Crystal to a player");
				p.sendMessage(ChatColor.GREEN + "/Crystal set {name} {Crystal}" + ChatColor.YELLOW + " - Sets a players Crystal");
			}
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("Crystals")){
					p.sendMessage(ChatColor.GREEN + "You have " + ChatColor.AQUA + CrystalAPI.getCrystals(p.getName()) + ChatColor.GREEN + " Crystals.");
					return;
				}
				if (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("set")){
					p.sendMessage(ChatColor.RED + "Please indicate player and coins!");
					return ;
				}
				p.sendMessage(ChatColor.RED + "Unknown command.");
				}
			if (args.length == 2){
				if (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("set")){
					p.sendMessage(ChatColor.RED + "Please indicate player and coins!");
					return;
				}
				if (args[0].equalsIgnoreCase("Crystals")){
					if (ifPlayerExists(args[1])){
						p.sendMessage(ChatColor.AQUA + args[1] + ChatColor.GREEN + " has " + ChatColor.AQUA + CrystalAPI.getCrystals(args[1]) + ChatColor.GREEN + " Crystals");
					}else{
						p.sendMessage(ChatColor.RED + "Player doesn't exist, remember it's case sensitive.");
					}
					return;
				}
				p.sendMessage(ChatColor.RED + "Unknown command.");
				   return ;
			}
			if (args.length == 3){
				if (args[0].equalsIgnoreCase("give")){
					if (!ifPlayerExists(args[1])){
						p.sendMessage(ChatColor.RED + "Player doesn't exist, remember it's case sensitive.");
						return;
					}
					@SuppressWarnings("unused")
					int newInt = 0;
					try{
						newInt = Integer.parseInt(args[2]);
					}catch(Exception e){
						p.sendMessage(ChatColor.RED + "Please indicate a number of crystals.");
						return ;
					}
					if (Integer.valueOf(args[2]) < 0){
						p.sendMessage(ChatColor.RED + "Cannot give negative Crystals! Use " + ChatColor.AQUA + "/Crystals take");
						return ;
					}
					int Coinsorig = CrystalAPI.getCrystals(args[1]);
					int Coinsgiven = Integer.valueOf(args[2]);
					int Coinsset = Coinsgiven + Coinsorig;
					p.sendMessage(ChatColor.GREEN + "You gave " + ChatColor.AQUA + args[1] +  " " + Coinsgiven + ChatColor.GREEN + " crystals, they now have " + ChatColor.AQUA + Coinsset);
					CrystalAPI.setCrystals(args[1], Coinsset);
					return ;
				}
				if (args[0].equalsIgnoreCase("take")){
						if (!ifPlayerExists(args[1])){
							p.sendMessage(ChatColor.RED + "Player doesn't exist, remember it's case sensitive.");
							return;
						}
						int newInt = 0;
						try{
							newInt = Integer.parseInt(args[2]);
						}catch(Exception e){
							p.sendMessage(ChatColor.RED + "Please indicate a number of crystals.");
							return ;
						}
						if (Integer.valueOf(args[2]) < 0){
							p.sendMessage(ChatColor.RED + "Cannot take negative Crystals! Use " + ChatColor.AQUA + "/Crystals give");
							return ;
						}
						int Coinsorig = CrystalAPI.getCrystals(args[1]);
						int Coinstaken = Integer.valueOf(args[2]);
						int Coinsset = Coinstaken - Coinsorig;
						if (Coinsset < 0){
							p.sendMessage(ChatColor.GREEN + "You took " + ChatColor.AQUA + Coinstaken + ChatColor.GREEN + " from " + args[1] + ChatColor.GREEN + ", they now have " + ChatColor.AQUA + "0");
							CrystalAPI.setCrystals(args[1], 0);
						}else{
						p.sendMessage(ChatColor.GREEN + "You took " + ChatColor.AQUA + Coinstaken + ChatColor.GREEN + " from " + args[1] + ChatColor.GREEN + ", they now have " + ChatColor.AQUA + Coinsset);
						CrystalAPI.setCrystals(args[1], Coinsset);
						}
						return ;
				}
				if (args[0].equalsIgnoreCase("set")){
					int newInt = 0;
					try{
						newInt = Integer.parseInt(args[2]);
					}catch(Exception e){
						p.sendMessage(ChatColor.RED + "Please indicate a number of crystals.");
						return ;
					}
					if (newInt < 0){
						p.sendMessage(ChatColor.RED + "A player can't have less then 0 crystals.");
						return;
					}
					p.sendMessage(ChatColor.GREEN + "You set " + ChatColor.AQUA + args[1] + ChatColor.GREEN + "'s crystals to " + ChatColor.AQUA + newInt);
					CrystalAPI.setCrystals(args[1], newInt);
					return;
				}
				p.sendMessage(ChatColor.RED + "Unknown Command.");
			}
			if (args.length > 3){
				p.sendMessage(ChatColor.RED + "Unknown Command.");
			}
		}else{
			p.sendMessage(ChatColor.RED + "This command is for admins only.");	
		}
		}
	
	public static boolean ifPlayerExists(String p){
		if (Files.getDataFile().contains("Players." + p)){
			return true;
		}
		return false;
	}
}
