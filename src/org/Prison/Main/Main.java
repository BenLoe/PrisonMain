package org.Prison.Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.BenLoe.Gadgets.Types.DeviceType;

import org.Prison.Main.Booster.BoosterCooldown;
import org.Prison.Main.Booster.BoosterManager;
import org.Prison.Main.CorruptEvents.DropKey;
import org.Prison.Main.CorruptEvents.FireRain;
import org.Prison.Main.CorruptEvents.GhastTerror;
import org.Prison.Main.CorruptEvents.Pigmen;
import org.Prison.Main.Currency.CrystalCommands;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Leaderboard.EShardLeaderboard;
import org.Prison.Main.Leaderboard.MoneyLeaderboard;
import org.Prison.Main.Leaderboard.ShardLeaderboard;
import org.Prison.Main.Menu.CrafterMenu;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.Trails.ParticleType;
import org.Prison.Main.Traits.SpeedTrait;
import org.Prison.Main.Tutorial.Tutorial;
import org.Prison.Punish.PunishAPI;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

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
	public static HashMap<String,Integer> guard = new HashMap<String,Integer>();
	public static boolean chatSilenced = false;
	public static List<UUID> sheeps = new ArrayList<UUID>();
	public static List<Location> particles = new ArrayList<>();
	public static HashMap<String,String> MineVote = new HashMap<>();
	
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
				Sheep sheep1 = (Sheep) Bukkit.getWorld("PrisonMap").spawnEntity(new Location(Bukkit.getWorld("PrisonMap"), -217, 70, 194), EntityType.SHEEP);
				Sheep sheep2 = (Sheep) Bukkit.getWorld("PrisonMap").spawnEntity(new Location(Bukkit.getWorld("PrisonMap"), -217, 70, 194), EntityType.SHEEP);
				Sheep sheep3 = (Sheep) Bukkit.getWorld("PrisonMap").spawnEntity(new Location(Bukkit.getWorld("PrisonMap"), -217, 70, 194), EntityType.SHEEP);	
				sheep1.setColor(DyeColor.RED);
				sheep1.setCustomName("§c§lPunch Me!");
				sheep1.setCustomNameVisible(true);
				sheep2.setColor(DyeColor.LIME);
				sheep2.setCustomName("§a§lPunch Me!");
				sheep2.setCustomNameVisible(true);
				sheep3.setColor(DyeColor.BLUE);
				sheep3.setCustomName("§9§lPunch Me!");
				sheep3.setCustomNameVisible(true);
				sheeps.add(sheep1.getUniqueId());
				sheeps.add(sheep2.getUniqueId());
				sheeps.add(sheep3.getUniqueId());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "killall drops PrisonMap");
				Calendar c = Calendar.getInstance();
				Files.getDataFile().set("CurrentMonth", c.get(Calendar.MONTH));
				Files.saveDataFile();
			}
		}, 3 * 20l);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				Tutorial.check();
				for (Player p : Bukkit.getOnlinePlayers()){
					if (guard.containsKey(p.getName())){
						int newi = guard.get(p.getName()) - 1;
						guard.remove(p.getName());
						if (newi != 0){
							guard.put(p.getName(), newi);
						}
					}
				}
				for (Sheep sheep : Bukkit.getWorld("PrisonMap").getEntitiesByClass(Sheep.class)){
					if (sheeps.contains(sheep.getUniqueId())){
						if (DonatorCellLine.ifPlayerIsIn(sheep.getLocation(), "SheepLine")){
							sheep.teleport(new Location(Bukkit.getWorld("PrisonMap"), -217, 70, 194));
						}
					}
				}
				GhastTerror.check();
				FireRain.check();
				Pigmen.check();
			}
		}, 20 *5l, 20l);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				for (World world : Bukkit.getWorlds()){
					for (Entity e : world.getEntitiesByClass(Item.class)){
						if (ParticleType.diamonds.contains(e.getUniqueId())){
							if (e.getTicksLived() > 10){
								ParticleType.diamonds.remove(e.getUniqueId());
								e.teleport(e.getLocation().subtract(0, 500, 0));
							}
						}
						if (ParticleType.gold.contains(e.getUniqueId())){
							if (e.getTicksLived() > 10){
								ParticleType.gold.remove(e.getUniqueId());
								e.teleport(e.getLocation().subtract(0, 500, 0));
							}
						}
					}
				}
				
				for (Player p : Bukkit.getOnlinePlayers()){
					ParticleType util = ParticleType.getActive(p);
					if (!util.equals(ParticleType.NONE) && !util.equals(ParticleType.HELL) && !util.equals(ParticleType.HALO) && !util.equals(ParticleType.WITCH)){
						util.display(p);
					}
				}
				GhastTerror.checkBlockRegen();
				FireRain.check2();
				CrafterMenu.check();
			}
		}, 5 * 20l, 4l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
			public void run(){
				for (Player p : Bukkit.getOnlinePlayers()){
					ParticleType util = ParticleType.getActive(p);
					if (util.equals(ParticleType.HELL) || util.equals(ParticleType.HALO) || util.equals(ParticleType.WITCH)){
						util.display1(p);
					}
				}
			}
		}, 5*20l, 2l);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				BoosterCooldown.checkCooldown("Event");
				if (!BoosterCooldown.hasCooldown("Event")){
					int amount = 0;
					for (Player p : Bukkit.getOnlinePlayers()){
					if (p.getWorld().getName().equals("NetherMap")) amount++;
					}
					if (amount > 1){
						int r = new Random().nextInt(4) + 1;
						BoosterCooldown.setCooldown(6, "Event");
						switch(r){
						case 1 :
							GhastTerror.startAttack();
							break;
						case 2 :
							Pigmen.start();
							break;
						case 3: 
							FireRain.start();
							break;
						case 4:
							DropKey.drop();
							break;
						}
					}
				}
				BoosterCooldown.checkCooldown("Money");
				if (!BoosterCooldown.hasCooldown("Money")){
					if (Files.getDataFile().getStringList("MoneyPlayers").size() > 13){
					MoneyLeaderboard.updateSigns();
					ShardLeaderboard.updateSigns();
					EShardLeaderboard.updateSigns();
					}
					BoosterCooldown.setCooldown(5, "Money");
				}
				Bukkit.getWorld("PrisonMap").setStorm(false);
				Bukkit.getWorld("PrisonMap").setWeatherDuration(10000);
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
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
					if (!bookshelf.isEmpty()){
						BookShelf.check();
					}
					if (!wood.isEmpty()){
						WoodColecting.check();
					}
					if (!particles.isEmpty()){
						for (Location loc : particles){
							ParticleEffect.SPELL_WITCH.display(0f, 0f, 0f, 0f, 6, loc, 20);
						}
					}
					DropKey.check();
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
	
	public void onDisable(){
		for (Sheep sheep : Bukkit.getWorld("PrisonMap").getEntitiesByClass(Sheep.class)){
			if (sheeps.contains(sheep.getUniqueId())){
				sheep.teleport(sheep.getLocation().subtract(0, 500, 0));
			}
		}
		Events.plugin = null;
		Files.plugin = null;
	}
	
	public static Location getLocation(String type){
		return new Location(Bukkit.getWorld(Files.config().getString("Location." + type + ".world")), Files.config().getInt("Location." + type + ".x"), Files.config().getInt("Location." + type + ".y"),Files.config().getInt("Location." + type + ".z")).setDirection(Files.config().getVector("Location." + type + ".Direction"));
	}
	@SuppressWarnings("unchecked")
	public boolean onCommand(CommandSender sender, Command cmd,
			String Label, String[] args){
		if (Label.equalsIgnoreCase("crystal")){
			CrystalCommands.execute(sender, args);
		}
		if (sender instanceof Player){
			Player p = (Player) sender;
			BoosterManager.attemptBoosterCommand(p, args, Label);
			if (Label.equalsIgnoreCase("reloadfiles")){
				if (p.isOp()){
				Files.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Reloaded PrisonMain config files.");
				}
			}
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
							Files.getDataFile().set("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Letter", args[1]);
							Files.saveDataFile();
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
							Files.getDataFile().set("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Speed", Integer.parseInt(args[1]));
							Files.saveDataFile();
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
							Files.getDataFile().set("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Smartlvl", Integer.parseInt(args[1]));
							Files.saveDataFile();
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
			if (Label.equalsIgnoreCase("MineVote")){
				if (MineVote.containsKey(p.getName())){
					if (args.length < 2 || args.length > 2){
						p.sendMessage(ChatColor.RED + "You cannot do that right now.");
						return true;
					}
				}else{
					p.sendMessage(ChatColor.RED + "You cannot do that right now.");
				}
			}
			if (p.isOp()){
			if (Label.equalsIgnoreCase("test1")){
				FireRain.start();
			}
			if (Label.equalsIgnoreCase("test2")){
				GhastTerror.startAttack();
			}
			if (Label.equalsIgnoreCase("test3")){
				Pigmen.start();
			}
			}
		}else{
			if (Label.equalsIgnoreCase("GiveVote")){
				if (args.length == 1){
					DeviceType.VOTING_KEY.addAmount(1, Bukkit.getPlayer(args[0]));
					Bukkit.getPlayer(args[0]).sendMessage("§a§lThank you §afor voting. To use your voting key, select the §e\"voting key\" §agadget and right click the §e/warp altar §awith it.");
					Bukkit.getPlayer(args[0]).playSound(Bukkit.getPlayer(args[0]).getLocation(), Sound.LEVEL_UP, 1f, 1f);
					ItemAPI.givePlayerItems(Bukkit.getPlayer(args[0]));
					if (Files.getDataFile().contains("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Votes")){
						int old = Files.getDataFile().getInt("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Votes");
						Files.getDataFile().set("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Votes", old + 1);
						Files.saveDataFile();
						if ((old + 1) == 50){
							if (Bukkit.getPlayer(args[0]) != null){
								AchievementAPI.completeAchievement(Bukkit.getPlayer(args[0]), AchievementMenu.THE_VOTING_KING);
							}
						}
					}else{
						Files.getDataFile().set("Players." + Bukkit.getPlayer(args[0]).getUniqueId() + ".Votes", 1);
						Files.saveDataFile();
					}
				}
				
				if (args.length == 2){
					DeviceType.VOTING_KEY.addAmount(Integer.parseInt(args[1]), Bukkit.getPlayer(args[0]));
					Bukkit.getPlayer(args[0]).sendMessage("§a§lThank you §afor voting. To use your voting key, select the §e\"voting key\" §agadget and right click the §e/warp altar §awith it.");
					Bukkit.getPlayer(args[0]).playSound(Bukkit.getPlayer(args[0]).getLocation(), Sound.LEVEL_UP, 1f, 1f);
					ItemAPI.givePlayerItems(Bukkit.getPlayer(args[0]));
				}
			}
			if (Label.equalsIgnoreCase("GiveCorrupt")){
				if (args.length == 1){
					DeviceType.CORRUPT_CHEST.addAmount(1, Bukkit.getPlayer(args[0]));
					DeviceType.KEY.addAmount(1, Bukkit.getPlayer(args[0]));
					ItemAPI.givePlayerItems(Bukkit.getPlayer(args[0]));
				}
				
				if (args.length == 2){
					DeviceType.CORRUPT_CHEST.addAmount(Integer.parseInt(args[1]), Bukkit.getPlayer(args[0]));
					DeviceType.KEY.addAmount(Integer.parseInt(args[1]), Bukkit.getPlayer(args[0]));
					ItemAPI.givePlayerItems(Bukkit.getPlayer(args[0]));
				}
			}
		}
		return false;
	}
}
