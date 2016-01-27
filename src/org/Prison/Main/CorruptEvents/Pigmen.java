package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Pigmen {
	
	public static boolean active = true;
	public static int amount = 15;

	public static void start(){
		Random r = new Random();
		switch(r.nextInt(3) + 1){
		case 1:
		case 2:
			amount = r.nextInt(10) + 10;
			break;
		case 3:
			active = false;
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
				public void run(){
					active = true;
				}
			}, 45 * 20l);
			break;
		}
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
		boolean noPlayers = true;
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.getWorld().getName().equals("NetherMap")){
				noPlayers = false;
				break;
			}
		}
		if (active){
		int pigmen = 0;
		for (Entity e : Bukkit.getWorld("NetherMap").getEntities()){
			if (e instanceof PigZombie || e instanceof Skeleton){
			if (noPlayers){
				e.remove();
			}else{
				if (e.getLocation().getBlockY() < 50){
					Location loc = getSpawn();
					e.teleport(loc);
					e.setVelocity(getVelocity(loc));
				}
				if (e instanceof PigZombie){
					((PigZombie) e).setAngry(true);
				}
				Creature le = (Creature) e;
					if (le.getTarget() == null){
						boolean bool = false;
						for (int i = 1; i < 80; i++){
							for (Entity e1 : le.getNearbyEntities(i, i, i)){
								if (e1 instanceof Player){
									le.setTarget((LivingEntity) e1);
									bool = true;
								}
							}
							if (bool) break;
						}	
					}
			}
				pigmen++;
			}
		}
		if (pigmen < amount){
			if (!noPlayers){
				Location loc = getSpawn();
				if (new Random().nextInt(5) == 1){
					Skeleton skel = (Skeleton) Bukkit.getWorld("NetherMap").spawnEntity(loc, EntityType.SKELETON);
					skel.getEquipment().setChestplate(new ItemStack(Material.IRON_HELMET));
					skel.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 1));
					skel.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 3));
					if (new Random().nextInt(7) == 1){
						ItemStack bow = new ItemStack(Material.BOW);
						bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
						skel.getEquipment().setItemInHand(bow);
					}
					if (new Random().nextInt(6) == 1){
						skel.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
					}
					skel.setVelocity(getVelocity(loc));
				}else{
					PigZombie pigzombie = (PigZombie) Bukkit.getWorld("NetherMap").spawnEntity(loc, EntityType.PIG_ZOMBIE);
					pigzombie.setAngry(true);
					pigzombie.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD));
					pigzombie.setVelocity(getVelocity(loc));
					if (new Random().nextInt(6) == 1){
						pigzombie.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
					}
					if (new Random().nextInt(3) == 1){
						pigzombie.setBaby(true);
						pigzombie.setHealth(6.0);
					}
					pigzombie.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 3));
				}
			}
		}
	}
	}
}
