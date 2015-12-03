package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.Prison.Main.Main;
import org.Prison.Main.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class FireRain {

	public static boolean active = false;
	public static List<UUID> wolfs = new ArrayList<UUID>();
	public static Location spawn = new Location(Bukkit.getWorld("NetherMap"), 25, 98, -16);
	
	public static void start(){
		active = true;
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				active = false;
			}
		}, 110 * 20);
	}
	
	public static void check(){
		if (active){
			int spawned = 0;
			Random r = new Random();
			for (int i = 0; i < 100; i++){
				if (spawned >= 17){
					break;
				}
				int x = r.nextInt(70) - 35;
				int z = r.nextInt(70) - 35;
				Location spawning = spawn.clone().add(x, 0, z);
				if (spawning.getBlock().getType() == Material.AIR){
					spawned++;
					Creeper creeper = (Creeper) spawning.getWorld().spawnEntity(spawning, EntityType.CREEPER);
					creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 20, 2));
					creeper.setFireTicks(1000);
					wolfs.add(creeper.getUniqueId());
					double xv = (-0.8 + (0.8 - -0.8) * new Random().nextDouble());
					double yv = (-0.8 + (0.8 - -0.8) * new Random().nextDouble());
					double zv = (-0.8 + (0.8 - -0.8) * new Random().nextDouble());
					creeper.setVelocity(new Vector(xv, yv, zv));
				}
			}
		}
	}
	
	public static void check2(){
		for (Creeper creeper : Bukkit.getWorld("NetherMap").getEntitiesByClass(Creeper.class)){
			if (wolfs.contains(creeper.getUniqueId())){
				if (creeper.getLocation().clone().subtract(0, 1, 0).getBlock().getType() != Material.AIR || creeper.getTicksLived() > 40){
					for (Entity e : creeper.getNearbyEntities(1.4, 1.4, 1.4)){
						if (e instanceof Player){
							e.setFireTicks(40);
						}
					}
					ParticleEffect.FLAME.display(0.2f, 0.3f, 0.2f, 0.001f, 30, creeper.getLocation(), 100);
					wolfs.remove(creeper.getUniqueId());
					creeper.remove();
				}
			}
		}
	}
	
}
