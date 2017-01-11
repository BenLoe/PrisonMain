package org.Prison.Main.Traits;


import org.Prison.Main.Files;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class SmartTrait {

	public static int getLevel(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Smartlvl")){
			return Files.getDataFile().getInt("Players." + p.getUniqueId() + ".Smartlvl");
		}else{
			return 0;
		}
	}
	
	public static int getNeeded(int lvl){
		switch(lvl){
		case 0:{
			return 50;
		}
		case 1:{
			return 100;
		}
		case 2:{
			return 200;
		}
		case 3:{
			return 300;
		}
		case 4:{
			return 450;
		}
		case 5:{
			return 500;
		}
		case 6:{
			return 600;
		}
		case 7:{
			return 750;
		}
		case 8:{
			return 800;
		}
		case 9:{
			return 900;
		}
		case 10:{
			return 1000;
		}
		case 11:{
			return 1200;
		}
		case 12:{
			return 1350;
		}
		case 13:{
			return 1400;
		}
		case 14:{
			return 1500;
		}
		case 15:{
			return 1700;
		}
		}
		return 0;
	}
	
	public static void checkLevelUp(Player p){
		int lvl = getLevel(p);
		if (lvl < 15){
		int Smart = getSmart(p);
		if (Smart >= getNeeded(lvl)){
			levelUp(p);
		}
		}
	}
	
	public static int getSmart(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Smart")){
			return Files.getDataFile().getInt("Players." + p.getUniqueId() + ".Smart");
		}else{
			return 0;
		}
	}
	
	public static void levelUp(Player p){
		int currentlvl = getLevel(p);
		if (currentlvl < 15){
			p.sendMessage("§a§lLevel Up! §eYour intellectual level is now " + (currentlvl + 1));
			int newlvl = currentlvl + 1;
			Files.getDataFile().set("Players." + p.getUniqueId() + ".Smartlvl", newlvl);
			Files.getDataFile().set("Players." + p.getUniqueId() + ".Smart", 0);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 0.8f);
			Files.saveDataFile();
			if (newlvl == 15){
				AchievementAPI.completeAchievement(p ,AchievementMenu.SMARTEST_GUY_AROUND);
			}
		}
		}
	
	public static String getProgress(Player p){
		int lvl = getLevel(p);
		if (lvl >= 15){
			return "§a§l    MAXED     ";
		}
		double smart = getSmart(p);
		double needed = getNeeded(lvl);
		double percent = (smart / needed) * 100;
		if (percent < 99 && percent > 92.5){
			return "§e❚❚❚❚❚❚❚❚❚❚❚❚❚❚§7❚";
		}
		if (percent < 92.4 && percent > 85.9){
			return "§e❚❚❚❚❚❚❚❚❚❚❚❚❚❚§7❚";
		}
		if (percent < 85.8 && percent > 79.3){
			return "§e❚❚❚❚❚❚❚❚❚❚❚❚❚❚§7❚❚";
		}
		if (percent < 79.2 && percent > 72.7){
			return "§e❚❚❚❚❚❚❚❚❚❚❚❚❚§7❚❚❚";
		}
		if (percent < 72.6 && percent > 66.1){
			return "§e❚❚❚❚❚❚❚❚❚❚❚❚§7❚❚❚❚";
		}
		if (percent < 66 && percent > 59.5){
			return "§e❚❚❚❚❚❚❚❚❚❚❚§7❚❚❚❚❚";
		}
		if (percent < 59.4 && percent > 52.9){
			return "§e❚❚❚❚❚❚❚❚❚❚§7❚❚❚❚❚❚";
		}
		if (percent < 52.8 && percent > 46.3){
			return "§e❚❚❚❚❚❚❚❚§7❚❚❚❚❚❚❚";
		}
		if (percent < 46.2 && percent > 39.7){
			return "§e❚❚❚❚❚❚❚§7❚❚❚❚❚❚❚❚";
		}
		if (percent < 39.6 && percent > 33.1){
			return "§e❚❚❚❚❚§7❚❚❚❚❚❚❚❚❚❚";
		}
		if (percent < 33 && percent > 26.5){
			return "§e❚❚❚❚§7❚❚❚❚❚❚❚❚❚❚❚";
		}
		if (percent < 26.4 && percent > 19.9){
			return "§e❚❚❚§7❚❚❚❚❚❚❚❚❚❚❚❚";
		}
		if (percent < 19.8 && percent > 13.3){
			return "§e❚❚§7❚❚❚❚❚❚❚❚❚❚❚❚❚";
		}
		if (percent < 13.2 && percent > 6.7){
			return "§e❚§7❚❚❚❚❚❚❚❚❚❚❚❚❚❚";
		}
		if (percent < 6.6){
			return "§7❚❚❚❚❚❚❚❚❚❚❚❚❚❚❚";
		}
		return "§e❚❚❚❚❚❚❚❚❚❚❚❚❚❚§7❚";
	}
}
