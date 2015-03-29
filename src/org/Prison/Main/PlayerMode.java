package org.Prison.Main;


import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerMode {

	public static boolean isInPlayerMode(Player p){
		if (Files.config().getStringList("PlayerMode").contains(p.getName())){
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("deprecation")
	public static void setInPlayerMode(boolean b, Player p){
		if (b){
			if (!Files.config().getStringList("PlayerMode").contains(p.getName())){
				List<String> list = Files.config().getStringList("PlayerMode");
				list.add(p.getName());
				Files.config().set("PlayerMode", list);
				Files.saveConfig();
				p.getInventory().setItem(7, new ItemStack(Material.AIR));
				p.getInventory().setItem(8, new ItemStack(Material.AIR));
				p.updateInventory();
			}
		}else{
			if (Files.config().getStringList("PlayerMode").contains(p.getName())){
				List<String> list = Files.config().getStringList("PlayerMode");
				list.remove(p.getName());
				Files.config().set("PlayerMode", list);
				Files.saveConfig();
			}
		}
	}
}
