package org.Prison.Main.Items;

import java.util.ArrayList;
import java.util.List;
import me.BenLoe.Gadgets.Types.*;
import org.Prison.Main.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemAPI {
	public static void givePlayerItems(Player p){
		Files.reloadConfig();
		p.getInventory().setItem(7, new ItemStack(Material.AIR));
		p.getInventory().setItem(8, new ItemStack(Material.AIR));
		p.updateInventory();
		List<String> lore1 = new ArrayList<String>();
		List<String> lore2 = new ArrayList<String>();
		lore1.add(ChatColor.GRAY + "Right click to activate your gadget.");
		lore1.add(ChatColor.GRAY + "Select your gadget in the game menu.");
		lore1.add(ChatColor.BLUE + "Not movable.");
		lore2.add(ChatColor.GRAY + "Right click to see a menu of");
		lore2.add(ChatColor.GRAY + "options and fun stuff.");
		lore2.add(ChatColor.BLUE + "Not movable.");
		String display = "";
		if (DeviceType.getDevice(p) != DeviceType.NONE){
			display = "§a" + DeviceType.getDevice(p).getNiceName() + " §e§l| " + DeviceType.getDevice(p).getAmount(p) + " Uses";
		}else{
			display = ChatColor.GREEN + "Gadget";
		}
		ItemStack gadget = getItem(DeviceType.getDevice(p).getItem(), display, lore1);
		ItemStack menu = getItem(Material.NETHER_STAR, ChatColor.GREEN + "Game Menu", lore2);
		p.getInventory().setItem(7, gadget);
		p.getInventory().setItem(8, menu);
		p.updateInventory();
	}
	public static ItemStack getItem(Material m, String DisplayName, List<String> Lore){
		ItemStack item = new ItemStack(m);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(DisplayName);
		itemm.setLore(Lore);
		item.setItemMeta(itemm);
		return item;
	}
}
