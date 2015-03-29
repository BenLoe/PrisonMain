package org.Prison.Main;

import java.util.ArrayList;
import java.util.List;

import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.Traits.SmartTrait;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BookShelf {

	public static void check(){
		for (Player p : Bukkit.getOnlinePlayers()){
		if (Main.bookshelf.containsKey(p.getName())){
			int current = Main.bookshelf.get(p.getName());
			int newint = Main.bookshelf.get(p.getName()) + 1;
			Main.bookshelf.remove(p.getName());
			switch(current){
			case 5:{
				int booster = 1;
				if (RankType.getRank(p).equals(RankType.VIP)){
					booster =2 ;
				}else{
					if (!RankType.getRank(p).equals(RankType.NONE)){
						booster = 3;
					}
				}
				Files.config().set("Players." + p.getName() + ".Smart" , SmartTrait.getSmart(p) + booster);
				Files.saveConfig();
				SmartTrait.checkLevelUp(p);
			}
			break;
			case 4:{
				Block b = Main.bookshelfBlock.get(p.getName());
				List<Player> players = new ArrayList<Player>();
				players.add(p);
				ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.BOOKSHELF, (byte)0), 0.9f, 1f, 0.9f, 0.01f, 300, b.getLocation().add(0.5, 0, 0.5), players);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.2f, 0.8f);
				Main.bookshelf.put(p.getName(), newint);
				Main.bookshelfBlock.remove(p.getName());
			}
			break;
			case 3:{
				p.playSound(p.getLocation(), Sound.CLICK, 0.2f, 1.6f);
				Main.bookshelf.put(p.getName(), newint);
			}
			break;
			case 2:{
				p.playSound(p.getLocation(), Sound.CLICK, 0.2f, 1.4f);
				Main.bookshelf.put(p.getName(), newint);
			}
			break;
			case 1:{
				p.playSound(p.getLocation(), Sound.CLICK, 0.2f, 1.2f);
				Main.bookshelf.put(p.getName(), newint);
			}
			}
		}
		}
	}
}
