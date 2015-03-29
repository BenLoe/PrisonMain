package org.Prison.Main.Enchanter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.Prison.Main.Files;
import org.Prison.Main.Letter.LetterType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellMenu {

	public static HashMap<String, LetterType> inSellerMenu = new HashMap<String,LetterType>();
	public static HashMap<String, Inventory> inventory = new HashMap<String,Inventory>();
	@SuppressWarnings("deprecation")
	public static void open(Player p, LetterType t, Inventory inv){
		Cooldown.checkCooldown(p, t, 1);
		Cooldown.checkCooldown(p, t, 2);
		Cooldown.checkCooldown(p, t, 3);
		if (Files.config().contains("Sellers." + t.getName() + ".Pick.ID")){
			int id = Files.config().getInt("Sellers." + t.getName() + ".Pick.ID");
			ItemStack pick = new ItemStack(Material.getMaterial(id));
			for (String s : Files.config().getStringList("Sellers." + t.getName() + ".Pick.Enchantments")){
				int enchantid = Integer.parseInt(s.split(",")[0]);
				int enchantlevel = Integer.parseInt(s.split(",")[1]);
				pick.addEnchantment(Enchantment.getById(enchantid), enchantlevel);
			}
			ItemMeta meta = pick.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add("  ");
			lore.add(ChatColor.GRAY + "Click to purchase a specialized pickaxe");
			lore.add(ChatColor.GRAY + "for mining in the " + LetterType.getColoredString(t) + " §7mine.");
			lore.add(ChatColor.GRAY + "After purchasing, the item goes out of stock");
			lore.add(ChatColor.GRAY + "for " + Cooldown.getCooldown(t, 1) + ChatColor.GRAY + ".");
			lore.add("  ");
			if (Cooldown.hasCooldown(p, t, 1)){
				lore.add("§c§lOut of Stock");
				lore.add(ChatColor.GRAY + "New shipment coming in " + Cooldown.getTimeLeft(p, t, 1));
				meta.setDisplayName("§c§lBuy Specialized Pickaxe");
			}else{
				lore.add("§a§lIn Stock");
				lore.add("  ");
				meta.setDisplayName("§a§lBuy Specialized Pickaxe");
			}
			meta.setLore(lore);
			pick.setItemMeta(meta);
			inv.setItem(20, pick);
		}
		if (Files.config().contains("Sellers." + t.getName() + ".Axe.ID")){
			int id = Files.config().getInt("Sellers." + t.getName() + ".Axe.ID");
			ItemStack pick = new ItemStack(Material.getMaterial(id));
			for (String s : Files.config().getStringList("Sellers." + t.getName() + ".Axe.Enchantments")){
				int enchantid = Integer.parseInt(s.split(",")[0]);
				int enchantlevel = Integer.parseInt(s.split(",")[1]);
				pick.addEnchantment(Enchantment.getById(enchantid), enchantlevel);
			}
			ItemMeta meta = pick.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add("  ");
			lore.add(ChatColor.GRAY + "Click to purchase a specialized axe");
			lore.add(ChatColor.GRAY + "for mining in the " + LetterType.getColoredString(t) + " §7mine.");
			lore.add(ChatColor.GRAY + "After purchasing, the item goes out of stock");
			lore.add(ChatColor.GRAY + "for" + Cooldown.getCooldown(t, 2) + ChatColor.GRAY + ".");
			lore.add("  ");
			if (Cooldown.hasCooldown(p, t, 2)){
				lore.add("§c§lOut of Stock");
				lore.add(ChatColor.GRAY + "New shipment coming in " + Cooldown.getTimeLeft(p, t, 2));
				meta.setDisplayName("§c§lBuy Specialized Axe");
			}else{
				lore.add("§a§lIn Stock");
				lore.add("  ");
				meta.setDisplayName("§a§lBuy Specialized Axe");
			}
			meta.setLore(lore);
			pick.setItemMeta(meta);
			inv.setItem(22, pick);
		}
		if (Files.config().contains("Sellers." + t.getName() + ".Shovel.ID")){
			int id = Files.config().getInt("Sellers." + t.getName() + ".Shovel.ID");
			ItemStack pick = new ItemStack(Material.getMaterial(id));
			for (String s : Files.config().getStringList("Sellers." + t.getName() + ".Shovel.Enchantments")){
				int enchantid = Integer.parseInt(s.split(",")[0]);
				int enchantlevel = Integer.parseInt(s.split(",")[1]);
				pick.addEnchantment(Enchantment.getById(enchantid), enchantlevel);
			}
			ItemMeta meta = pick.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add("  ");
			lore.add(ChatColor.GRAY + "Click to purchase a specialized shovel");
			lore.add(ChatColor.GRAY + "for mining in the " + LetterType.getColoredString(t) + " §7mine.");
			lore.add(ChatColor.GRAY + "After purchasing, the item goes out of stock");
			lore.add(ChatColor.GRAY + "for" + Cooldown.getCooldown(t, 3) + ChatColor.GRAY + ".");
			lore.add("  ");
			if (Cooldown.hasCooldown(p, t, 3)){
				lore.add("§c§lOut of Stock");
				lore.add(ChatColor.GRAY + "New shipment coming in " + Cooldown.getTimeLeft(p, t, 3));
				meta.setDisplayName("§c§lBuy Specialized Shovel");
			}else{
				lore.add("§a§lIn Stock");
				lore.add("  ");
				meta.setDisplayName("§a§lBuy Specialized Shovel");
			}
			meta.setLore(lore);
			pick.setItemMeta(meta);
			inv.setItem(24, pick);
		}
		if (!inSellerMenu.containsKey(p.getName())){
		p.openInventory(inv);
		inSellerMenu.put(p.getName(), t);
		inventory.put(p.getName(), inv);
		}
	}
}