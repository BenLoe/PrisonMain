package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.BenLoe.SuperSpleef.ParticleEffect;
import me.BenLoe.quest.Main;

import org.Prison.Main.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GhastTerror {

	public static boolean active = false;
	public static List<UUID> ghasts = new ArrayList<UUID>();
	public static Location spawn = new Location(Bukkit.getWorld("NetherMap"), 23, 85, -17);
	public static HashMap<Location,Material> regen = new HashMap<Location,Material>();
	public static List<FallingBlock> fallings = new ArrayList<FallingBlock>();
	
	public static void startAttack(){
		active = true;
		for (int i = 1; i < 5; i++){
			Ghast ghast = (Ghast) Bukkit.getWorld("NetherMap").spawnEntity(spawn, EntityType.GHAST);
			ghasts.add(ghast.getUniqueId());
		}
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.getWorld().getName().equals("NetherMap")){
				p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 1f, 1f);
				p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 1f, 1f);
				p.playSound(p.getLocation(), Sound.GHAST_MOAN, 1f, 1f);
			}
		}
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				for (Ghast ghast : Bukkit.getWorld("NetherMap").getEntitiesByClass(Ghast.class)){
					ghasts.remove(ghast.getUniqueId());
					ghast.teleport(ghast.getLocation().subtract(0, 500, 0));
					
				}
				for (Player p : Bukkit.getOnlinePlayers()){
					if (p.getWorld().getName().equals("NetherMap")){
						p.playSound(p.getLocation(), Sound.GHAST_DEATH, 1f, 1f);
					}
				}
				active = false;
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
						regenMap();
					}
				}, 40);
			}
		}, 110 * 20);
	}
	
	public static void check(){
		if (active){
		for (Ghast ghast : Bukkit.getWorld("NetherMap").getEntitiesByClass(Ghast.class)){
			if (ghast.getLocation().getBlockY() < 65){
				ghast.teleport(ghast.getLocation().clone().add(0, 15, 0));
			}
			if (new Random().nextInt(20) == 0){
					ghast.launchProjectile(Fireball.class, new Vector(0, -4, 0));
				}
			}
		}
		}
	
	@SuppressWarnings("deprecation")
	public static void checkBlockRegen(){
		if (!regen.isEmpty()){
		Random r = new Random();
		List<Location> locs = new ArrayList<Location>(regen.keySet());
		int i1 = r.nextInt(regen.size());
		Location loc = locs.get(i1);
		Material block1 = regen.get(loc);
		loc.getBlock().setType(block1);
		regen.remove(loc);
		if (block1 == Material.DIRT){
			loc.getBlock().setData((byte) 2);
		}
		if (regen.size() > 1){
			int i2 = r.nextInt(regen.size() - 1);
			Location loc2 = locs.get(i2);
			Material block2 = regen.get(loc2);
			loc2.getBlock().setType(block2);
			if (block2 == Material.DIRT){
				loc2.getBlock().setData((byte) 2);
			}
			regen.remove(loc2);
		}
		if (regen.size() > 2){
			int i2 = r.nextInt(regen.size() - 2);
			Location loc2 = locs.get(i2);
			Material block2 = regen.get(loc2);
			loc2.getBlock().setType(block2);
			if (block2 == Material.DIRT){
				loc2.getBlock().setData((byte) 2);
			}
			regen.remove(loc2);
		}
		if (regen.size() > 3){
			int i2 = r.nextInt(regen.size() - 3);
			Location loc2 = locs.get(i2);
			Material block2 = regen.get(loc2);
			loc2.getBlock().setType(block2);
			if (block2 == Material.DIRT){
				loc2.getBlock().setData((byte) 2);
			}
			regen.remove(loc2);
		}
		
		}
		if (!fallings.isEmpty()){
			List<FallingBlock> remove = new ArrayList<FallingBlock>();
			for (FallingBlock f : fallings){
				f.setFallDistance(1000f);
				if (f.getLocation().clone().subtract(0, 0.5, 0).getBlock().getType() != Material.AIR){
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(f.getMaterial(), f.getBlockData()), 0.4f, 0.5f, 0.4f, 0.1f, 20, f.getLocation().clone().add(0, 1.3, 0), 100);
					remove.add(f);
					f.remove();
						if (!regen.containsKey(f.getLocation())){
						f.getLocation().getBlock().setType(Material.AIR);
						}
				}
			}
			fallings.removeAll(remove);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void copyMap(){
		int x1 = Math.min(Files.config().getInt("Nether" + ".x1"), Files.config().getInt("Nether" + ".x2"));
		int z1 = Math.min(Files.config().getInt("Nether" + ".z1"), Files.config().getInt("Nether" + ".z2"));
		int y1 = Math.min(Files.config().getInt("Nether" + ".y1"), Files.config().getInt("Nether" + ".y2"));
		int y2 = Math.max(Files.config().getInt("Nether" + ".y1"), Files.config().getInt("Nether" + ".y2"));
		int x2 = Math.max(Files.config().getInt("Nether" + ".x1"), Files.config().getInt("Nether" + ".x2"));
		int z2 = Math.max(Files.config().getInt("Nether" + ".z1"), Files.config().getInt("Nether" + ".z2"));	
		for (int x = x1; x <= x2; x++) {
		    for (int y = y1; y <= y2; y++) {
		        for (int z = z1; z <= z2; z++) {
		           Location end = Bukkit.getWorld("NetherMap").getBlockAt(x, y, z).getLocation();
		           Location ref = end.clone().subtract(300, 0, 0);
		           if (ref.getBlock().getType() != end.getBlock().getType() || ref.getBlock().getData() != end.getBlock().getData()){
		        	   ref.getBlock().setType(end.getBlock().getType());
		        	   ref.getBlock().setData(end.getBlock().getData());
		           }
		        }
		    }
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void regenMap(){
		int x1 = Math.min(Files.config().getInt("Nether" + ".x1"), Files.config().getInt("Nether" + ".x2"));
		int z1 = Math.min(Files.config().getInt("Nether" + ".z1"), Files.config().getInt("Nether" + ".z2"));
		int y1 = Math.min(Files.config().getInt("Nether" + ".y1"), Files.config().getInt("Nether" + ".y2"));
		int y2 = Math.max(Files.config().getInt("Nether" + ".y1"), Files.config().getInt("Nether" + ".y2"));
		int x2 = Math.max(Files.config().getInt("Nether" + ".x1"), Files.config().getInt("Nether" + ".x2"));
		int z2 = Math.max(Files.config().getInt("Nether" + ".z1"), Files.config().getInt("Nether" + ".z2"));	
		for (int x = x1; x <= x2; x++) {
		    for (int y = y1; y <= y2; y++) {
		        for (int z = z1; z <= z2; z++) {
		           Location end = Bukkit.getWorld("NetherMap").getBlockAt(x, y, z).getLocation();
		           Location ref = end.clone().subtract(300, 0, 0);
		           if (end.getBlock().getType() != ref.getBlock().getType() || end.getBlock().getData() != ref.getBlock().getData()){
		        	   end.getBlock().setType(ref.getBlock().getType());
		        	   end.getBlock().setData(ref.getBlock().getData());
		           }
		        }
		    }
		}
		for (FallingBlock b : fallings){
			b.remove();
		}
		fallings.clear();
	}
	
}
