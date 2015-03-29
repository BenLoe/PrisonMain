package org.Prison.Main.Currency;

import org.Prison.Main.Files;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class CrystalAPI {

	public static int getCrystals(String p){
		Files.reloadConfig();
		String name = p;
		if (Files.config().contains("Players." + name)){
			return Files.config().getInt("Players." + name + ".Crystals");
		}else{
			return 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void setCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		String name = p;
		if (Files.config().contains("Players." + name)){
			old = Files.config().getInt("Players." + name + ".Crystals");
		}
		newi = i;
		Files.config().set("Players." + name + ".Crystals", i);
		Files.saveConfig();
		if (Bukkit.getPlayerExact(p) != null){
		Player p1 = Bukkit.getPlayerExact(p);
		Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
		Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
		newscore.setScore(11);
		p1.getScoreboard().resetScores(oldscore.getEntry());
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void addCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		String name = p;
		if (Files.config().contains("Players." + name)){
			int current = Files.config().getInt("Players." + name + ".Crystals");
			old = current;
			newi = current + i;
			Files.config().set("Players." + name + ".Crystals", current + i);
		}else{
			newi = i;
			Files.config().set("Players." + name + ".Crystals", i);
		}
		if (Bukkit.getPlayerExact(p) != null){
		Player p1 = Bukkit.getPlayerExact(p);
		Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
		Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
		newscore.setScore(11);
		p1.getScoreboard().resetScores(oldscore.getEntry());
		}
		Files.saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	public static void removeCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		String name = p;
		if (Files.config().contains("Players." + name)){
			int current = Files.config().getInt("Players." + name + ".Crystals");
			old = current;
			newi = current - i;
			Files.config().set("Players." + name + ".Crystals", current - i);
		}else{
			newi = i;
			Files.config().set("Players." + name + ".Crystals", i);
		}
		if (Bukkit.getPlayerExact(p) != null){
			Player p1 = Bukkit.getPlayerExact(p);
			Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
			Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
			Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
			newscore.setScore(11);
			p1.getScoreboard().resetScores(oldscore.getEntry());
			}
		Files.saveConfig();
	}
}
