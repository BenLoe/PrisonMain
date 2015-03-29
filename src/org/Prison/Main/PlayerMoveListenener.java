package org.Prison.Main;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.RegionChecker.VisibleLines;
import org.Prison.Main.Traits.SpeedTrait;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListenener implements Listener{

	public static Main plugin;
	public PlayerMoveListenener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		SpeedTrait.checkLevelUp(p);
		if (!PlayerMode.isInPlayerMode(p)){
			if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockY() != event.getTo().getBlockY() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()){
				DonatorCellLine.checkForPlayer(p, event);
				CellBlockLines.checkForPlayer(p, event);
				VisibleLines.checkForPlayer(p, event);
			}
			if (!p.getWorld().getName().equals("PVP") && event.getTo().getBlockY() < 6 && event.getTo().subtract(0, 1, 0).getBlock().getType() == Material.GRASS){
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
		if ((RankType.getRank(p).equals(RankType.ELITE) || RankType.getRank(p).equals(RankType.JRMOD) || RankType.getRank(p).equals(RankType.MODERATOR) || RankType.getRank(p).equals(RankType.ADMIN) || RankType.getRank(p).equals(RankType.OWNER))){
			if (p.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && !p.getWorld().getName().equals("PVP")){
				p.setAllowFlight(true);
			}
		}else{
			if (p.getAllowFlight()){
				p.setAllowFlight(false);
			}
		}
		}
	}
}
