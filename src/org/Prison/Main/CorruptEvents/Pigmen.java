package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Pigmen {
	
	public static boolean active = false;

	public static void start(){
		active = true;
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				active = false;
			}
		}, 110 * 20);
	}
	
	public static Location getSpawn(){
		List<Location> locs = new ArrayList<>();
		World world = Bukkit.getWorld("NetherMap");
		locs.add(new Location(world, 25, 52, -28));
		locs.add(new Location(world, 26, 52, -26));
		locs.add(new Location(world, 38, 55, 11));
		locs.add(new Location(world, 3, 55, -38));
		locs.add(new Location(world, 0, 55, -36));
		return locs.get(new Random().nextInt(locs.size()));
	}
	
	public static Vector getVelocity(Location loc){
		Location middle = new Location(Bukkit.getWorld("NetherMap"), 19, 59, -17);
		Vector direction = middle.toVector().subtract(loc.toVector()).normalize();
		double xv = (-0.2+ (0.5 - -0.2) * new Random().nextDouble());
		double yv = (0.1 + (0.2 - -0.1) * new Random().nextDouble());
		double zv = (-0.2 + (0.5 - -0.2) * new Random().nextDouble());
		direction.multiply(1.3).setY(1.26).add(new Vector(xv, yv, zv));
		return direction;
	}
	
	public static void check(){
		if (active){
		int pigmen = 0;
		for (PigZombie zombiepig : Bukkit.getWorld("NetherMap").getEntitiesByClass(PigZombie.class)){
			zombiepig.setAngry(true);
			if (zombiepig.getTarget() == null){
				boolean bool = false;
				for (int i = 1; i < 80; i++){
					for (Entity e : zombiepig.getNearbyEntities(i, i, i)){
						if (e instanceof Player){
							zombiepig.setTarget((LivingEntity) e);
							bool = true;
						}
					}
					if (bool) break;
				}
			}
			pigmen++;
		}
		if (pigmen < 20){
				Location loc = getSpawn();
				PigZombie pigzombie = (PigZombie) Bukkit.getWorld("NetherMap").spawnEntity(loc, EntityType.PIG_ZOMBIE);
				pigzombie.setAngry(true);
				pigzombie.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
				pigzombie.setVelocity(getVelocity(loc));
				if (new Random().nextInt(3) == 1){
					pigzombie.setBaby(true);
					pigzombie.setHealth(6.0);
				}
		}
	}
	}
}
