package org.Prison.Main.Menu;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SantaPickMenu {

	public static void open(Player p){
		Inventory inv = Bukkit.createInventory(null, 9 * 4, "§4§lWish List");
		if (Main.Menu.containsKey(p.getName())){
			Main.Menu.remove(p.getName());
		}
		MenuItem.createMenu(inv, MenuType.SANTA_PICK, p);
		p.openInventory(inv);
		Main.Menu.put(p.getName(), MenuType.SANTA_PICK);
	}
	
	
}
