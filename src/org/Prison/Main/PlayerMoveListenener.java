package org.Prison.Main;

import me.BenLoe.SuperSpleef.Game;
import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.CorruptEvents.CorruptMenu;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.RegionChecker.VisibleLines;
import org.Prison.Main.Traits.SpeedTrait;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListenener implements Listener{

	public static Main plugin;
	public PlayerMoveListenener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent event){
		final Player p = event.getPlayer();
		if (p.getWorld().getName().equals("PrisonMap") && !Game.playerInGame(p) && !org.Prison.Lucky.Game.playerInGame(p)){
			SpeedTrait.checkLevelUp(p);
		}
		if (!PlayerMode.isInPlayerMode(p)){
			if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockY() != event.getTo().getBlockY() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()){
				DonatorCellLine.checkForPlayer(p, event);
				CellBlockLines.checkForPlayer(p, event);
				VisibleLines.checkForPlayer(p, event);
				if (CorruptMenu.teleporting.contains(p.getName())){
					event.setCancelled(true);
					p.teleport(event.getFrom());
				}
			}
			if (!p.getWorld().getName().equals("PVP") && !p.getWorld().getName().equals("Build") && event.getTo().getBlockY() < 6 && p.getLocation().clone().subtract(0, 1, 0).getBlock().getType() == Material.GRASS){
				p.teleport(Main.getLocation("spawn"));
				p.sendMessage(ChatColor.YELLOW + "You got out of the map so we teleported you back, your welcome.");
			}
		}
		if (Main.Tutorialint.containsKey(p.getName())){
			if (!(event.getFrom() == event.getTo())){
				p.teleport(event.getFrom());
			}
		}
		if (!p.getGameMode().equals(GameMode.CREATIVE)){
			if ((RankType.getRank(p).equals(RankType.ELITE) ||RankType.getRank(p).equals(RankType.BUILDER) || RankType.getRank(p).equals(RankType.JRMOD) || RankType.getRank(p).equals(RankType.MODERATOR) || RankType.getRank(p).equals(RankType.ADMIN) || RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ULTRA))){
				if (org.Prison.Lucky.Game.ingame.contains(p.getName()) || org.Prison.Lucky.Game.watching.contains(p.getName()) || Game.ingame.contains(p.getName()) || Game.watching.contains(p.getName()) || CorruptMenu.teleporting.contains(p.getName())){
					p.setAllowFlight(false);
				}else
					if (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && !p.getWorld().getName().equals("PVP")){
						p.setAllowFlight(true);
					}
			}else{
				if (p.getAllowFlight()){
					p.setAllowFlight(false);
				}
			}
		}
		if (p.getWorld().getName().equals("NetherMap")){
			if (p.getLocation().clone().subtract(0, 1, 0).getBlock().getType() == Material.OBSIDIAN &&  p.getLocation().getBlockY() > 55){
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 12));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5 * 20, -20));
						ParticleEffect.PORTAL.display(0.5f, 1f, 0.5f, 0.3f, 50, p.getLocation(), 20);
						CorruptMenu.teleporting.add(p.getName());
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
							public void run(){
								CorruptMenu.teleporting.remove(p.getName());
								p.teleport(Main.getLocation("spawn"));
								p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 0.5f, 1f);
							}
						}, 4 * 20l);
					}
				}, 20l);
			}
		}
	}
}
