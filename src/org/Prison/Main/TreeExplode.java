package org.Prison.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.BenLoe.Gadgets.Types.SantaMorph;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class TreeExplode {

	public static boolean prep = false;
	public static boolean explode = false;
	
	public static void startPrep(){
		if (prep){
			prep = false;
		}else{
			prep = true;
		}
	}
	
	public static void explode(){
		if (explode){
			explode = false;
		}else{
			explode = true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void check(){
		if (prep){
			List<Vector> blocks = new ArrayList<>();
			blocks.add(new Vector(-383, 28, 116));
			blocks.add(new Vector(-384, 26, 115));
			blocks.add(new Vector(-386, 26, 118));
			blocks.add(new Vector(-385, 27, 119));
			blocks.add(new Vector(-383, 28, 120));
			blocks.add(new Vector(-382, 27, 120));
			blocks.add(new Vector(-380, 26, 118));
			blocks.add(new Vector(-383, 24, 114));
			blocks.add(new Vector(-381, 23, 115));
			blocks.add(new Vector(-387, 23, 117));
			blocks.add(new Vector(-385, 24, 120));
			for (Vector v : blocks){
				Block b = new Location(Bukkit.getWorld("PrisonMap"), v.getX(), v.getY(), v.getZ()).getBlock();
				b.setType(Material.WOOL);
				Random r = new Random();
				int type = r.nextInt(7) + 1;
				switch(type){
				case 1:{
					b.setData((byte) 4);
				}
				break;
				case 2:{
					b.setData((byte) 5);
				}
				break;
				case 3:{
					b.setData((byte) 14);
				}
				break;
				case 4:{
					b.setData((byte) 11);
				}
				break;
				case 5:{
					b.setData((byte) 1);
				}
				break;
				case 6:{
					b.setData((byte) 10);
				}
				break;
				case 7:{
					b.setData((byte) 9);
				}
				}
			}
		}
		
		if (explode){
			ItemStack present = SantaMorph.getRandomPresent();
			ItemMeta itemm = present.getItemMeta();
			itemm.setDisplayName("§b§lPresent §7(Right click altar)");
			present.setItemMeta(itemm);
			Item item = Bukkit.getWorld("PrisonMap").dropItem(new Location(Bukkit.getWorld("PrisonMap"), -382, 33, 118.5), present);
			double x = (-0.6 + (0.6 - -0.6) * new Random().nextDouble());
			double z = (-0.6 + (0.6 - -0.6) * new Random().nextDouble());
			item.setVelocity(new Vector(x, 0.2, z));
		}
	}
	
	
}
