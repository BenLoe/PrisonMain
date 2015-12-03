package org.Prison.Main.Currency;

import java.util.UUID;

import org.Prison.Main.Files;
import org.Prison.Main.UUIDFetcher;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class CrystalAPI {

	public static int getCrystals(String p){
		Files.reloadConfig();
		UUID uuid = new UUIDFetcher(p).callForOne();
		if (Files.getDataFile().contains("Players." + uuid)){
			return Files.getDataFile().getInt("Players." + uuid + ".Crystals");
		}else{
			return 0;
		}
	}
	
	public static int getCrystals(Player p){
		Files.reloadConfig();
		UUID uuid = p.getUniqueId();
		if (Files.getDataFile().contains("Players." + uuid)){
			return Files.getDataFile().getInt("Players." + uuid + ".Crystals");
		}else{
			return 0;
		}
	}
	
	public static void setCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = new UUIDFetcher(p).callForOne();
		if (Files.getDataFile().contains("Players." + uuid)){
			old = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
		}
		newi = i;
		Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		Files.saveDataFile();
		if (Bukkit.getPlayerExact(p) != null){
		Player p1 = Bukkit.getPlayerExact(p);
		Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(old + "");
		Score newscore = o.getScore(newi + "");
		newscore.setScore(11);
		p1.getScoreboard().resetScores(oldscore.getEntry());
		}
	}
	
	public static void setCrystals(Player p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = p.getUniqueId();
		if (Files.getDataFile().contains("Players." + uuid)){
			old = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
		}
		newi = i;
		Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		Files.saveDataFile();
		Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(old + "");
		Score newscore = o.getScore(newi + "");
		newscore.setScore(11);
		p.getScoreboard().resetScores(oldscore.getEntry());
	}
	
	public static void addCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = new UUIDFetcher(p).callForOne();
		if (Files.getDataFile().contains("Players." + uuid)){
			int current = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
			old = current;
			newi = current + i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", current + i);
		}else{
			newi = i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		}
		if (Bukkit.getPlayerExact(p) != null){
		Player p1 = Bukkit.getPlayerExact(p);
		Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(old + "");
		Score newscore = o.getScore(newi + "");
		newscore.setScore(11);
		p1.getScoreboard().resetScores(oldscore.getEntry());
		}
		Files.saveDataFile();
	}
	
	public static void addCrystals(Player p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = p.getUniqueId();
		if (Files.getDataFile().contains("Players." + uuid)){
			int current = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
			old = current;
			newi = current + i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", current + i);
		}else{
			newi = i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		}
		Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(old + "");
		Score newscore = o.getScore(newi + "");
		newscore.setScore(11);
		p.getScoreboard().resetScores(oldscore.getEntry());
		Files.saveDataFile();
	}
	
	public static void removeCrystals(String p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = new UUIDFetcher(p).callForOne();
		if (Files.getDataFile().contains("Players." + uuid)){
			int current = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
			old = current;
			newi = current - i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", current - i);
		}else{
			newi = i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		}
		if (Bukkit.getPlayerExact(p) != null){
			Player p1 = Bukkit.getPlayerExact(p);
			Objective o = p1.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
			Score oldscore = o.getScore(old + "");
			Score newscore = o.getScore(newi + "");
			newscore.setScore(11);
			p1.getScoreboard().resetScores(oldscore.getEntry());
			}
		Files.saveDataFile();
	}
	
	public static void removeCrystals(Player p, int i){
		int old = 0;
		int newi = 0;
		UUID uuid = p.getUniqueId();
		if (Files.getDataFile().contains("Players." + uuid)){
			int current = Files.getDataFile().getInt("Players." + uuid + ".Crystals");
			old = current;
			newi = current - i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", current - i);
		}else{
			newi = i;
			Files.getDataFile().set("Players." + uuid + ".Crystals", i);
		}
			Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
			Score oldscore = o.getScore(old + "");
			Score newscore = o.getScore(newi + "");
			newscore.setScore(11);
			p.getScoreboard().resetScores(oldscore.getEntry());
		Files.saveDataFile();
	}
}
