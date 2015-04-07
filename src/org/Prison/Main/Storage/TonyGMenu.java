package org.Prison.Main.Storage;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Files;
import org.Prison.Main.Currency.MoneyAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TonyGMenu {

	public static List<String> inTonyMenu = new ArrayList<String>();
	
	public static void openMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 3 * 9, ChatColor.BLUE + "Tony G's Storage");
		List<String> lore = new ArrayList<String>();
		
		
		ItemStack chest1 = new ItemStack(Material.CHEST);
		ItemMeta chest1m = chest1.getItemMeta();
		lore.add(ChatColor.GRAY + "Click to buy access to storage chest 1.");
		lore.add("  ");
		lore.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "200,000$");
		if (TonyStorageAPI.hasAccessTo(p, 1)){
			lore.add(ChatColor.RED + "You have already bought this.");
		}else
		if (MoneyAPI.getMoney(p) < 200000){
			lore.add(ChatColor.RED + "Not enough money.");
		}
		chest1m.setLore(lore);
		chest1m.setDisplayName(ChatColor.AQUA + "Storage Chest 1");
		chest1.setItemMeta(chest1m);
		lore.clear();
		
		
		ItemStack chest2 = new ItemStack(Material.CHEST);
		ItemMeta chest2m = chest2.getItemMeta();
		lore.add(ChatColor.GRAY + "Click to buy access to storage chest 2.");
		lore.add("  ");
		lore.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "400,000$");
		if (TonyStorageAPI.hasAccessTo(p, 2)){
			lore.add(ChatColor.RED + "You have already bought this.");
		}else
		if (MoneyAPI.getMoney(p) < 400000){
			lore.add(ChatColor.RED + "Not enough money.");
		}
		chest2m.setLore(lore);
		chest2m.setDisplayName(ChatColor.AQUA + "Storage Chest 2");
		chest2.setItemMeta(chest2m);
		lore.clear();
		
		ItemStack chest3 = new ItemStack(Material.CHEST);
		ItemMeta chest3m = chest3.getItemMeta();
		lore.add(ChatColor.GRAY + "Click to buy access to storage chest 3.");
		lore.add("  ");
		lore.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "600,000$");
		if (TonyStorageAPI.hasAccessTo(p, 3)){
			lore.add(ChatColor.RED + "You have already bought this.");
		}else
		if (MoneyAPI.getMoney(p) < 600000){
			lore.add(ChatColor.RED + "Not enough money.");
		}
		chest3m.setLore(lore);
		chest3m.setDisplayName(ChatColor.AQUA + "Storage Chest 3");
		chest3.setItemMeta(chest3m);
		lore.clear();
		
		ItemStack chest4 = new ItemStack(Material.CHEST);
		ItemMeta chest4m = chest4.getItemMeta();
		lore.add(ChatColor.GRAY + "Click to buy access to storage chest 4.");
		lore.add("  ");
		lore.add(ChatColor.GRAY + "Cost: " + ChatColor.GREEN + "1,000,000$");
		if (TonyStorageAPI.hasAccessTo(p, 4)){
			lore.add(ChatColor.RED + "You have already bought this.");
		}else
		if (MoneyAPI.getMoney(p) < 1000000){
			lore.add(ChatColor.RED + "Not enough money.");
		}
		chest4m.setLore(lore);
		chest4m.setDisplayName(ChatColor.AQUA + "Storage Chest 4");
		chest4.setItemMeta(chest4m);
		lore.clear();
		
		inv.setItem(10, chest1);
		inv.setItem(12, chest2);
		inv.setItem(14, chest3);
		inv.setItem(16, chest4);
		
		inTonyMenu.add(p.getName());
		
		p.openInventory(inv);
	}
	
	public static void menuClick(Player p, int slot){
		int money = MoneyAPI.getMoney(p);
		if (slot == 10){
			if (money >= 200000 && !TonyStorageAPI.hasAccessTo(p, 1)){
				p.closeInventory();
				MoneyAPI.removeMoney(p, 200000);
				Files.getDataFile().set("Players." + p.getName() + ".Chest1", true);
				p.sendMessage(ChatColor.GREEN + "Bought access to storage chest 1.");
				Files.saveDataFile();
			}
		}
		
		if (slot == 12){
			if (money >= 400000 && !TonyStorageAPI.hasAccessTo(p, 2)){
				p.closeInventory();
				MoneyAPI.removeMoney(p, 400000);
				Files.getDataFile().set("Players." + p.getName() + ".Chest2", true);
				p.sendMessage(ChatColor.GREEN + "Bought access to storage chest 2.");
				Files.saveDataFile();
			}
		}
		
		if (slot == 14){
			if (money >= 600000 && !TonyStorageAPI.hasAccessTo(p, 3)){
				p.closeInventory();
				MoneyAPI.removeMoney(p, 600000);
				Files.getDataFile().set("Players." + p.getName() + ".Chest3", true);
				p.sendMessage(ChatColor.GREEN + "Bought access to storage chest 3.");
				Files.saveDataFile();
			}
		}
		
		if (slot == 16){
			if (money >= 1000000 && !TonyStorageAPI.hasAccessTo(p, 4)){
				p.closeInventory();
				MoneyAPI.removeMoney(p, 1000000);
				Files.getDataFile().set("Players." + p.getName() + ".Chest4", true);
				p.sendMessage(ChatColor.GREEN + "Bought access to storage chest 4.");
				Files.saveDataFile();
			}
		}
	}
	
}
