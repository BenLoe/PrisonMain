package org.Prison.Main.Enchanter;


import org.Prison.Main.Files;
import org.Prison.Main.Letter.LetterType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.citizensnpcs.api.event.NPCRightClickEvent;

public class ClickHandler {

	public static void checkClick(NPCRightClickEvent event){
		Player p = event.getClicker();
		for (LetterType l : LetterType.values()){
		if (Files.config().contains("Sellers.ID" + event.getNPC().getId())){
			if (Files.config().getString("Sellers.ID" + event.getNPC().getId()).equals(l.getName())){
			SellMenu.open(p, l, Bukkit.createInventory(null, 9 * 6, ChatColor.BLUE + "Buy Tools | " + LetterType.getColoredString(l)));
			break;
			}
		}
		}
	}
	
	public static void checkInventoryClick(InventoryClickEvent event){
		Player p = (Player) event.getWhoClicked();
		if (SellMenu.inSellerMenu.containsKey(p.getName())){
			LetterType t = SellMenu.inSellerMenu.get(p.getName());
			event.setCancelled(true);
			if (event.getRawSlot() == 20){
				if (!Cooldown.hasCooldown(p, t, 1)){
					giveItems(p, 1, t);
					SellMenu.open(p, t, event.getInventory());
				}
			}
			if (event.getRawSlot() == 22){
				if (!Cooldown.hasCooldown(p, t, 2)){
					giveItems(p, 2, t);
					SellMenu.open(p, t, event.getInventory());
				}
			}
			if (event.getRawSlot() == 24){
				if (!Cooldown.hasCooldown(p, t, 3)){
					giveItems(p, 3, t);
					SellMenu.open(p, t, event.getInventory());
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void giveItems(Player p, int id, LetterType t){
		String type = "";
		String name = "";
		switch(id){
		case 1:{
			type = "Pick";
			name = "Pickaxe";
		}
		break;
		case 2:{
			type = "Axe";
			name = "Axe";
		}
		break;
		case 3:{
			type = "Shovel";
			name = "Shovel";
		}
		}
		int idp = Files.config().getInt("Sellers." + t.getName() + "." + type + ".ID");
		ItemStack item = new ItemStack(Material.getMaterial(idp));
		for (String s : Files.config().getStringList("Sellers." + t.getName() + "." + type + ".Enchantments")){
			int enchantid = Integer.parseInt(s.split(",")[0]);
			int enchantlevel = Integer.parseInt(s.split(",")[1]);
			item.addEnchantment(Enchantment.getById(enchantid), enchantlevel);
		}
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName("§b§lSpecialized " + name);
		item.setItemMeta(itemm);
		p.getInventory().addItem(item);
		Cooldown.setCooldown(p, t, id);
	}
}
