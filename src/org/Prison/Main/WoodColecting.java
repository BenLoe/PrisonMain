package org.Prison.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WoodColecting {

	public static void check(){
		for (Player p : Bukkit.getOnlinePlayers()){
			if (Main.wood.containsKey(p.getName())){
				int current = Main.wood.get(p.getName());
				int newint = current + 1;
				Main.wood.remove(p.getName());
				switch(current){
				case 1:{
					Block b =Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 20, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1.4f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 2:{
					Block b = Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 20, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1.4f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 3:{
					Block b = Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 20, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1.4f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 4:{
					Block b = Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 20, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1.4f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 5:{
					Block b = Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 20, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1.4f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 6:{
					Block b = Main.woodBlock.get(p.getName());
					List<Player> players = new ArrayList<Player>();
					players.add(p);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.LOG, (byte)0), 0.3f, 0.3f, 0.3f, 1f, 120, b.getLocation().add(0.5, 0.5, 0.5), players);
					p.playSound(p.getLocation(), Sound.DIG_WOOD, 0.3f, 1f);
					Main.wood.put(p.getName(), newint);
				}
				break;
				case 7:{
					Main.woodBlock.remove(p.getName());
				}
				}
			}
		}
	}
}
