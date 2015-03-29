package org.Prison.Main.InfoBoard;

import org.Prison.Main.Files;
import org.Prison.Main.Main;
import org.Prison.Main.Currency.CrystalAPI;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Ranks.RankType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class InfoBoard {
	
	
	@SuppressWarnings("deprecation")
	public static void setBoardFor(Player p){
		Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sc.registerNewObjective(p.getName(), "dummy");
		o.setDisplayName("§r  §7§m--§4§l[ The Pit ]§7§m-- ");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score1 = o.getScore(Bukkit.getServer().getOfflinePlayer("§r"));
		score1.setScore(16);
		Score score2 = o.getScore(Bukkit.getServer().getOfflinePlayer("§a§lMoney:"));
		score2.setScore(15);
		Score score3 = o.getScore(Bukkit.getServer().getOfflinePlayer("" + MoneyAPI.getMoney(p)));
		score3.setScore(14);
		Score score4 = o.getScore(Bukkit.getServer().getOfflinePlayer("§r§1"));
		score4.setScore(13);
		Score score5 = o.getScore(Bukkit.getServer().getOfflinePlayer("§b§lCrystals:"));
		score5.setScore(12);
		Score score6 = o.getScore(Bukkit.getServer().getOfflinePlayer("" + CrystalAPI.getCrystals(p.getName())));
		score6.setScore(11);
		Score score7 = o.getScore(Bukkit.getServer().getOfflinePlayer("§r§2"));
		score7.setScore(10);
		Score score8 = o.getScore(Bukkit.getServer().getOfflinePlayer("§9§lCell Block:"));
		score8.setScore(9);
		Score score9 = o.getScore(Bukkit.getServer().getOfflinePlayer(LetterType.getPlayerLetter(p).name()));
		score9.setScore(8);
		Score score10 = o.getScore(Bukkit.getServer().getOfflinePlayer("§r§3"));
		score10.setScore(7);
		Score score11 = o.getScore(Bukkit.getServer().getOfflinePlayer("§e§lRank:"));
		score11.setScore(6);
		Score score12 = o.getScore(Bukkit.getServer().getOfflinePlayer(RankType.toNiceName(RankType.getRank(p))));
		score12.setScore(5);
		Score score13 = o.getScore(Bukkit.getServer().getOfflinePlayer("§r§4"));
		score13.setScore(4);
		p.setScoreboard(sc);
	}
	
	public static void update(final Objective o, final Player p, final Score money, final Score crystal, final Score cell, final Score rank){
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			@SuppressWarnings("deprecation")
			public void run(){
				if (p.isOnline()){
					Files.reloadConfig();				
					if (!p.getWorld().getName().equals("PVP")){
						Score money1 = null;
						Score crystal1 = null;
						Score cell1 = o.getScore(Bukkit.getServer().getOfflinePlayer(LetterType.getPlayerLetter(p).name()));
						Score rank1 = o.getScore(Bukkit.getServer().getOfflinePlayer(RankType.toNiceName(RankType.getRank(p))));
					if (Integer.parseInt(money.getEntry()) != MoneyAPI.getMoney(p)){
					money1 = o.getScore(Bukkit.getServer().getOfflinePlayer("" + MoneyAPI.getMoney(p)));
				    money1.setScore(14);
				    p.getScoreboard().resetScores(money.getEntry());
					}
					if (Integer.parseInt(crystal.getEntry()) != CrystalAPI.getCrystals(p.getName())){
					crystal1 = o.getScore(Bukkit.getServer().getOfflinePlayer("" + CrystalAPI.getCrystals(p.getName())));
				    crystal1.setScore(11);
				    p.getScoreboard().resetScores(crystal.getEntry());
					}
					if (cell.getEntry() != cell1.getEntry()){
				cell1.setScore(8);
				p.getScoreboard().resetScores(cell.getEntry());
					}
					if (rank.getEntry() != rank1.getEntry()){
				rank1.setScore(5);
				p.getScoreboard().resetScores(rank.getEntry());
					}
					update2(o, p, money1, crystal1, cell1, rank1);
					}
				}
			}
		}, 20l * 15);
	}
	
	public static void update2(final Objective o, final Player p, final Score money, final Score crystal, final Score cell, final Score rank){
		update(o, p, money, crystal, cell, rank);
	}
}
