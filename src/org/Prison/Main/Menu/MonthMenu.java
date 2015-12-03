package org.Prison.Main.Menu;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MonthMenu {
	public static void open(Player p){
		if (Main.Menu.containsKey(p.getName())){
			Main.Menu.remove(p.getName());
		}
		Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.BLUE + "Monthly Rewards");
		MenuItem.createMenu(inv, MenuType.MONTH, p);
		p.openInventory(inv);
		Main.Menu.put(p.getName(), MenuType.MONTH);
	}
}
