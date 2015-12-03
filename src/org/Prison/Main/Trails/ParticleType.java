package org.Prison.Main.Trails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import me.BenLoe.SuperSpleef.Main;

import org.Prison.Main.Files;
import org.Prison.Main.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public enum ParticleType {

	FIRE, HEART, NOTE, GOLD, DIAMOND, THUNDERSTORM, SEASONAL, HALO, WITCH, HELL, NONE;
	
	public static List<UUID> diamonds = new ArrayList<UUID>();
	public static List<UUID> gold = new ArrayList<UUID>();
	public static HashMap<String,Double> hell = new HashMap<>();
	public static HashMap<String,Double> seasonal = new HashMap<>();
	public static HashMap<String,Double> halo = new HashMap<>();
	public static HashMap<String,Double> witch = new HashMap<>();
	public static HashMap<String,Double> witch2 = new HashMap<>();
	
	public boolean hasBought(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Particles")){
			if (Files.getDataFile().getStringList("Players." + p.getUniqueId() + ".Particles").contains(this.name())){
				return true;
			}
		}
		return false;
	}
	
	public void setActive(final Player p){
		Files.getDataFile().set("Players." + p.getUniqueId() + ".ActiveParticle", this.name());
		Files.saveDataFile();
	}
	
	public void addBought(Player p){
		if (!this.hasBought(p)){
		List<String> bought = new ArrayList<String>();
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Particles")){
			bought.addAll(Files.getDataFile().getStringList("Players." + p.getUniqueId() + ".Particles"));
		}
		bought.add(this.name());
		Files.getDataFile().set("Players." + p.getUniqueId() + ".Particles", bought);
		Files.saveDataFile();
		}
	}
	
	public static ParticleType getActive(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".ActiveParticle")){
			for (ParticleType t : values()){
				if (Files.getDataFile().getString("Players." + p.getUniqueId() + ".ActiveParticle").equalsIgnoreCase(t.name())){
					return t;
				}
			}
		}
		return NONE;
	}
	
	public void display(final Player p){
		Location loc = p.getLocation().clone();
		Random r = new Random();
		switch(this){
		case DIAMOND:
			ItemStack toDrop = new ItemStack(Material.AIR);
			int i = r.nextInt(4) + 1;
			switch(i){
			case 1:
				toDrop = new ItemStack(Material.DIAMOND);
				break;
			case 2:
				toDrop = new ItemStack(Material.DIAMOND_BLOCK);
				break;
			case 3:
				toDrop = new ItemStack(Material.DIAMOND_PICKAXE);
				break;
			case 4:
				toDrop = new ItemStack(Material.DIAMOND_CHESTPLATE);
				break;
			}
			ItemMeta toDropm = toDrop.getItemMeta();
			List<String> lore = new ArrayList<>();
			lore.add("NOPICKUP111");
			lore.add(new Random().nextInt(100) + "");
			toDropm.setLore(lore);
			toDrop.setItemMeta(toDropm);
			double x3 = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			double z3 = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			Item item = loc.getWorld().dropItem(loc.clone().add(0, 2, 0), toDrop);
			item.setVelocity(new Vector(x3, 0.3, z3));
			item.setPickupDelay(1000);
			diamonds.add(item.getUniqueId());
			break;
		case FIRE:
			 final double size1 = 0.35;
             final int points1 = 10; //amount of points to be generated
             for (int i1 = 0; i1 < 360; i1 += 360 / points1){
            	 double angle = (i1 * Math.PI / 180);
            	 double x = size1 * Math.cos(angle);
            	 double z = size1 * Math.sin(angle);
            	ParticleEffect.FLAME.display(0.0f, 0.0f, 0.0f, 0.0f, 1, loc.clone().add(x, 2, z), 20);
             }
			break;
		case GOLD:
			ItemStack toDrop1 = new ItemStack(Material.AIR);
			int i1 = r.nextInt(4) + 1;
			switch(i1){
			case 1:
				toDrop1 = new ItemStack(Material.GOLD_INGOT);
				break;
			case 2:
				toDrop1 = new ItemStack(Material.GOLD_NUGGET);
				break;
			case 3:
				toDrop1 = new ItemStack(Material.GOLD_PICKAXE);
				break;
			case 4:
				toDrop1 = new ItemStack(Material.GOLD_CHESTPLATE);
				break;
			}
			ItemMeta toDropm1 = toDrop1.getItemMeta();
			List<String> lore1 = new ArrayList<>();
			lore1.add("NOPICKUP111");
			lore1.add(new Random().nextInt(100) +"");
			toDropm1.setLore(lore1);
			toDrop1.setItemMeta(toDropm1);
			double x1 = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			double z1 = (-0.08 + (0.08 - -0.08) * new Random().nextDouble());
			Item item1 = loc.getWorld().dropItem(loc.clone().add(0, 2, 0), toDrop1);
			item1.setVelocity(new Vector(x1, 0.3, z1));
			item1.setPickupDelay(1000);
			gold.add(item1.getUniqueId());
			break;
		case HEART:
			ParticleEffect.HEART.display(0.15f, 0.01f, 0.15f, 0.001f, 1, loc.clone().add(0, 2, 0), 20);
			break;
		case NOTE:
			int i2 = r.nextInt(5) + 1;
			switch(i2){
			case 1:
				ParticleEffect.NOTE.display(0.18f, 0.01f, 0.18f, 10f, 2, loc.clone().add(0, 2, 0), 20);
				break;
			case 2:
				ParticleEffect.NOTE.display(0.18f, 0.01f, 0.18f, 11f, 2, loc.clone().add(0, 2, 0), 20);
				break;
			case 3:
				ParticleEffect.NOTE.display(0.18f, 0.01f, 0.18f, 12f, 2, loc.clone().add(0, 2, 0), 20);
				break;
			case 4:
				ParticleEffect.NOTE.display(0.18f, 0.01f, 0.18f, 13f, 2, loc.clone().add(0, 2, 0), 20);
				break;
			case 5:
				ParticleEffect.NOTE.display(0.18f, 0.01f, 0.18f, 14f, 2, loc.clone().add(0, 2, 0), 20);
				break;
			}
			break;
		case THUNDERSTORM:
			ParticleEffect.CLOUD.display(0.25f, 0.095f, 0.25f, 0.0001f, 15, loc.clone().add(0, 2.7, 0), 20);
			ParticleEffect.DRIP_WATER.display(0.14f, 0.08f, 0.14f, 1f, 5, loc.clone().add(0, 2.75, 0), 20);
			break;
		case SEASONAL:
			if (seasonal.containsKey(p.getName())){
				double phi = seasonal.get(p.getName());
				phi = phi + Math.PI / 8;
				double x, y, z;

				Location location1 = p.getLocation();
				for (double t = 0; t <= 2 * Math.PI; t = t + Math.PI / 16) {
                 for (double i3 = 0; i3 <= 1; i3 = i3 + 1) {
                     Random color = new Random();
                     int rbg = color.nextInt(3);

                     x = 0.4 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i3 * Math.PI);
                     y = 0.5 * t;
                     z = 0.4 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i3 * Math.PI);
                     location1.add(x, y, z);
                     if (rbg == 1) {
                         ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(1, 0, 0), location1, 7);
                     }
                     if (rbg == 2) {
                         ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 69, 0), location1, 7);
                     }
                     location1.subtract(x, y, z);
                 }

             }
				seasonal.put(p.getName(), phi);

             if (phi > 10 * Math.PI) {
                 seasonal.put(p.getName(), 0.0);
             }
			}else{
				seasonal.put(p.getName(), 0.0);
			}
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("unused")
	public void display1(final Player p){
		Location loc = p.getLocation().clone();
		switch(this){
		case WITCH:
 			if (witch.containsKey(p.getName()) && witch2.containsKey(p.getName())){
 				int points = 15;
 				double i = witch.get(p.getName());
 				final double size = 0.45;
 	            i += 1;
 	            double angle = (i * Math.PI / 180);
 	            double x = size * Math.cos(angle);
 	            double z = size * Math.sin(angle);
	            
 	            loc.add(x, 2, z);
 	            ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 2, loc, 7);
 	            loc.subtract(x, 2, z);
 	            if (i >= 361) {
 	                i = 0 + 361 / points;
 	            } else {
 	                i += 361 / points;
 	            }
 	            double i2 = witch2.get(p.getName());
 	            i2 += 1;
	            double angle2 = (i2 * Math.PI / 180);
	            double x2 = size * Math.cos(angle2);
	            double z2 = size * Math.sin(angle2);
	            
	            loc.add(x2, 2, z2);
	            ParticleEffect.SPELL_WITCH.display(0, 0, 0, 0, 2, loc, 7);
	            loc.subtract(x2, 2, z2);
	            if (i2 <= 0) {
	                i2 = 360 - (360 / points);
	            } else {
	                i2 -= 350 / points;
	            }
 	            witch.put(p.getName(), i);
 	            witch2.put(p.getName(), i2);
 	        }else{
 	        	witch.put(p.getName(), 0.0);
 	        	witch2.put(p.getName(), 190.0);
 	        }
			break;
		case HELL:
			if (hell.containsKey(p.getName())){
				if (hell.get(p.getName()) != -2.0){
				double t = hell.get(p.getName());
				t += Math.PI/16;

                for(double phi = 0; phi <= 2*Math.PI; phi += Math.PI/2) {
                    double x = .068*(4*Math.PI - t) * Math.cos(t + phi);
                    double y = .2*t;
                    double z = .068*(4*Math.PI - t) * Math.sin(t + phi);
                    loc.add(x,y,z);
                    ParticleEffect.REDSTONE.display(0, 0, 0, 0, 1, loc, 7);
                    loc.subtract(x,y,z);
                }
                
                if(t >= 3*Math.PI) {
                    hell.put(p.getName(), -2.0);
                    Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
                    	public void run(){
                    		hell.put(p.getName(), 0.0);
                    	}
                    }, 20l);
                }else{
                	hell.put(p.getName(), t);
                }
				}
			}else{
				hell.put(p.getName(), 0.0);
			}
			break;
		case HALO:
			if (halo.containsKey(p.getName())){
			double i = halo.get(p.getName());
			final double size = 0.5;
            i += 1;
            final int points = 15; //amount of points to be generated
            double angle = (i * Math.PI / 180);
            double x = size * Math.cos(angle);
            double z = size * Math.sin(angle);

            loc.add(x, 2.1, z);

            for (double y = 0; y < 5; y += 1) { //animated ring
                ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 255, 0), loc, 7);
                ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, 0, 1, loc, 7);

            }

            loc.subtract(x, 2.5, z);
            if (i >= 361) {
                i = 0 + 361 / points;
            } else {
                i += 361 / points;
            }
            halo.put(p.getName(), i);
        }else{
        	halo.put(p.getName(), 0.0);
        }
			break;
		default:
			break;
		
		}
	}
}
