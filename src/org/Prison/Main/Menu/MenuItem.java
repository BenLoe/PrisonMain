package org.Prison.Main.Menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.Gadgets.Menu.GadgetMenu;
import me.BenLoe.quest.QuestAPI;
import me.BenLoe.quest.Menu.QuestMenu;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.Files;
import org.Prison.Main.Main;
import org.Prison.Main.Currency.CrystalAPI;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.Trails.ParticleType;
import org.Prison.Main.Traits.SmartTrait;
import org.Prison.Main.Traits.SpeedTrait;
import org.Prison.Tools.ToolStats;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public enum MenuItem {
	
	MAIN_MENU_GADGETS("MAIN_MENU_GADGETS", 19, MenuType.MAIN_MENU),
	
	MAIN_MENU_FAVOR_BOOK("MAIN_MENU_FAVOR_BOOK", 31, MenuType.MAIN_MENU),
	
	MAIN_MENU_OPTIONS("MAIN_MENU_OPTIONS", 21, MenuType.MAIN_MENU),
	
	MAIN_MENU_TELEPORTS("MAIN_MENU_TELEPORTS", 23, MenuType.MAIN_MENU),
	
	MAIN_MENU_ACHIVEMENTS("MAIN_MENU_ACHIVEMENTS", 25, MenuType.MAIN_MENU),
	
	MAIN_MENU_PARTICLE("MAIN_MENU_PARTICLE", 29, MenuType.MAIN_MENU),
	
	MAIN_MENU_CHARACTER("MAIN_MENU_CHARACTER", 33, MenuType.MAIN_MENU),
	
	OPTION_MENU_SEE_PLAYERS_ICON("OPTION_MENU_SEE_PLAYERS_ICON", 19, MenuType.OPTION_MENU)
	,
	OPTION_MENU_SEE_PLAYERS_TOGGLE("OPTION_MENU_SEE_PLAYERS_TOGGLE", 28, MenuType.OPTION_MENU),
	
	OPTION_MENU_LETTER_ICON("OPTION_MENU_LETTER_ICON", 21, MenuType.OPTION_MENU),
	
	OPTION_MENU_LETTER_TOGGLE("OPTION_MENU_LETTER_TOGGLE", 30, MenuType.OPTION_MENU),
	
	OPTION_MENU_FRIENDS_ICON("OPTION_MENU_FRIENDS_ICON", 23, MenuType.OPTION_MENU),
	
	OPTION_MENU_FRIENDS_TOGGLE("OPTION_MENU_FRIENDS_TOGGLE", 32, MenuType.OPTION_MENU),
	
	OPTION_MENU_ACHIEVEMENT_ICON("OPTION_MENU_ACHIEVEMENT_ICON", 25, MenuType.OPTION_MENU),
	
	OPTION_MENU_ACHIEVEMENTS_TOGGLE("OPTION_MENU_ACHIEVEMENT_TOGGLE", 34, MenuType.OPTION_MENU),
	
	OPTION_MENU_FIXED_ICON("OPTION_MENU_FIXED_ICON", 31, MenuType.OPTION_MENU),
	
	OPTION_MENU_FIXED_TOGGLE("OPTION_MENU_FIXED_TOGGLE", 40, MenuType.OPTION_MENU),
	
	OPTION_MENU_BACK("OPTION_MENU_BACK", 49, MenuType.OPTION_MENU),
	
	ACHIEVEMENTS_BACK("ACHIEVEMENTS_BACK", 49, MenuType.ACHIEVEMENTS_MENU),
	
	TELEPORT_HOME("TELEPORT_HOME", 19, MenuType.TELEPORT_MENU),
	
	TELEPORT_MINES("TELEPORT_MINES", 21, MenuType.TELEPORT_MENU),
	
	TELEPORT_CELLS("TELEPORT_CELLS", 23, MenuType.TELEPORT_MENU),
	
	TELEPORT_PVP("TELEPORT_PVP", 25, MenuType.TELEPORT_MENU),
	
	TELEPORT_ALTAR("TELEPORT_ALTAR", 31, MenuType.TELEPORT_MENU),
	
	TELEPORT_BACK("TELEPORT_BACK", 49, MenuType.TELEPORT_MENU),
	
	PARTICLE_BACK("PARTICLE_BACK", 48, MenuType.PARTICLE_MENU),
	
	PARTICLE_NONE("PARTICLE_NONE", 49, MenuType.PARTICLE_MENU),
		
	PARTICLE_SEASONAL("PARTICLE_SEASONAL", 32, MenuType.PARTICLE_MENU),
	
	PARTICLE_HELL("PARTICLE_HELL", 31, MenuType.PARTICLE_MENU),
	
	PARTICLE_HEART("PARTICLE_HEART", 19, MenuType.PARTICLE_MENU),
	
	PARTICLE_HALO("PARTICLE_HALO", 24, MenuType.PARTICLE_MENU),
	
	PARTICLE_NOTE("PARTICLE_NOTE", 20, MenuType.PARTICLE_MENU),
	
	PARTICLE_FIRE("PARTICLE_FIRE", 21, MenuType.PARTICLE_MENU),
	
	PARTICLE_GOLD("PARTICLE_GOLD", 22, MenuType.PARTICLE_MENU),
	
	PARTICLE_DIAMOND("PARTICLE_DIAMOND", 23, MenuType.PARTICLE_MENU),
	
	PARTICLE_THUNDER("PARTICLE_THUNDER", 30, MenuType.PARTICLE_MENU),
	
	PARICLE_WITCH("PARTICLE_WITCH", 25, MenuType.PARTICLE_MENU),
	
	MONTH_VIP("MONTH_VIP", 11, MenuType.MONTH),
	
	MONTH_ELITE("MONTH_ELITE", 13, MenuType.MONTH),
	
	MONTH_ULTRA("MONTH_ULTRA", 15, MenuType.MONTH),
	
	
	
	CRAFTER_PICK("CRAFTER_PICK", 14, MenuType.CRAFTER),
	
	CRAFTER_COST("CRAFTER_COST", 12, MenuType.CRAFTER),
	
	CRAFTER_EFF_DOWN("CRAFTER_EFF_DOWN", 28, MenuType.CRAFTER),
	
	CRAFTER_EFF_SHOW("CRAFTER_EFF_SHOW", 29, MenuType.CRAFTER),
	
	CRAFTER_EFF_UP("CRAFTER_EFF_UP", 30, MenuType.CRAFTER),
	
	CRAFTER_FOR_DOWN("CRAFTER_FOR_DOWN", 37, MenuType.CRAFTER),
	
	CRAFTER_FOR_SHOW("CRAFTER_FOR_SHOW", 38, MenuType.CRAFTER),
	
	CRAFTER_FOR_UP("CRAFTER_FOR_UP", 39, MenuType.CRAFTER),
	
	CRAFTER_UNB_DOWN("CRAFTER_UNB_DOWN", 46, MenuType.CRAFTER),
	
	CRAFTER_UNB_SHOW("CRAFTER_UNB_SHOW", 47, MenuType.CRAFTER),
	
	CRAFTER_UNB_UP("CRAFTER_UNB_UP", 48, MenuType.CRAFTER),
	
	CRAFTER_EXT_DOWN("CRAFTER_EXT_DOWN", 32, MenuType.CRAFTER),
	
	CRAFTER_EXT_SHOW("CRAFTER_EXT_SHOW", 33, MenuType.CRAFTER),
	
	CRAFTER_EXT_UP("CRAFTER_EXT_UP", 34, MenuType.CRAFTER),
	
	CRAFTER_ANC_DOWN("CRAFTER_ANC_DOWN", 41, MenuType.CRAFTER),
	
	CRAFTER_ANC_SHOW("CRAFTER_ANC_SHOW", 42, MenuType.CRAFTER),
	
	CRAFTER_ANC_UP("CRAFTER_ANC_UP", 43, MenuType.CRAFTER),
	
	CRAFTER_SPE_DOWN("CRAFTER_SPE_DOWN", 50, MenuType.CRAFTER),
	
	CRAFTER_SPE_SHOW("CRAFTER_SPE_SHOW", 51, MenuType.CRAFTER),
	
	CRAFTER_SPE_UP("CRAFTER_SPE_UP", 52, MenuType.CRAFTER),
	;
	
	
	private final String name;
	private final int i;
	private final MenuType menutype;
	
	private MenuItem(String name, int i, MenuType m){
		this.name = name;
		this.i = i;
		this.menutype = m;
	}
	
	public void wasClicked(Player p){
		switch(name){
		case "CRAFTER_EFF_UP":{
			int current = CrafterMenu.tools.get(p.getName()).getEfficiency();
			if (current == 30){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), current + 1, curstats.getFortune(), curstats.getUnbreaking());
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_EFF_DOWN":{
			int current = CrafterMenu.tools.get(p.getName()).getEfficiency();
			if (current == 0){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), current - 1, curstats.getFortune(), curstats.getUnbreaking());
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_FOR_UP":{
			int current = CrafterMenu.tools.get(p.getName()).getFortune();
			if (current == 18){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), current + 1, curstats.getUnbreaking());
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_FOR_DOWN":{
			int current = CrafterMenu.tools.get(p.getName()).getFortune();
			if (current == 0){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), current - 1, curstats.getUnbreaking());
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_UNB_UP":{
			int current = CrafterMenu.tools.get(p.getName()).getUnbreaking();
			if (current == 15){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), current + 1);
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}break;
		case "CRAFTER_UNB_DOWN":{
			int current = CrafterMenu.tools.get(p.getName()).getUnbreaking();
			if (current == 0){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), current - 1);
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_ANC_UP":{
			double current = CrafterMenu.tools.get(p.getName()).getAncientChance();
			if (current >= 1.00){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), (((current * 10) + 1) /10), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());
				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_ANC_DOWN":{
			double current = CrafterMenu.tools.get(p.getName()).getAncientChance();
			if (current <= 0.00){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), (((current * 10) - 1) /10), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_EXT_UP":{
			double current = CrafterMenu.tools.get(p.getName()).getExtraDrops();
			if (current >= 0.80){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats((((current * 10) + 1) /10) ,curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_EXT_DOWN":{
			double current = CrafterMenu.tools.get(p.getName()).getExtraDrops();
			if (current <= 0.00){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats((((current * 10) - 1) /10) ,curstats.getAncientChance(), 0, (int) curstats.getSpeed(), curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_SPE_UP":{
			int current = (int) CrafterMenu.tools.get(p.getName()).getSpeed();
			if (current == 5){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, current + 1, curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_SPE_DOWN":{
			int current = (int) CrafterMenu.tools.get(p.getName()).getSpeed();
			if (current == 0){
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}else{
				ToolStats curstats = CrafterMenu.tools.get(p.getName());
				CrafterMenu.tools.remove(p.getName());
				ToolStats stats = new ToolStats(curstats.getExtraDrops(), curstats.getAncientChance(), 0, current - 1, curstats.getEfficiency(), curstats.getFortune(), curstats.getUnbreaking());				CrafterMenu.tools.put(p.getName(), stats);
				p.playSound(p.getLocation(), Sound.CLICK, 1f, 1f);
				CrafterMenu.open(p, p.getOpenInventory().getTopInventory());
			}
		}
		break;
		case "CRAFTER_COST":{
			ToolStats stats = CrafterMenu.tools.get(p.getName());
			int money = CrafterMenu.money.get(p.getName());
			int xp = CrafterMenu.xp.get(p.getName());
			int shards = CrafterMenu.shards.get(p.getName());
			if (money > 0 || xp > 0 || shards > 0){
				if (MoneyAPI.getMoney(p) >= money && Stats.getStats(p.getUniqueId()).getShards()>= shards && p.getLevel() >= xp){
					ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
					ItemMeta itemm = item.getItemMeta();
					itemm.setDisplayName("§d§lCustom Pick");
					List<String> lore = new ArrayList<String>();
					lore.add("§9§lStatistics:");
					lore.add(ChatColor.GRAY + " Efficiency: " + ChatColor.GREEN + stats.getEfficiency());
					lore.add(ChatColor.GRAY + " Fortune: " + ChatColor.GREEN + "" + stats.getFortune());
					lore.add(ChatColor.GRAY + " Unbreaking: " + ChatColor.GREEN + stats.getUnbreaking());
					lore.add(" ");
					lore.add(ChatColor.GRAY + " Extra Drops: " + ChatColor.RED + stats.getExtraDrops() + "0%");
					lore.add(ChatColor.GRAY + " Ancient Chance: " + ChatColor.RED + stats.getAncientChance() + "0%");
					lore.add(ChatColor.GRAY + " Speed: " + ChatColor.YELLOW + "+" + String.valueOf((int)stats.getSpeed()));
					lore.add(ChatColor.GREEN + " Enchants Left: " + ChatColor.YELLOW + "0");
					itemm.setLore(lore);
					itemm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					item.setItemMeta(itemm);
					item.addUnsafeEnchantment(Enchantment.DIG_SPEED, stats.getEfficiency());
					item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, stats.getFortune());
					item.addUnsafeEnchantment(Enchantment.DURABILITY, stats.getUnbreaking());
					p.getInventory().addItem(item);
					p.updateInventory();
					MoneyAPI.removeMoney(p, money);
					p.setLevel(p.getLevel() - xp);
					Stats.getStats(p.getUniqueId()).removeShards(shards);
					p.sendMessage(CrafterMenu.tag + ChatColor.GREEN + "Sucessfully crafted your pickaxe!");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					p.closeInventory();
					if (!CrafterMenu.diamonds){
						CrafterMenu.diamonds = true;
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
							public void run(){
								CrafterMenu.diamonds = false;
							}
						}, 40l);
					}
				}else{
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				}
			}else{
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
			}
		}
		break;
		case "MAIN_MENU_FAVOR_BOOK":{
			Main.Menu.remove(p.getName());
			QuestMenu.openMenu(p);
		}
		break;
		case "MAIN_MENU_OPTIONS":{
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_BACK":{
			MainMenu.open(p);
		}
		break;
		case "ACHIEVEMENTS_BACK":{
			MainMenu.open(p);
		}
		break;
		case "MAIN_MENU_ACHIVEMENTS":{
			Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.BLUE + "Achievements");
			inv.setItem(49, MenuItem.OPTION_MENU_BACK.getItemFor(p));
			p.openInventory(AchievementMenu.getInventoryFor(inv, p));
			if (Main.Menu.containsKey(p.getName())){
				Main.Menu.remove(p.getName());
			}
			Main.Menu.put(p.getName(), MenuType.ACHIEVEMENTS_MENU);
		}
		break;
		case "MAIN_MENU_TELEPORTS":{
			TeleportMenu.open(p);
		}
		break;
		case "MAIN_MENU_PARTICLE":{
			ParticleMenu.open(p);
		}
		break;
		case "OPTION_MENU_SEE_PLAYERS_ICON":{
			OptionAPI.reverse(OptionType.VISIBILITY, p);
			OptionsMenu.open(p);
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				for (Player p1 : Bukkit.getOnlinePlayers()){
					p.showPlayer(p1);
				}
			}else{
			for (Player p1 : Bukkit.getOnlinePlayers()){
				if (!FriendAPI.getFriendList(p.getName()).contains(p1.getName())){
					p.hidePlayer(p1);
				}
				if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
					if (!FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
					p1.hidePlayer(p);
					}
				}
			}
			}
		}
		break;
		case "OPTION_MENU_SEE_PLAYERS_TOGGLE":{
			OptionAPI.reverse(OptionType.VISIBILITY, p);
			OptionsMenu.open(p);
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				for (Player p1 : Bukkit.getOnlinePlayers()){
					p.showPlayer(p1);
				}
			}else{
			for (Player p1 : Bukkit.getOnlinePlayers()){
				if (!FriendAPI.getFriendList(p.getName()).contains(p1.getName())){
					p.hidePlayer(p1);
				}
				if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
					if (!FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
					p1.hidePlayer(p);
					}
				}
			}
			}
		}
		break;
		case "OPTION_MENU_LETTER_ICON":{
			OptionAPI.reverse(OptionType.LETTER, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_LETTER_TOGGLE":{
			OptionAPI.reverse(OptionType.LETTER, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_FRIENDS_ICON":{
			OptionAPI.reverse(OptionType.FRIENDS, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_FRIENDS_TOGGLE":{
			OptionAPI.reverse(OptionType.FRIENDS, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_ACHIEVEMENT_ICON":{
			OptionAPI.reverse(OptionType.ACHIEVEMENTS, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_ACHIEVEMENT_TOGGLE":{
			OptionAPI.reverse(OptionType.ACHIEVEMENTS, p);
			OptionsMenu.open(p);
		}
		break;
		case "OPTION_MENU_FIXED_ICON":{
			if (p.hasPermission("Option.Fixed")){
			OptionAPI.reverse(OptionType.FIXED, p);
			OptionsMenu.open(p);
			}
		}
		break;
		case "OPTION_MENU_FIXED_TOGGLE":{
			if (p.hasPermission("Option.Fixed")){
			OptionAPI.reverse(OptionType.FIXED, p);
			OptionsMenu.open(p);
			}
		}
		break;
		case "MAIN_MENU_GADGETS":{
			GadgetMenu.open(p);
			Main.Menu.remove(p.getName());
		}
		break;
		case "TELEPORT_HOME":{
			p.closeInventory();
			if (p.getWorld().getName().equals("PVP")){
				if (p.getLocation().getBlockY() < 80){
					p.sendMessage(ChatColor.GREEN+ "First do " + ChatColor.AQUA + "/spawn" + ChatColor.GREEN + " to get to the pvp spawn");
					break;
				}
			}
			p.teleport(Main.getLocation("Spawn"));
		}
		break;
		case "TELEPORT_BACK":{
			MainMenu.open(p);
		}
		break;
		case "TELEPORT_MINES":{
			p.closeInventory();
			if (p.getWorld().getName().equals("PVP")){
				if (p.getLocation().getBlockY() < 80){
					p.sendMessage(ChatColor.GREEN+ "First do " + ChatColor.AQUA + "/spawn" + ChatColor.GREEN + " to get to the pvp spawn.");
					break;
				}
			}
			p.teleport(Main.getLocation("Mines"));
		}
		break;
		case "TELEPORT_CELLS":{
			p.closeInventory();
			if (p.getWorld().getName().equals("PVP")){
				if (p.getLocation().getBlockY() < 80){
					p.sendMessage(ChatColor.GREEN+ "First do " + ChatColor.AQUA + "/spawn" + ChatColor.GREEN + " to get to the pvp spawn.");
					break;
				}
			}
			p.teleport(Main.getLocation("Cells"));
		}
		break;
		case "TELEPORT_PVP":{
			p.closeInventory();
			if (p.getWorld().getName().equals("PVP")){
				if (p.getLocation().getBlockY() < 80){
					p.sendMessage(ChatColor.GREEN+ "First do " + ChatColor.AQUA + "/spawn" + ChatColor.GREEN + " to get to the pvp spawn.");
					break;
				}
			}
			p.teleport(Main.getLocation("PvPSpawn"));
		}
		break;
		case "TELEPORT_ALTAR":{
			p.closeInventory();
			if (p.getWorld().getName().equals("PVP")){
				if (p.getLocation().getBlockY() < 80){
					p.sendMessage(ChatColor.GREEN+ "First do " + ChatColor.AQUA + "/spawn" + ChatColor.GREEN + " to get to the pvp spawn.");
					break;
				}
			}
			p.teleport(Main.getLocation("Altarloc"));
		}
		break;
		case "PARTICLE_BACK":{
			MainMenu.open(p);
		}
		break;
		case "PARTICLE_NONE":{
			ParticleType.NONE.setActive(p);
			p.closeInventory();
			p.sendMessage(ChatColor.GREEN + "You no longer have an active particle effect.");
		}
		break;
		case "PARTICLE_SEASONAL":{
			if (ParticleType.SEASONAL.hasBought(p) || p.isOp()){
				p.sendMessage("§aYou now have the Seasonal Bond particle effect active.");
				ParticleType.SEASONAL.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cYou must unlock this particle effect by voting with §9/vote§c.");
			}
		}
		break;
		case "PARTICLE_HELL":{
			if (ParticleType.HELL.hasBought(p) || p.isOp()){
				p.sendMessage("§aYou now have the Hell's Charge particle effect active.");
				ParticleType.HELL.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cYou must unlock this particle effect by voting with §9/vote§c.");
			}
		}
		break;
		case "PARTICLE_NOTE":{
			if (RankType.getRank(p) != RankType.NONE){
				p.sendMessage("§aYou now have the Note particle effect active.");
				ParticleType.NOTE.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §3§lVIP §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_DIAMOND":{
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP && RankType.getRank(p) != RankType.ELITE){
				p.sendMessage("§aYou now have the Diamond effect active.");
				ParticleType.DIAMOND.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §6§lULTRA §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_GOLD":{
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP){
				p.sendMessage("§aYou now have the Gold effect active.");
				ParticleType.GOLD.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §a§lELITE §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_WITCH":{
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP && RankType.getRank(p) != RankType.ELITE){
				p.sendMessage("§aYou now have the Witch's Curse particle effect active.");
				ParticleType.WITCH.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §6§lULTRA §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_HEART":{
			if (RankType.getRank(p) != RankType.NONE){
				p.sendMessage("§aYou now have the Heart particle effect active.");
				ParticleType.HEART.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §3§lVIP §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_FIRE":{
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP){
				p.sendMessage("§aYou now have the Fire Crown particle effect active.");
				ParticleType.FIRE.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §a§lELITE §cand above can use this particle effect.");
			}
		}
		break;
		case "PARTICLE_THUNDER":{
			if (ParticleType.THUNDERSTORM.hasBought(p) || p.isOp()){
				p.sendMessage("§aYou now have the Thunderstorm particle effect active.");
				ParticleType.THUNDERSTORM.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cYou must unlock this particle effect by voting with §9/vote§c.");
			}
		}
		break;
		case "PARTICLE_HALO":{
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP && RankType.getRank(p) != RankType.ELITE){
				p.sendMessage("§aYou now have the Halo particle effect active.");
				ParticleType.HALO.setActive(p);
				p.closeInventory();
			}else{
				p.sendMessage("§cOnly §6§lULTRA §cand above can use this particle effect.");
			}
		}
		break;
		case "MONTH_VIP":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (RankType.getRank(p) != RankType.NONE){
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".VIP" + currentmonth)){
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				}else{
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					CrystalAPI.addCrystals(p, 2000);
					p.sendMessage("§aClaimed §3§lVIP §amonthly reward of 2000 crystals.");
					Files.getDataFile().set("Players." + p.getUniqueId() + ".VIP" + currentmonth, true);
					Files.saveDataFile();
				}
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".VIP" + (currentmonth - 1))){
					Files.getDataFile().set("Players." + p.getUniqueId() + ".VIP" + (currentmonth - 1), null);
					Files.saveDataFile();
				}
			}else{
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				p.sendMessage("§3§lVIP§c's gain 2000 crystals every month. To purchase Vip go to §bwww.ThePitMc.com§c.");
			}
		}
		break;
		case "MONTH_ELITE":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP){
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ELITE" + currentmonth)){
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				}else{
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					CrystalAPI.addCrystals(p, 2000);
					p.sendMessage("§aClaimed §a§lELITE §amonthly reward of 2000 crystals.");
					Files.getDataFile().set("Players." + p.getUniqueId() + ".ELITE" + currentmonth, true);
					Files.saveDataFile();
				}
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ELITE" + (currentmonth - 1))){
					Files.getDataFile().set("Players." + p.getUniqueId() + ".ELITE" + (currentmonth - 1), null);
					Files.saveDataFile();
				}
			}else{
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				p.sendMessage("§a§lELITE§c's gain 4000 crystals every month. To purchase Elite go to §bwww.ThePitMc.com§c.");
			}
		}
		break;
		case "MONTH_ULTRA":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (RankType.getRank(p) != RankType.NONE && RankType.getRank(p) != RankType.VIP && RankType.getRank(p) != RankType.ELITE){
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ULTRA" + currentmonth)){
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				}else{
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
					CrystalAPI.addCrystals(p, 2000);
					p.sendMessage("§aClaimed §6§lULTRA §amonthly reward of 2000 crystals.");
					Files.getDataFile().set("Players." + p.getUniqueId() + ".ULTRA" + currentmonth, true);
					Files.saveDataFile();
				}
				if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ULTRA" + (currentmonth - 1))){
					Files.getDataFile().set("Players." + p.getUniqueId() + ".ULTRA" + (currentmonth - 1), null);
					Files.saveDataFile();
				}
			}else{
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				p.sendMessage("§6§lULTRA§c's gain 6000 crystals every month. To purchase Ultra go to §bwww.ThePitMc.com§c.");
			}
		}
		}
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemFor(Player p){
		switch(name){
		case "CRAFTER_EFF_UP":{
			int stat = CrafterMenu.tools.get(p.getName()).getEfficiency();
			String display = "";
			if (stat == 30){
				display = "§a§l+1 >> §7(Max lvl)";
			}else{
				display = "§a§l+1 >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_EFF_SHOW":{
			int stat = CrafterMenu.tools.get(p.getName()).getEfficiency();
			String display = "§d§lEfficiency: §b§l" + stat;
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 30");
			ItemStack i = new ItemStack(Material.AIR);
			if (stat != 0){
				 i = new ItemStack(160, stat, (byte)11);
			}else{
				 i = new ItemStack(160, 1, (byte)11);
			}
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_EFF_DOWN":{
			String display = "§c§l<< -1";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_FOR_UP":{
			int stat = CrafterMenu.tools.get(p.getName()).getFortune();
			String display = "";
			if (stat == 18){
				display = "§a§l+1 >> §7(Max lvl)";
			}else{
				display = "§a§l+1 >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_FOR_SHOW":{
			int stat = CrafterMenu.tools.get(p.getName()).getFortune();
			String display = "§d§lFortune: §b§l" + stat;
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 18");
			ItemStack i = new ItemStack(Material.AIR);
			if (stat != 0){
				 i = new ItemStack(160, stat, (byte)11);
			}else{
				 i = new ItemStack(160, 1, (byte)11);
			}
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_FOR_DOWN":{
			String display = "§c§l<< -1";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_UNB_UP":{
			int stat = CrafterMenu.tools.get(p.getName()).getUnbreaking();
			String display = "";
			if (stat == 15){
				display = "§a§l+1 >> §7(Max lvl)";
			}else{
				display = "§a§l+1 >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_UNB_SHOW":{
			int stat = CrafterMenu.tools.get(p.getName()).getUnbreaking();
			String display = "§d§lUnbreaking: §b§l" + stat;
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 15");
			ItemStack i = new ItemStack(Material.AIR);
			if (stat != 0){
				 i = new ItemStack(160, stat, (byte)11);
			}else{
				 i = new ItemStack(160, 1, (byte)11);
			}
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_UNB_DOWN":{
			String display = "§c§l<< -1";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_SPE_UP":{
			int stat = (int) CrafterMenu.tools.get(p.getName()).getSpeed();
			String display = "";
			if (stat == 5){
				display = "§a§l+1 >> §7(Max lvl)";
			}else{
				display = "§a§l+1 >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_SPE_SHOW":{
			int stat = (int) CrafterMenu.tools.get(p.getName()).getSpeed();
			String display = "§d§lSpeed: §b§l" + stat;
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 5");
			ItemStack i = new ItemStack(Material.AIR);
			if (stat != 0){
				 i = new ItemStack(160, stat, (byte)11);
			}else{
				 i = new ItemStack(160, 1, (byte)11);
			}
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_SPE_DOWN":{
			String display = "§c§l<< -1";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_ANC_UP":{
			double stat = CrafterMenu.tools.get(p.getName()).getAncientChance();
			String display = "";
			if (stat == 1.00){
				display = "§a§l+0.10% >> §7(Max percent)";
			}else{
				display = "§a§l+0.10% >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_ANC_SHOW":{
			double stat = CrafterMenu.tools.get(p.getName()).getAncientChance();
			String display = "§d§lAncient Chance: §b§l" + stat + "%";
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 1.00%");
			ItemStack i = new ItemStack(160, 1, (byte)11);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_ANC_DOWN":{
			String display = "§c§l<< -0.10%";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_EXT_UP":{
			double stat = CrafterMenu.tools.get(p.getName()).getExtraDrops();
			String display = "";
			if (stat == 0.80){
				display = "§a§l+0.10% >> §7(Max percent)";
			}else{
				display = "§a§l+0.10% >>";
			}
			ItemStack i = new ItemStack(159, 1, (byte)5);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_EXT_SHOW":{
			double stat = CrafterMenu.tools.get(p.getName()).getExtraDrops();
			String display = "§d§lExtra Drops: §b§l" + stat + "%";
			List<String> lore = new ArrayList<>();
			lore.add("§7Max is 0.80%");
			ItemStack i = new ItemStack(160, 1, (byte)11);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_EXT_DOWN":{
			String display = "§c§l<< -0.10%";
			ItemStack i = new ItemStack(159, 1, (byte)14);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			i.setItemMeta(im);
			return i;
		}
		case "CRAFTER_PICK":{
			ToolStats stats = CrafterMenu.tools.get(p.getName());
			ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName("§d§lCustom Pick");
			List<String> lore = new ArrayList<String>();
			lore.add("§9§lStatistics:");
			lore.add(ChatColor.GRAY + " Efficiency: " + ChatColor.GREEN + stats.getEfficiency());
			lore.add(ChatColor.GRAY + " Fortune: " + ChatColor.GREEN + "" + stats.getFortune());
			lore.add(ChatColor.GRAY + " Unbreaking: " + ChatColor.GREEN + stats.getUnbreaking());
			lore.add(" ");
			lore.add(ChatColor.GRAY + " Extra Drops: " + ChatColor.RED + String.valueOf(stats.getExtraDrops()) + "0%");
			lore.add(ChatColor.GRAY + " Ancient Chance: " + ChatColor.RED + stats.getAncientChance() + "0%");
			lore.add(ChatColor.GRAY + " Speed: " + ChatColor.YELLOW + "+" + String.valueOf((int) stats.getSpeed()));
			lore.add(ChatColor.GREEN + " Enchants Left: " + ChatColor.YELLOW + "0");
			itemm.setLore(lore);
			itemm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(itemm);
			item.addUnsafeEnchantment(Enchantment.DIG_SPEED, stats.getEfficiency());
			item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, stats.getFortune());
			item.addUnsafeEnchantment(Enchantment.DURABILITY, stats.getUnbreaking());
			return item;
		}
		case "CRAFTER_COST":{
			ToolStats stats = CrafterMenu.tools.get(p.getName());
			int eff = stats.getEfficiency();
			int fort = stats.getFortune();
			int unb = stats.getUnbreaking();
			double anc = stats.getAncientChance();
			double ext = stats.getExtraDrops();
			int speed = (int) stats.getSpeed();
			
			int money = 0;
			int shards = 0;
			int xp = 0;
			
			xp += 15 * eff; shards += 45 * eff; money += 14000 * eff;
			xp += 14 * fort; shards += 30 * fort; money += 12000 * fort;
			xp += 13 * unb; shards += 30 * unb; money += 12000 * unb;
			xp += 12 * speed; shards += 40 * speed; money += 10000 * speed;
			xp += (anc) * 105; shards += (anc) * 120; money += anc * 4000;
			xp += (ext) * 105; shards += (ext) * 120; money += ext * 4000;
			
			CrafterMenu.money.put(p.getName(), money);
			CrafterMenu.shards.put(p.getName(), shards);
			CrafterMenu.xp.put(p.getName(), xp);
			
			String display = "§d§lCost for your custom pick:";
			ItemStack i = new ItemStack(Material.AIR);
			List<String> lore = new ArrayList<>();
			lore.add("");
			if (MoneyAPI.getMoney(p) >= money){
				lore.add("§aMoney: " + money + "$");
			}else{
				lore.add("§aMoney: " + money + "$ §c(Not enough money)");	
			}
			if (Stats.getStats(p.getUniqueId()).getShards() >= shards){
				lore.add("§9Shards: " + shards);
			}else{
				lore.add("§9Shards: " + shards + " §c(Not enough shards)");	
			}
			if (p.getLevel() >= xp){
				lore.add("§eXP Levels: " + xp);
			}else{
				lore.add("§eXP Levels: " + xp + " §c(Not enough levels)");	
			}
			lore.add("");
			if (MoneyAPI.getMoney(p) >= money && Stats.getStats(p.getUniqueId()).getShards() >= shards && p.getLevel() >= xp){
				i = new ItemStack(159, 1, (byte)13);
				lore.add("§aClick you craft custom pickaxe.");
			}else{
				i = new ItemStack(159, 1, (byte)14);
				lore.add("§cThis pickaxe is too expensive for you.");
			}		
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(display);
			im.setLore(lore);
			i.setItemMeta(im);
			return i;
		}
		case "MAIN_MENU_FAVOR_BOOK":{
			List<String> lore = new ArrayList<>();
			lore.add("§7Click to see all your available");
			lore.add("§7and active favors.");
			return ItemAPI.getItem(Material.BOOK, ChatColor.GREEN + "Favor Book", lore);
		}
		case "MONTH_VIP":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (Files.getDataFile().getInt("CurrentMonth") != currentmonth){
				Files.getDataFile().set("CurrentMonth", currentmonth);
				Files.saveDataFile();
			}
			
			boolean hasclaimed = false;
			if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".VIP" + currentmonth)){
				hasclaimed = true;
			}
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to claim Vip's §b2000 §7monthly crystals.");
			lore.add("");
			if (hasclaimed){
				lore.add("§cYou already claimed this month's reward.");
			}
			ItemStack item = new ItemStack(Material.AIR);
			if (hasclaimed){
				item = new ItemStack(159, 1, (byte)14);
			}else{
				item = new ItemStack(159, 1, (byte)5);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName("§aVIP monthly reward.");
			itemm.setLore(lore);
			item.setItemMeta(itemm);
			return item;
		}
		case "MONTH_ELITE":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (Files.getDataFile().getInt("CurrentMonth") != currentmonth){
				Files.getDataFile().set("CurrentMonth", currentmonth);
				Files.saveDataFile();
			}
			
			boolean hasclaimed = false;
			if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ELITE" + currentmonth)){
				hasclaimed = true;
			}
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to claim Elite's §b2000 §7monthly crystals.");
			lore.add("");
			if (hasclaimed){
				lore.add("§cYou already claimed this month's reward.");
			}
			ItemStack item = new ItemStack(Material.AIR);
			if (hasclaimed){
				item = new ItemStack(159, 1, (byte)14);
			}else{
				item = new ItemStack(159, 1, (byte)5);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName("§aELITE monthly reward.");
			itemm.setLore(lore);
			item.setItemMeta(itemm);
			return item;
		}
		case "MONTH_ULTRA":{
			Calendar c = Calendar.getInstance();
			int currentmonth = c.get(Calendar.MONTH);
			if (Files.getDataFile().getInt("CurrentMonth") != currentmonth){
				Files.getDataFile().set("CurrentMonth", currentmonth);
				Files.saveDataFile();
			}
			
			boolean hasclaimed = false;
			if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ULTRA" + currentmonth)){
				hasclaimed = true;
			}
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to claim Ultra's §b2000 §7monthly crystals.");
			lore.add("");
			if (hasclaimed){
				lore.add("§cYou already claimed this month's reward.");
			}
			ItemStack item = new ItemStack(Material.AIR);
			if (hasclaimed){
				item = new ItemStack(159, 1, (byte)14);
			}else{
				item = new ItemStack(159, 1, (byte)5);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName("§aULTRA monthly reward.");
			itemm.setLore(lore);
			item.setItemMeta(itemm);
			return item;
		}
		case "MAIN_MENU_CHARACTER":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "All your personal info:");
			lore.add("     ");
			lore.add(ChatColor.GRAY + "Name: " + RankType.getPlayerColor(RankType.getRank(p)) + p.getName());
			lore.add(ChatColor.GRAY + "Cell Block: " + LetterType.getColoredString(LetterType.getPlayerLetter(p)));
			lore.add(ChatColor.GRAY + "Rank: " + RankType.toNiceName(RankType.getRank(p)));
			lore.add("");
			lore.add(ChatColor.GRAY + "Money: " + ChatColor.GREEN + MoneyAPI.getMoney(p));
			lore.add(ChatColor.GRAY + "Crystals: " + ChatColor.AQUA + CrystalAPI.getCrystals(p));
			lore.add(ChatColor.GRAY + "Pickaxe shards: §9" + Stats.getStats(p.getName()).getShards());
			lore.add(ChatColor.GRAY + "Favor points: §e" + QuestAPI.getFavor(p));
			lore.add("");
			lore.add(ChatColor.GRAY + "Traits:");
			lore.add("§9Speed    " + SpeedTrait.getProgress(p) + "§9 lvl " + SpeedTrait.getLevel(p));
			lore.add("§eIntellect " + SmartTrait.getProgress(p) + " §elvl " + SmartTrait.getLevel(p));
			ItemStack item = new ItemStack(397, 1, (short) 3);
			SkullMeta itemm = (SkullMeta) item.getItemMeta();
			itemm.setOwner(p.getName());
			itemm.setLore(lore);
			itemm.setDisplayName(ChatColor.GREEN + "Personal Information");
			item.setItemMeta(itemm);
			return item;
		}
		case "MAIN_MENU_OPTIONS":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to view a list of");
			lore.add(ChatColor.GRAY + "game options.");
			return ItemAPI.getItem(Material.REDSTONE_COMPARATOR, ChatColor.GREEN + "Options", lore);
		}
		case "MAIN_MENU_GADGETS":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to view a list of");
			lore.add(ChatColor.GRAY + "gadgets to equip.");
			return ItemAPI.getItem(Material.MINECART, ChatColor.GREEN + "Gadgets", lore);
		}
		case "MAIN_MENU_PARTICLE":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to view a list of");
			lore.add(ChatColor.GRAY + "particle effects to make you look");
			lore.add(ChatColor.GRAY + "sexy and nice!");
			return ItemAPI.getItem(Material.BLAZE_POWDER, ChatColor.GREEN + "Particle Effects", lore);
		}
		case "MAIN_MENU_TELEPORTS":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Right click to view a list of");
			lore.add(ChatColor.GRAY + "places you can teleport to.");
			return ItemAPI.getItem(Material.ENDER_PEARL, ChatColor.GREEN + "Teleports", lore);
		}
		case "MAIN_MENU_ACHIVEMENTS":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to see a list of completed");
			lore.add(ChatColor.GRAY + "and uncompleted achievements.");
			return ItemAPI.getItem(Material.DIAMOND, ChatColor.GREEN + "Achievements", lore);
		}
		case "OPTION_MENU_SEE_PLAYERS_ICON":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle other player visibility,");
			lore.add(ChatColor.GRAY + "you will still see your friends and");
			lore.add(ChatColor.GRAY + "gang members.");
			ItemStack item = new ItemStack(373, 1, (short) 16430);
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Player Visibility ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Player Visibility ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_SEE_PLAYERS_TOGGLE":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle other player visibility,");
			lore.add(ChatColor.GRAY + "you will still see your friends and");
			lore.add(ChatColor.GRAY + "gang members.");
			ItemStack item = new ItemStack(Material.AIR);
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				item = new ItemStack(351, 1, (short) 10);
			}else{
				item = new ItemStack(351, 1, (short) 8);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Player Visibility ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Player Visibility ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_LETTER_ICON":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether you see cell");
			lore.add(ChatColor.GRAY + "block letter in chat");
			ItemStack item = new ItemStack(Material.SIGN);
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Cell Block Visibility ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Cell Block Visibility ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_FIXED_TOGGLE":{
			if (!p.hasPermission("Option.Fix")){
				return new ItemStack(Material.AIR);
			}else{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.FIXED, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle wether you will be moved,");
			lore.add(ChatColor.GRAY + "by gagdets.");
			ItemStack item = new ItemStack(Material.AIR);
			if (OptionAPI.isEnabled(OptionType.FIXED, p.getName())){
				item = new ItemStack(351, 1, (short) 10);
			}else{
				item = new ItemStack(351, 1, (short) 8);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.FIXED, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Fixed ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Fixed ✘");
			}
			item.setItemMeta(itemm);
			return item;
			}
		}
		case "OPTION_MENU_FIXED_ICON":{
			if (!p.hasPermission("Option.Fix")){
				return new ItemStack(Material.AIR);
			}else{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.FIXED, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle wether you will be moved,");
			lore.add(ChatColor.GRAY + "by gagdets.");
			ItemStack item = new ItemStack(Material.IRON_PLATE);
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.FIXED, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Fixed ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Fixed ✘");
			}
			item.setItemMeta(itemm);
			return item;
			}
		}
		case "OPTION_MENU_LETTER_TOGGLE":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether you see cell");
			lore.add(ChatColor.GRAY + "block letter in chat");
			ItemStack item = new ItemStack(Material.AIR);
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
				item = new ItemStack(351, 1, (short) 10);
			}else{
				item = new ItemStack(351, 1, (short) 8);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Cell Block Visibility ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Cell Block Visibility ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_FRIENDS_ICON":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.FRIENDS, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether people are allowed");
			lore.add(ChatColor.GRAY + "to request you as a friend.");
			ItemStack item = new ItemStack(397, 1, (short) 3);
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.FRIENDS, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Friend Requests ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Friend Requests ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_FRIENDS_TOGGLE":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.FRIENDS, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether people are allowed");
			lore.add(ChatColor.GRAY + "to request you as a friend.");
			ItemStack item = new ItemStack(Material.AIR);
			if (OptionAPI.isEnabled(OptionType.FRIENDS, p.getName())){
				item = new ItemStack(351, 1, (short) 10);
			}else{
				item = new ItemStack(351, 1, (short) 8);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.FRIENDS, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Friend Requests ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Friend Requests ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_ACHIEVEMENT_ICON":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether you see achievement");
			lore.add(ChatColor.GRAY + "completed messages.");
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Achievement Messages ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Achievement Messages ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_ACHIEVEMENT_TOGGLE":{
			List<String> lore = new ArrayList<String>();
			if (OptionAPI.isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				lore.add(ChatColor.RED + "Click to disable");
			}else{
				lore.add(ChatColor.GREEN + "Click to enable");
			}
			lore.add(ChatColor.GRAY + "Toggle whether you see achievement");
			lore.add(ChatColor.GRAY + "completed messages.");
			ItemStack item = new ItemStack(Material.AIR);
			if (OptionAPI.isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				item = new ItemStack(351, 1, (short) 10);
			}else{
				item = new ItemStack(351, 1, (short) 8);
			}
			ItemMeta itemm = item.getItemMeta();
			itemm.setLore(lore);
			if (OptionAPI.isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				itemm.setDisplayName(ChatColor.GREEN + "✔ Achievement Messages ✔");
			}else{
				itemm.setDisplayName(ChatColor.RED + "✘ Achievement Messages ✘");
			}
			item.setItemMeta(itemm);
			return item;
		}
		case "OPTION_MENU_BACK":{
			return ItemAPI.getItem(Material.ARROW, "§8◄ Back", null);
		}
		case "TELEPORT_HOME":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + " ");
			lore.add(ChatColor.GRAY + "Click this to teleport back to the pit spawn.");
			return ItemAPI.getItem(Material.COMPASS, ChatColor.AQUA + "Teleport to Spawn", lore);
		}
		case "TELEPORT_BACK":{
			return ItemAPI.getItem(Material.ARROW, "§8◄ Back", null);
		}
		case "TELEPORT_MINES":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + " ");
			lore.add(ChatColor.GRAY + "Click this to teleport to the mines entrance.");
			lore.add(ChatColor.GRAY + "To get to specific mines, do " + ChatColor.GREEN + "/warp (letter)");
			return ItemAPI.getItem(Material.IRON_PICKAXE, ChatColor.AQUA + "Teleport to the Mines", lore);
		}
		case "TELEPORT_CELLS":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + " ");
			lore.add(ChatColor.GRAY + "Click this to teleport to the cells.");
			return ItemAPI.getItem(Material.IRON_FENCE, ChatColor.AQUA + "Teleport to the Cells", lore);
		}
		case "TELEPORT_PVP":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + " ");
			lore.add(ChatColor.GRAY + "Click this to teleport to PvP");
			return ItemAPI.getItem(Material.DIAMOND_SWORD, ChatColor.AQUA + "Teleport to PvP", lore);
		}
		case "TELEPORT_ALTAR":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + " ");
			lore.add(ChatColor.GRAY + "Click this to teleport to the voting altar.");
			return ItemAPI.getItem(Material.ENCHANTMENT_TABLE, ChatColor.AQUA + "Teleport to the Voting Altar", lore);
		}
		case "PARTICLE_BACK":{
			return ItemAPI.getItem(Material.ARROW, "§8◄ Back", null);
		}
		case "PARTICLE_NONE":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to reset your particle effect.");
			return ItemAPI.getItem(Material.GLASS, ChatColor.AQUA + "Reset Particle Effect", lore);
		}
		case "PARTICLE_SEASONAL":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate the Seasonal Bond particle effect.");
			lore.add(ChatColor.GRAY + "It looks like a helix of colors going with the");
			lore.add(ChatColor.GRAY + "current season.");
			lore.add("");
			if (!ParticleType.SEASONAL.hasBought(p)){
				lore.add("§9Unlockable in the voting altar.");
			}
			return ItemAPI.getItem(Material.PUMPKIN, "§3Seasonal Bond", lore);
		}
		case "PARTICLE_HALO":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate a Halo particle effect.");
			lore.add(ChatColor.GRAY + "It looks like a halo made of yellow.");
			lore.add("");
			lore.add("§6§lULTRA §6exclusive");
			return ItemAPI.getItem(Material.GLOWSTONE, "§3Halo", lore);
		}
		case "PARTICLE_HEART":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate a Heart particle effect.");
			lore.add(ChatColor.GRAY + "It looks like floating hearts.");
			lore.add("");
			lore.add("§3§lVIP §3exclusive");
			return ItemAPI.getItem(Material.GOLDEN_APPLE, "§3Hearts", lore);
		}
		case "PARTICLE_WITCH":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate the Witch's Curse particle effect.");
			lore.add(ChatColor.GRAY + "It looks like circle of purple stars.");
			lore.add("");
			lore.add("§6§lULTRA §6exclusive");
			return ItemAPI.getItem(Material.FERMENTED_SPIDER_EYE, "§3Witch's Curse", lore);
		}
		case "PARTICLE_NOTE":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate a Note particle effect.");
			lore.add(ChatColor.GRAY + "It looks like floating notes.");
			lore.add("");
			lore.add("§3§lVIP §3exclusive");
			return ItemAPI.getItem(Material.NOTE_BLOCK, "§3Notes", lore);
		}
		case "PARTICLE_FIRE":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate the Fire Crown particle effect.");
			lore.add(ChatColor.GRAY + "It looks like crown made of fire.");
			lore.add("");
			lore.add("§a§lELITE §aexclusive");
			return ItemAPI.getItem(Material.BLAZE_POWDER, "§3Fire Crown", lore);
		}
		case "PARTICLE_GOLD":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate a Gold Drops particle effect.");
			lore.add(ChatColor.GRAY + "It is gold items dropping from your head.");
			lore.add("");
			lore.add("§a§lELITE §aexclusive");
			return ItemAPI.getItem(Material.GOLD_INGOT, "§3Gold Drops", lore);
		}
		case "PARTICLE_DIAMOND":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate a Diamond Drops particle effect.");
			lore.add(ChatColor.GRAY + "It is diamond items dropping from your head.");
			lore.add("");
			lore.add("§6§lULTRA §6exclusive");
			return ItemAPI.getItem(Material.DIAMOND, "§3Diamond Drops", lore);
		}
		case "PARTICLE_THUNDER":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate the Thunderstorm particle effect.");
			lore.add(ChatColor.GRAY + "It looks like cloud and rain particles.");
			lore.add("");
			if (!ParticleType.THUNDERSTORM.hasBought(p)){
				lore.add("§9Unlockable in the voting altar.");
			}
			return ItemAPI.getItem(Material.WATER_BUCKET, "§3Thunderstorm", lore);
		}
		case "PARTICLE_HELL":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Click to activate the Hell's Charge particle effect.");
			lore.add(ChatColor.GRAY + "It looks red particles going up your body.");
			lore.add("");
			if (!ParticleType.HELL.hasBought(p)){
				lore.add("§9Unlockable in the voting altar.");
			}
			return ItemAPI.getItem(Material.NETHERRACK, "§3Hell's Charge", lore);
		}
		}
		return new ItemStack(Material.AIR);
	}
	
	public int getSlot(){
		return this.i;
	}
	public static MenuItem getItemFromSlot(int slot, MenuType menu){
		for (MenuItem m : values()){
			if (menu.equals(m.menutype)){
				if (slot == m.i){
					return m;
				}
			}
		}
		return MenuItem.MAIN_MENU_CHARACTER;
	}
	public static boolean wasAItem(int slot, MenuType menu){
		for (MenuItem m : values()){
			if (menu.equals(m.menutype)){
				if (slot == m.i){
					return true;
				}
			}
		}
		return false;
	}
	
	public static Inventory createMenu(Inventory inv, MenuType menu, Player p){
		for (MenuItem m : values()){
			if (m.menutype.equals(menu)){
				inv.setItem(m.getSlot(), m.getItemFor(p));
			}
		}
		return inv;
	}
	

}
