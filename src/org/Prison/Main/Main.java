package org.Prison.Main;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.Gadgets.Types.DeviceType;
import me.BenLoe.Gadgets.Types.SantaMorph;
import me.BenLoe.SuperSpleef.Game;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.Booster.BoosterCooldown;
import org.Prison.Main.Booster.BoosterManager;
import org.Prison.Main.CorruptEvents.GhastTerror;
import org.Prison.Main.CorruptEvents.Pigmen;
import org.Prison.Main.Currency.CrystalCommands;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Leaderboard.EShardLeaderboard;
import org.Prison.Main.Leaderboard.MoneyLeaderboard;
import org.Prison.Main.Leaderboard.ShardLeaderboard;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Menu.CrafterMenu;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.Trails.ParticleType;
import org.Prison.Main.Traits.SpeedTrait;
import org.Prison.Main.Tutorial.Tutorial;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class Main extends JavaPlugin {

	Files files = new Files(this);
	Events events = new Events(this);
	PlayerMoveListenener listener = new PlayerMoveListenener(this);
	public static HashMap<String,MenuType> Menu = new HashMap<String,MenuType>();
	public static HashMap<String,Integer> Tutorialint = new HashMap<String,Integer>();
	public static HashMap<String,Integer> bookshelf = new HashMap<String,Integer>();
	public static HashMap<String,Entry<Location,Integer>> antibook = new HashMap<>();
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
	public static List<String> Vanish = new ArrayList<>();
	public static HashMap<String,String> MineVote = new HashMap<>();
	public static boolean playerv = true;
	public static boolean monthly = false;
	public static HashMap<UUID,String> rabbit = new HashMap<UUID,String>();
	public static HashMap<UUID,UUID> armor = new HashMap<UUID,UUID>();
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();
		Team owner = board.registerNewTeam("Owner");
		owner.setPrefix("§c§lOWNER §c");
		Team admin = board.registerNewTeam("Admin");
		admin.setPrefix("§c§lADMIN §c");
		Team mod = board.registerNewTeam("Mod");
		mod.setPrefix("§4§lMOD §4");
		Team jrmod = board.registerNewTeam("JrMod");
		jrmod.setPrefix("§4§lJRMOD §4");
		Team ultra = board.registerNewTeam("Ultra");
		ultra.setPrefix("§6§lULTRA §6");
		Team elite = board.registerNewTeam("Elite");
		elite.setPrefix("§a§lELITE §a");
		Team vip = board.registerNewTeam("Vip");
		vip.setPrefix("§3§lVIP §3");
		Team normal = board.registerNewTeam("Normal");
		normal.setPrefix("§7");
		Files.saveDataFile();
		if (!Files.getLogFile().contains("Log")){
			Files.getLogFile().set("Log", new ArrayList<String>()); 
			Files.saveLogFile();
		}
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
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "baltop");
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
					if (!org.Prison.Lucky.Game.playerInGame(p)){
						p.setFoodLevel(22);
					}else{
						if (org.Prison.Lucky.Game.gs != org.Prison.Lucky.Game.GameState.FIGHT){
							p.setFoodLevel(22);
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
					if (!util.equals(ParticleType.NONE) && !util.equals(ParticleType.HELL) && !util.equals(ParticleType.HALO) && !util.equals(ParticleType.WITCH) && !util.equals(ParticleType.CORRUPT)){
						util.display(p);
					}
				}
				GhastTerror.checkBlockRegen();
				CrafterMenu.check();
				if (monthly){
					Location loc = new Location(Bukkit.getWorld("PrisonMap"), -222.5, 70.4, 189.5);
					for (int in = 0; in < 3; in++){
						double x = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
						double z = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
						ItemStack items = new ItemStack(Material.NETHER_STAR);
						ItemMeta itemm = items.getItemMeta();
						itemm.setDisplayName(new Random().nextInt(100) + "diamond");
						items.setItemMeta(itemm);
						Item item = loc.getWorld().dropItem(loc, items);
						item.setVelocity(new Vector(x, 0.3, z));
						item.setPickupDelay(1000000);
						CrafterMenu.items.add(item.getUniqueId());
					}
					for (Player p : Bukkit.getOnlinePlayers()){
						if (p.getLocation().distance(loc) < 10){
							p.playSound(loc, Sound.ITEM_PICKUP, 1f, 1f);
						}
					}
				}
			}
		}, 5 * 20l, 4l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
			public void run(){
				for (Player p : Bukkit.getOnlinePlayers()){
					ParticleType util = ParticleType.getActive(p);
					if (util.equals(ParticleType.HELL) || util.equals(ParticleType.HALO) || util.equals(ParticleType.WITCH) || util.equals(ParticleType.CORRUPT)){
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
					if (amount >= 1){
						int r = new Random().nextInt(3) + 1;
						BoosterCooldown.setCooldown(6, "Event");
						switch(r){
						case 1 :
							GhastTerror.startAttack();
							break;
						case 2 :
						case 3:
							Pigmen.start();
							break;
						}
					}
				}
				BoosterCooldown.checkCooldown("Money1");
				if (!BoosterCooldown.hasCooldown("Money1")){
					if (Files.getDataFile().getStringList("MoneyPlayers").size() > 13){
					MoneyLeaderboard.updateSigns();
					ShardLeaderboard.updateSigns();
					EShardLeaderboard.updateSigns();
					}
					BoosterCooldown.setCooldown(5, "Money1");
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
					TreeExplode.check();
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
		for (Player p : Bukkit.getOnlinePlayers()){
			if (Main.Vanish.contains(p.getName())){
				p.sendMessage("§c§lNo longer vanished.");
				p.setPlayerListName(RankType.getPlayerColor(RankType.getRank(p)) + p.getName());
				for (Player p1 : Bukkit.getOnlinePlayers()){
					if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
						if (FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
							p1.showPlayer(p);
						}
					}else{
						p1.showPlayer(p);
					}
				}
			}
		}
		for (Sheep sheep : Bukkit.getWorld("PrisonMap").getEntitiesByClass(Sheep.class)){
			if (sheeps.contains(sheep.getUniqueId())){
				sheep.teleport(sheep.getLocation().subtract(0, 500, 0));
			}
		}
		Events.plugin = null;
		Files.plugin = null;
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();
		Team owner = board.getTeam("Owner");
		owner.unregister();
		Team admin = board.getTeam("Admin");
		admin.unregister();
		Team mod = board.getTeam("Mod");
		mod.unregister();
		Team jrmod = board.getTeam("JrMod");
		jrmod.unregister();
		Team ultra = board.getTeam("Ultra");
		ultra.unregister();
		Team elite = board.getTeam("Elite");
		elite.unregister();
		Team vip = board.getTeam("Vip");
		vip.unregister();
		Team normal = board.getTeam("Normal");
		normal.unregister();
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
						if (Bukkit.getPlayer(args[0]) != null){
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
						if (Bukkit.getPlayer(args[0]) != null){
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
						if (Bukkit.getPlayer(args[0]) != null){
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
			if (Label.equalsIgnoreCase("Build")){
				if (p.hasPermission("Build.Build")){
					p.teleport(new Location(Bukkit.getWorld("Build"), 88.5, 23, 15.5));
				}else{
					p.sendMessage(ChatColor.RED + "You are not a builder.");
				}
			}
			if (Label.equalsIgnoreCase("pVanish")){
				if (p.hasPermission("Vanish.Vanish")){
					if (Main.Vanish.contains(p.getName())){
						p.sendMessage("§c§lNo longer vanished.");
						p.setPlayerListName(RankType.getPlayerColor(RankType.getRank(p)) + p.getName());
						for (Player p1 : Bukkit.getOnlinePlayers()){
							if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
								if (FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
									p1.showPlayer(p);
								}
							}else{
								p1.showPlayer(p);
							}
						}
						Main.Vanish.remove(p.getName());
					}else{
						p.sendMessage("§a§lYou are vanished from normal players.");
						p.setPlayerListName(RankType.getPlayerColor(RankType.getRank(p)) + p.getName() + " §7[Vanished]");
						for (Player p1 : Bukkit.getOnlinePlayers()){
							if (!p1.hasPermission("Vanish.See")){
								p1.hidePlayer(p);
							}
						}
						Main.Vanish.add(p.getName());
					}
				}else{
					p.sendMessage(ChatColor.RED + "This command is for moderators only.");
				}
			}
			if (Label.equalsIgnoreCase("SecretSanta")){
				int util = 1;
				if (util < 10){
					p.sendMessage(ChatColor.RED + "Santa is gone for the year!");
					return true;
				}
				if (args.length < 1 || args.length > 2){
					p.sendMessage("§cWrong syntax: /SecretSanta (Name)");
				}else{
					Calendar c = Calendar.getInstance();
					int day = c.get(Calendar.DAY_OF_YEAR);
					int pday = -10;
					if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Secret")){
						pday = Files.getDataFile().getInt("Players." + p.getUniqueId() + ".Secret");
					}
					if (pday != day){
						if (args[0].equalsIgnoreCase(p.getName())){
							p.sendMessage("§2§l[§4§lSanta§2§l]: §cHO HO No... you can't give secret santa's to yourself!");
							return true;
						}
						if (Bukkit.getPlayer(args[0]) != null){
							if (Game.playerInGame(Bukkit.getPlayer(args[0]))){
								p.sendMessage("§2§l[§4§lSanta§2§l]: §cThat player is in a super spleef game, please wait until they leave.");
								return true;
							}
							ItemStack presents = SantaMorph.getRandomPresent();
							ItemMeta itemm = presents.getItemMeta();
							itemm.setDisplayName("§b§lPresent §7(Right click altar)");
							presents.setItemMeta(itemm);
							Player give = Bukkit.getPlayer(args[0]);
							give.sendMessage("§2§l[§4§lSanta§2§l]: §bSomeone just sent you a secret santa of 1 present!");
							give.playSound(give.getLocation(), Sound.LEVEL_UP, 1f, 1f);
							give.getInventory().addItem(presents);
							give.updateInventory();
							p.sendMessage("§2§l[§4§lSanta§2§l]: §aYour secret santa has been sent!");
							Files.getDataFile().set("Players." + p.getUniqueId() + ".Secret", day);
							Files.saveDataFile();
						}else{
							p.sendMessage("§2§l[§4§lSanta§2§l]: §cThat player is not online!");
						}
					}else{
						p.sendMessage("§2§l[§4§lSanta§2§l]: §cYou already sent someone a secret santa today!");
					}
				}
			}
			if (Label.equalsIgnoreCase("Convert")){
				if (p.getItemInHand() != null){
					ItemStack item = p.getItemInHand();
					if (item.hasItemMeta()){
						ItemMeta itemm = item.getItemMeta();
						if (itemm.hasLore() && itemm.getLore().size() > 1){
							if (itemm.getLore().get(1).contains("Efficiency") || itemm.getLore().get(1).contains("Protection") || itemm.getLore().get(1).contains("Sharpness")){
								if (itemm.hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
									itemm.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
									item.setItemMeta(itemm);
									p.getInventory().setItemInHand(item);
									p.updateInventory();
									p.sendMessage(ChatColor.GREEN + "Converted! You may now use it in storage chests.");
									return true;
								}else{
									p.sendMessage(ChatColor.RED + "Item already converted.");
									return true;
								}
							}
						}
					}
				}
				p.sendMessage(ChatColor.RED + "Your currently held item is not convertible.");
			}
			if (p.isOp()){
				if (Label.equalsIgnoreCase("test2")){
					DeviceType.SURGE.addAmount(2, p);
				}
				if (Label.equalsIgnoreCase("test3")){
					p.sendMessage("test3 received");
					Stats.getStats(p.getUniqueId()).addShards(1000);
				}
				if (Label.equalsIgnoreCase("GiveVote")){
					if (args.length == 1){
						DeviceType.VOTING_KEY.setDevice(Bukkit.getPlayer(args[0]));
						DeviceType.VOTING_KEY.addAmount(1, Bukkit.getPlayer(args[0]));
						Bukkit.getPlayer(args[0]).sendMessage("§a§lThanks §afor voting. To use your voting key, go to §e/warp altar §aand right click the voting altar with it.");
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
						DeviceType.VOTING_KEY.setDevice(Bukkit.getPlayer(args[0]));
						DeviceType.VOTING_KEY.addAmount(Integer.parseInt(args[1]), Bukkit.getPlayer(args[0]));
						Bukkit.getPlayer(args[0]).sendMessage("§a§lThanks §afor voting. To use your voting key, go to §e/warp altar §aand right click the voting altar with it.");
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
				if (Label.equalsIgnoreCase("test1")){
					p.sendMessage("§a§lCollect stats...");
					int votes = 1517;
					int speed = 0;
					int intellect = 0;
					int crystals = 0;
					int blocksbroken = 0;
					int timesranked = 0;
					int superspleef = 0;
					int achievementsgotten = 0;
					int totalshards = 0;
					int totalfavor = 0;
					int totalgadgets = 0;
					int mostgadgets = 0;
					String mostgadgetss = "";
					int nofriend = 0;
					int unique = 0;
					int votesdecember = 0;
					
					Set<String> PrisonMain = Files.getDataFile().getConfigurationSection("Players").getKeys(false);
					for (String s : PrisonMain){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							if (Files.getDataFile().contains("Players." + s + ".Speed")){
								speed += Files.getDataFile().getInt("Players." + s + ".Speed");
							}
							if (Files.getDataFile().contains("Players." + s + ".Smartlvl")){
								intellect += Files.getDataFile().getInt("Players." + s + ".Smartlvl");
							}
							if (Files.getDataFile().contains("Players." + s + ".Crystals")){
								crystals += Files.getDataFile().getInt("Players." + s + ".Crystals");
							}
							if (Files.getDataFile().contains("Players." + s + ".Votes")){
								votesdecember += Files.getDataFile().getInt("Players." + s + ".Votes");
							}
							if (Files.getDataFile().contains("Players." + s + ".Letter")){
								LetterType l = LetterType.fromString(Files.getDataFile().getString("Players." + s + ".Letter"));
								timesranked += (l.getInt() - 1);
							}
						}
					}
					Set<String> Achievement = org.PrisonMain.Achievement.Files.config().getConfigurationSection("Players").getKeys(false);
					for (String s : Achievement){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							if (org.PrisonMain.Achievement.Files.config().contains("Players." + s + ".Blocks")){
								blocksbroken += org.PrisonMain.Achievement.Files.config().getInt("Players." + s + ".Blocks");
							}
							if (org.PrisonMain.Achievement.Files.config().contains("Players." + s + ".Completed")){
								achievementsgotten += org.PrisonMain.Achievement.Files.config().getList("Players." + s + ".Completed").size();
							}
						}
					}	
					Set<String> Blackmarket = me.BenLoe.Blackmarket.Files.getDataFile().getConfigurationSection("Players").getKeys(false);
					for (String s : Blackmarket){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							if (me.BenLoe.Blackmarket.Files.getDataFile().contains("Players." + s + ".shards")){
								totalshards += me.BenLoe.Blackmarket.Files.getDataFile().getInt("Players." + s + ".shards");
							}
						}
					}
					Set<String> Favor = me.BenLoe.quest.Files.getDataFile().getConfigurationSection("Players").getKeys(false);
					for (String s : Favor){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							if (me.BenLoe.quest.Files.getDataFile().contains("Players." + s + ".favor")){
								totalfavor += me.BenLoe.quest.Files.getDataFile().getInt("Players." + s + ".favor");
							}
						}
					}
					Set<String> gadget = me.BenLoe.Gadgets.Files.getDataFile().getConfigurationSection("Players").getKeys(false);
					for (String s : gadget){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							for (DeviceType d : DeviceType.values()){
								if (me.BenLoe.Gadgets.Files.getDataFile().contains("Players." + s + ".Device." + d.name())){
									totalgadgets += me.BenLoe.Gadgets.Files.getDataFile().getInt("Players." + s + ".Device." + d.name());
									if (mostgadgets < me.BenLoe.Gadgets.Files.getDataFile().getInt("Players." + s + ".Device." + d.name())){
										mostgadgets =  me.BenLoe.Gadgets.Files.getDataFile().getInt("Players." + s + ".Device." + d.name());
										mostgadgetss = d.name();
									}
								}
							}
						}
					}
					Set<String> SuperSpleef = me.BenLoe.SuperSpleef.Files.getDataFile().getConfigurationSection("Players").getKeys(false);
					for (String s : SuperSpleef){
						if (s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
							if (me.BenLoe.SuperSpleef.Files.getDataFile().contains("Players." + s + ".GamesPlayed")){
								superspleef += me.BenLoe.SuperSpleef.Files.getDataFile().getInt("Players." + s + ".GamesPlayed");
							}
						}
					}
					Set<String> friends = org.Prison.Friends.Files.config().getConfigurationSection("Players").getKeys(false);
					unique = friends.size();
					for (String s : friends){
						if (org.Prison.Friends.Files.config().contains("Players." + s + ".friends")){
							if (org.Prison.Friends.Files.config().get("Players." + s + ".friends") instanceof String){
								nofriend++;
							}
						}
					}
					p.sendMessage("Â§aÂ§lStats collected!");
					p.sendMessage("");
					p.sendMessage("Â§7Unique Players: Â§e" + unique);
					p.sendMessage("");
					p.sendMessage("Â§7Total Crystals: Â§b" + crystals);
					p.sendMessage("Â§7Total Shards: Â§9" + totalshards);
					p.sendMessage("Â§7Total Favor Points: Â§e" + totalfavor);
					p.sendMessage("");
					p.sendMessage("Â§7Votes: Â§a" + votes);
					p.sendMessage("Â§7Votes December: Â§a" + votesdecember);
					p.sendMessage("Â§7Blocks Broken: Â§a" + blocksbroken);
					p.sendMessage("Â§7Achievements Completed: Â§a" + achievementsgotten);
					p.sendMessage("Â§7Times Ranked Up: Â§a" + timesranked);
					p.sendMessage("Â§7Super spleef games played: Â§a" + superspleef);
					p.sendMessage("");
					p.sendMessage("Â§7Speed levels: Â§a" + speed);
					p.sendMessage("Â§7Intellect levels: Â§a" + intellect);
					p.sendMessage("");
					p.sendMessage("Â§7Total gadgets: Â§a" + totalgadgets);
					p.sendMessage("Â§7Most gadgets: Â§a" + mostgadgets);
					p.sendMessage("Â§7Gadget type: Â§a" + mostgadgetss);
					p.sendMessage("");
					p.sendMessage("Â§7People with no friends: Â§a" + nofriend);
					p.sendMessage("");
					p.sendMessage("Â§bÂ§lHappy Mother Fucking New Year!");
				}
			}
		}else{
			if (Label.equalsIgnoreCase("GiveVote")){
				if (args.length == 1){
					DeviceType.VOTING_KEY.setDevice(Bukkit.getPlayer(args[0]));
					DeviceType.VOTING_KEY.addAmount(1, Bukkit.getPlayer(args[0]));
					Bukkit.getPlayer(args[0]).sendMessage("§a§lThanks §afor voting. To use your voting key, go to §e/warp altar §aand right click the voting altar with it.");
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
					DeviceType.VOTING_KEY.setDevice(Bukkit.getPlayer(args[0]));
					DeviceType.VOTING_KEY.addAmount(Integer.parseInt(args[1]), Bukkit.getPlayer(args[0]));
					Bukkit.getPlayer(args[0]).sendMessage("§a§lThanks §afor voting. To use your voting key, go to §e/warp altar §aand right click the voting altar with it.");
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
