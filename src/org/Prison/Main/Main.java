package org.Prison.Main;

import java.util.HashMap;
import java.util.UUID;

import me.BenLoe.Gadgets.Types.DeviceType;
import mkremins.fanciful.FancyMessage;

import org.Prison.Main.Booster.BoosterCooldown;
import org.Prison.Main.Booster.BoosterManager;
import org.Prison.Main.Currency.CrystalCommands;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Leaderboard.EShardLeaderboard;
import org.Prison.Main.Leaderboard.MoneyLeaderboard;
import org.Prison.Main.Leaderboard.ShardLeaderboard;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.Traits.SpeedTrait;
import org.Prison.Main.Tutorial.Tutorial;
import org.Prison.Punish.PunishAPI;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	Files files = new Files(this);
	Events events = new Events(this);
	PlayerMoveListenener listener = new PlayerMoveListenener(this);
	public static HashMap<String,MenuType> Menu = new HashMap<String,MenuType>();
	public static HashMap<String,Integer> Tutorialint = new HashMap<String,Integer>();
	public static HashMap<String,Integer> bookshelf = new HashMap<String,Integer>();
	public static HashMap<String,Integer> wood = new HashMap<String,Integer>();
	public static HashMap<String,Block> woodBlock = new HashMap<String,Block>();
	public static HashMap<String,Block> bookshelfBlock = new HashMap<String,Block>();
	public static HashMap<String,UUID> disguisedChicken = new HashMap<String,UUID>();
	public static HashMap<String,UUID> pet = new HashMap<String,UUID>();
	public static HashMap<String,Integer> disguisedId = new HashMap<String,Integer>();
	public static boolean chatSilenced = false;
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		
		Files.saveDataFile();
		Bukkit.getScheduler().runTaskLater(this, new Runnable(){
			public void run(){
				for (Player p : Bukkit.getOnlinePlayers()){
					SpeedTrait.setCorrectSpeed(p);
					if (!PlayerMode.isInPlayerMode(p)){
						ItemAPI.givePlayerItems(p);
						}
				}
			}
		}, 5 * 20l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
			public void run(){
				Tutorial.check();
			}
		}, 20 *5l, 20l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
			public void run(){
				BoosterCooldown.checkCooldown("Money");
				if (!BoosterCooldown.hasCooldown("Money")){
					MoneyLeaderboard.updateSigns();
					ShardLeaderboard.updateSigns();
					EShardLeaderboard.updateSigns();
					BoosterCooldown.setCooldown(5, "Money");
				}
			}
		}, 20 *20l, 60 * 20l);
		Bukkit.getScheduler().runTaskLater(this, new Runnable(){
			public void run(){
				for (Player p : Bukkit.getOnlinePlayers()){
					if (p.getWorld().getName().equals("PVP")){
					me.BenLoe.PitPvP.InfoBoard.setBoard(p);
					}else{
						InfoBoard.setBoardFor(p);
					}
				}
			}
		}, 5 * 20l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
					if (!bookshelf.isEmpty()){
						BookShelf.check();
					}
					if (!wood.isEmpty()){
						WoodColecting.check();
					}
			}
		}, 20 *5l, 8l);
		for (Player p : Bukkit.getOnlinePlayers()){
			if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				for (Player p1 : Bukkit.getOnlinePlayers()){
					p.hidePlayer(p1);
					if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
						p1.hidePlayer(p);
					}
				}
			}
		}
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(events, this);
		Bukkit.getPluginManager().registerEvents(listener, this);
	}
	public static Location getLocation(String type){
		return new Location(Bukkit.getWorld(Files.config().getString("Location." + type + ".world")), Files.config().getInt("Location." + type + ".x"), Files.config().getInt("Location." + type + ".y"),Files.config().getInt("Location." + type + ".z")).setDirection(Files.config().getVector("Location." + type + ".Direction"));
	}
	public boolean onCommand(CommandSender sender, Command cmd,
			String Label, String[] args){
		if (Label.equalsIgnoreCase("crystal")){
			CrystalCommands.execute(sender, args);
		}
		if (sender instanceof Player){
			Player p = (Player) sender;
			BoosterManager.attemptBoosterCommand(p, args, Label);
			if (Label.equalsIgnoreCase("sc")){
				if (p.hasPermission("Staff.Chat")){
					if (args.length == 0){
						if (ChatMessages.inStaff.contains(p.getName())){
							p.sendMessage(ChatColor.GREEN + "Now talking in global chat.");
							ChatMessages.inStaff.remove(p.getName());
						}else{
							p.sendMessage(ChatColor.GREEN + "Now talking in staff chat.");
							ChatMessages.inStaff.add(p.getName());
						}
					}else{
						String message = "";
						for(int i = 0; i < args.length; i++) message += (i != 0 ? " " : "") + args[i];
						ChatMessages.sendStaffMessage(p, message.replace("&", "§"));
					}
				}else{
					p.sendMessage(ChatColor.RED + "Staff only command.");
				}
			}
			if (Label.equalsIgnoreCase("SetLetter")){
				if (p.hasPermission("Letter.Set")){
					if (args.length == 2){
						if (PunishAPI.onlinePlayer(args[0])){
							p.sendMessage(ChatColor.GREEN + "Player's letter set to " + args[1]);
							Files.config().set("Players." + args[0] + ".Letter", args[1]);
							Files.saveConfig();
						}else{
							p.sendMessage(ChatColor.RED + "Player not online.");
						}
					}else{
						p.sendMessage(ChatColor.RED + "Invalid args: /setletter (Player) (Letter)");
					}
				}
			}
			if (Label.equalsIgnoreCase("SetSpeed")){
				if (p.hasPermission("Speed.Set")){
					if (args.length == 2){
						if (PunishAPI.onlinePlayer(args[0])){
							p.sendMessage(ChatColor.GREEN + "Player's speed set to " + args[1]);
							Files.config().set("Players." + args[0] + ".Speed", Integer.parseInt(args[1]));
							Files.saveConfig();
						}else{
							p.sendMessage(ChatColor.RED + "Player not online.");
						}
					}else{
						p.sendMessage(ChatColor.RED + "Invalid args: /setspeed (Player) (lvl)");
					}
				}
			}
			if (Label.equalsIgnoreCase("SetIntellect")){
				if (p.hasPermission("Letter.Set")){
					if (args.length == 2){
						if (PunishAPI.onlinePlayer(args[0])){
							p.sendMessage(ChatColor.GREEN + "Player's intellect set to " + args[1]);
							Files.config().set("Players." + args[0] + ".Smartlvl", Integer.parseInt(args[1]));
							Files.saveConfig();
						}else{
							p.sendMessage(ChatColor.RED + "Player not online.");
						}
					}else{
						p.sendMessage(ChatColor.RED + "Invalid args: /setIntellect (Player) (lvl)");
					}
				}
			}
			if (Label.equalsIgnoreCase("silence")){
				if (p.hasPermission("Punish.Mute")){
					chatSilenced = true;
					p.sendMessage(ChatColor.GREEN + "Chat silenced for 5 seconds, do your muting in that time.");
					Bukkit.getScheduler().runTaskLater(this, new Runnable(){
						public void run(){
							chatSilenced = false;
						}
					}, 5 * 20l);
				}else{
					p.sendMessage(ChatColor.RED + "No permission.");
				}
			}
			if (Label.equalsIgnoreCase("clearchat")){
				if (p.hasPermission("Punish.Mute")){
					for (Player p1 : Bukkit.getOnlinePlayers()){
						for (int i = 0; i < 120; i++){
							p1.sendMessage("  ");
						}
						p1.sendMessage(ChatColor.GREEN + "Chat cleared by a staff member.");
					}
				}
			}
			if (Label.equalsIgnoreCase("exit")){
				if (Main.Tutorialint.containsKey(p.getName())){
					Main.Tutorialint.remove(p.getName());
					p.teleport(getLocation("spawn"));
					p.sendMessage(ChatColor.YELLOW + "Exited tutorial.");
				}else{
					p.sendMessage(ChatColor.RED + "You are not taking the tutorial.");
				}
			}
			if (Label.equalsIgnoreCase("playermode")){
				if (p.hasPermission("Prison.Playermode")){
					if (PlayerMode.isInPlayerMode(p)){
						p.sendMessage(ChatColor.RED + "You are now in normal playermode");
						PlayerMode.setInPlayerMode(false, p);
						ItemAPI.givePlayerItems(p);
					}else{
						p.sendMessage(ChatColor.GREEN + "You are now in staff mode");
						PlayerMode.setInPlayerMode(true, p);
					}
				}else{
					p.sendMessage(ChatColor.RED + "This command is for admins only.");
				}
			}
			if (Label.equalsIgnoreCase("setlocation")){
				if (p.hasPermission("Prison.Locations")){
					if (args.length == 0){
						p.sendMessage(ChatColor.RED + "Wrong syntax: " + ChatColor.AQUA + "/setlocation (name)");
					}
					if (args.length == 1){
						if (Files.config().contains("Location." + args[0])){
							p.sendMessage(ChatColor.RED + "That location already exists.");
						}else{
							p.sendMessage(ChatColor.GREEN + "Location " + ChatColor.AQUA + args[0] + ChatColor.GREEN + " set to your location.");
							p.sendMessage(ChatColor.GREEN + "You can now use " + ChatColor.AQUA + "/tolocation " + args[0]);
							Files.config().set("Location." + args[0] + ".world", p.getLocation().getWorld().getName());
							Files.config().set("Location." + args[0] + ".x", p.getLocation().getBlockX());
							Files.config().set("Location." + args[0] + ".y", p.getLocation().getBlockY());
							Files.config().set("Location." + args[0] + ".z", p.getLocation().getBlockZ());
							Files.config().set("Location." + args[0] + ".Direction", p.getLocation().getDirection());
							Files.saveConfig();
						}
					}
				}else{
					p.sendMessage(ChatColor.RED + "This command is for admins only.");
				}
			}
			if (Label.equalsIgnoreCase("tolocation")){
				if (p.hasPermission("Prison.Locations")){
				if (args.length == 0){
					p.sendMessage(ChatColor.RED + "To get to a location do this:");
					p.sendMessage(ChatColor.AQUA + "/tolocation (location)");
				}
				if (args.length == 1){
					if (Files.config().contains("Location." + args[0])){
						p.teleport(new Location(Bukkit.getWorld(Files.config().getString("Location." + args[0] + ".world")), Files.config().getInt("Location." + args[0] + ".x"), Files.config().getInt("Location." + args[0] + ".y"),Files.config().getInt("Location." + args[0] + ".z")));
					}else{
						p.sendMessage(ChatColor.RED + "This location doesn't exist.");
					}
				}
				}else{
					p.sendMessage(ChatColor.RED + "This command is for admins only.");
				}
			}
			if (Label.equalsIgnoreCase("save")){
				if (p.isOp()){
				if (args.length == 0){
					p.sendMessage("nope");
				}
				if (args.length == 1){
					CellBlockLines.setRegion(args[0], p);
				}
				}else{
					p.sendMessage(ChatColor.RED + "This command is for admins only.");
				}
			}
			if (Label.equalsIgnoreCase("updatelead")){
				if (p.isOp()){
					MoneyLeaderboard.updateSigns();
					ShardLeaderboard.updateSigns();
					EShardLeaderboard.updateSigns();
					p.sendMessage(ChatColor.GREEN + "Leaderboards updated.");
				}
			}
		}else{
			if (Label.equalsIgnoreCase("GiveVote")){
				if (args.length == 1){
					DeviceType.VOTING_KEY.addAmount(1, args[0]);
					if (Files.config().contains("Players." + args[0] + ".Votes")){
						int old = Files.config().getInt("Players." + args[0] + ".Votes");
						Files.config().set("Players." + args[0] + ".Votes", old + 1);
						Files.saveConfig();
						if ((old + 1) == 50){
							if (Bukkit.getPlayer(args[0]) != null){
								AchievementAPI.completeAchievement(Bukkit.getPlayer(args[0]), AchievementMenu.THE_VOTING_KING);
							}
						}
					}else{
						Files.config().set("Players." + args[0] + ".Votes", 1);
						Files.saveConfig();
					}
				}
			}
		}
		return false;
	}
}
