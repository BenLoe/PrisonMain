package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.Prison.Main.Files;
import org.Prison.Main.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DropKey {

	public static List<Location> particle = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public static void drop(){
		Random r = new Random();
		List<Vector> locs = (List<Vector>) Files.config().getList("Keys");
		Vector v = locs.get(r.nextInt(locs.size()));
		Location loc = new Location(Bukkit.getWorld("NetherMap"), v.getBlockX(), v.getBlockY(), v.getBlockZ());
		loc.getBlock().setType(Material.CHEST);
		particle.add(loc);
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.getWorld().getName().equals("NetherMap")){
				p.sendMessage("§b§lA corrupt key has spawned in the corrupt pit. §7§o(Look for a flaming chest)");
			}
		}
	}
	
	public static void check(){
		if (!particle.isEmpty()){
			for (Location loc : particle){
				ParticleEffect.FLAME.display(0.4f, 0.4f, 0.4f, 0.05f, 30, loc.clone().add(0.5, 0, 0.5), 50);
			}
		}
	}
}
