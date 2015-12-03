package org.Prison.Main.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.Prison.Tools.ToolStats;
public class CrafterMenu {
	public static HashMap<String,ToolStats> tools = new HashMap<>();
	public static HashMap<String,Integer> money = new HashMap<>();
	public static HashMap<String,Integer> xp = new HashMap<>();
	public static HashMap<String,Integer> shards = new HashMap<>();
	public static boolean diamonds = false;
	public static List<UUID> items = new ArrayList<>();
	public static String tag = "§9[§dCrafter§9]: ";
	
	public static void open(Player p, Inventory inv){
		boolean itemb = false;
		for (ItemStack item : p.getInventory().getContents()){
			if (item == null){
				itemb = true;
			}
		}
		if (itemb){
		ToolStats stats = new ToolStats(0.0, 0.0, 0, 0, 0, 0, 0);
		if (Main.Menu.containsKey(p.getName())){
			Main.Menu.remove(p.getName());
		}
		if (!tools.containsKey(p.getName())){
			money.put(p.getName(), 0);
			tools.put(p.getName(), stats);
			xp.put(p.getName(), 0);
			shards.put(p.getName(), 0);	
		}
		MenuItem.createMenu(inv, MenuType.CRAFTER, p);
		p.openInventory(inv);
		Main.Menu.put(p.getName(), MenuType.CRAFTER);
	}else{
		p.sendMessage(ChatColor.RED + "Must have 1 open inventory slot.");
	}
	}
	
	public static void check(){
		if (diamonds){
			Location loc = new Location(Bukkit.getWorld("PrisonMap"), -189.5, 58, 212.5);
			for (int in = 0; in < 3; in++){
			double x = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			double z = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			Random r = new Random();
			ItemStack i = new ItemStack(Material.AIR);
			switch(r.nextInt(5) + 1){
			case 1:
			case 2:
			case 3:
				i = new ItemStack(Material.DIAMOND);
				break;
			case 4:
			case 5:
				i = new ItemStack(Material.STICK);
				break;
			}
			ItemMeta im = i.getItemMeta();
			im.setDisplayName((r.nextInt(100)) + "");
			List<String> lore = new ArrayList<>();
			lore.add((r.nextInt(100)) + "");
			i.setItemMeta(im);
			Item item = Bukkit.getWorld("PrisonMap").dropItem(loc, i);
			item.setPickupDelay(10000);
			item.setVelocity(new Vector(x, 0.43, z));
			items.add(item.getUniqueId());
			}
			for (Player p : Bukkit.getOnlinePlayers()){
				if (p.getLocation().distance(loc) < 10){
					p.playSound(loc, Sound.ITEM_PICKUP, 1f, 1f);
				}
			}
		}
		if (!items.isEmpty()){
			for (Item item : Bukkit.getWorld("PrisonMap").getEntitiesByClass(Item.class)){
				if (items.contains(item.getUniqueId())){
					if (item.getTicksLived() > 20){
						items.remove(item.getUniqueId());
						item.remove();
					}
				}
			}
		}
	}
	
}
