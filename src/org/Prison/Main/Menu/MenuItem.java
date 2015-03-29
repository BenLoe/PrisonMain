package org.Prison.Main.Menu;

import java.util.ArrayList;
import java.util.List;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.Gadgets.Menu.GadgetMenu;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.Main;
import org.Prison.Main.Currency.CrystalAPI;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.Traits.SmartTrait;
import org.Prison.Main.Traits.SpeedTrait;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public enum MenuItem {
	
	MAIN_MENU_GADGETS("MAIN_MENU_GADGETS", 19, MenuType.MAIN_MENU),
	
	MAIN_MENU_OPTIONS("MAIN_MENU_OPTIONS", 21, MenuType.MAIN_MENU),
	
	MAIN_MENU_TELEPORTS("MAIN_MENU_TELEPORTS", 23, MenuType.MAIN_MENU),
	
	MAIN_MENU_ACHIVEMENTS("MAIN_MENU_ACHIVEMENTS", 25, MenuType.MAIN_MENU),
	
	MAIN_MENU_CHARACTER("MAIN_MENU_CHARACTER", 40, MenuType.MAIN_MENU),
	
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
	
	TELEPORT_BACK("TELEPORT_BACK", 49, MenuType.TELEPORT_MENU);
	
	
	
	
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
			p.teleport(Main.getLocation("Altar"));
		}
		}
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getItemFor(Player p){
		switch(name){
		case "MAIN_MENU_CHARACTER":{
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "All your personal info:");
			lore.add("     ");
			lore.add(ChatColor.GRAY + "Name: " + RankType.getPlayerColor(RankType.getRank(p)) + p.getName());
			lore.add(ChatColor.GRAY + "Money: " + ChatColor.GREEN + MoneyAPI.getMoney(p));
			lore.add(ChatColor.GRAY + "Pickaxe shards: §9" + Stats.getStats(p.getName()).getShards());
			lore.add(ChatColor.GRAY + "Enchanted shards: §b" + Stats.getStats(p.getName()).getEnchantedShards());
			lore.add(ChatColor.GRAY + "Crystals: " + ChatColor.AQUA + CrystalAPI.getCrystals(p.getName()));
			lore.add(ChatColor.GRAY + "Cell Block: " + LetterType.getColoredString(LetterType.getPlayerLetter(p)));
			lore.add(ChatColor.GRAY + "Rank: " + RankType.toNiceName(RankType.getRank(p)));
			lore.add(ChatColor.GRAY + "Traits:");
			lore.add("§9Speed     " + SpeedTrait.getProgress(p) + "§9 lvl " + SpeedTrait.getLevel(p));
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
