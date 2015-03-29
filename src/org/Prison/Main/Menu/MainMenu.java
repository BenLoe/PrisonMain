package org.Prison.Main.Menu;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainMenu {

	public static void open(Player p){
		Inventory inv = Bukkit.createInventory(null, 9 * 6, ChatColor.BLUE+ "Game Menu");
		if (Main.Menu.containsKey(p.getName())){
			Main.Menu.remove(p.getName());
		}
		MenuItem.createMenu(inv, MenuType.MAIN_MENU, p);
		p.openInventory(inv);
		Main.Menu.put(p.getName(), MenuType.MAIN_MENU);
	}
}
