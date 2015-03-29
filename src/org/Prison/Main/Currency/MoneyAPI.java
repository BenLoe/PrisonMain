package org.Prison.Main.Currency;

import java.util.List;

import org.Prison.Main.Files;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import com.earth2me.essentials.api.*;

public class MoneyAPI {

	@SuppressWarnings("deprecation")
	public static int getMoney(Player p){
		String name = p.getName();
		try {
			if ((int) Economy.getMoney(name) >= 1000000){
				AchievementAPI.completeAchievement(p, AchievementMenu.SAVING_UP);
			}
			return (int) Economy.getMoney(name);
		} catch (UserDoesNotExistException e) {
			return 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void setMoney(Player p, int i){
		List<String> moneys = Files.getDataFile().getStringList("MoneyPlayers");
		if (!moneys.contains(p.getName())){
			moneys.add(p.getName());
			Files.getDataFile().set("MoneyPlayers", moneys);
			Files.saveDataFile();
		}
		String name = p.getName();
		int old = getMoney(p);
		int newi = 0;
		try {
			Economy.setMoney(name, i);
		} catch (NoLoanPermittedException e) {
			e.printStackTrace();
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		}
		newi = getMoney(p);
			Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
			Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
			Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
			newscore.setScore(14);
			p.getScoreboard().resetScores(oldscore.getEntry());
		Files.saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	public static void addMoney(Player p, int i){
		List<String> moneys = Files.getDataFile().getStringList("MoneyPlayers");
		if (!moneys.contains(p.getName())){
			moneys.add(p.getName());
			Files.getDataFile().set("MoneyPlayers", moneys);
			Files.saveDataFile();
		}
		String name = p.getName();
		int old = getMoney(p);
		int newi = 0;
		try {
			Economy.add(name, i);
		} catch (NoLoanPermittedException e) {
			e.printStackTrace();
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		}
		newi = getMoney(p);
		Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
		Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
		newscore.setScore(14);
		p.getScoreboard().resetScores(oldscore.getEntry());
		Files.saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	public static void removeMoney(Player p, int i){
		List<String> moneys = Files.getDataFile().getStringList("MoneyPlayers");
		if (!moneys.contains(p.getName())){
			moneys.add(p.getName());
			Files.getDataFile().set("MoneyPlayers", moneys);
			Files.saveDataFile();
		}
		int old = getMoney(p);
		int newi = 0;
		String name = p.getName();
		try {
			Economy.subtract(name, i);
		} catch (NoLoanPermittedException e) {
			e.printStackTrace();
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		}
		newi = getMoney(p);
		Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
		Score oldscore = o.getScore(Bukkit.getOfflinePlayer(old + ""));
		Score newscore = o.getScore(Bukkit.getOfflinePlayer(newi + ""));
		newscore.setScore(14);
		p.getScoreboard().resetScores(oldscore.getEntry());
		Files.saveConfig();
	}
}
