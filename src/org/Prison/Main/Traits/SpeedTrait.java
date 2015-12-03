package org.Prison.Main.Traits;


import org.Prison.Main.Files;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class SpeedTrait {

	public static int getLevel(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Speed")){
			return Files.getDataFile().getInt("Players." + p.getUniqueId() + ".Speed");
		}else{
			return 0;
		}
	}
	
	public static int getNeeded(int lvl){
		switch(lvl){
		case 0:{
			return 50000;
		}
		case 1:{
			return 100000;
		}
		case 2:{
			return 200000;
		}
		case 3:{
			return 350000;
		}
		case 4:{
			return 550000;
		}
		case 5:{
			return 1000000;
		}
		case 6:{
			return 2000000;
		}
		case 7:{
			return 3000000;
		}
		case 8:{
			return 4500000;
		}
		case 9:{
			return 6000000;
		}
		case 10:{
			return 8000000;
		}
		case 11:{
			return 10000000;
		}
		case 12:{
			return 1050000;
		}
		case 13:{
			return 1100000;
		}
		case 14:{
			return 1300000;
		}
		}
		return 0;
	}
	
	public static void checkLevelUp(Player p){
		int lvl = getLevel(p);
		if (lvl < 15){
		int walked = getWalked(p);
		if (walked >= getNeeded(lvl)){
			levelUp(p);
		}
		}
	}
	
	public static int getWalked(Player p){
		return p.getStatistic(Statistic.WALK_ONE_CM);
	}
	
	public static void levelUp(Player p){
		int currentlvl = getLevel(p);
		if (currentlvl < 15){
			p.sendMessage("§a§lLevel Up! §9Your speed level is now " + (currentlvl + 1));
			int newlvl = currentlvl + 1;
			Files.getDataFile().set("Players." + p.getUniqueId() + ".Speed", newlvl);
			Files.saveDataFile();
			setCorrectSpeed(p);
			p.setStatistic(Statistic.WALK_ONE_CM, 0);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 0.8f);
		}
		}
	public static void setCorrectSpeed(Player p){
		int lvl = getLevel(p);
		p.setWalkSpeed((0.021f * lvl + 0.2f));
	}
	
	public static String getProgress(Player p){
		int lvl = getLevel(p);
		if (lvl >= 15){
			return "§a§l    MAXED     ";
		}
		double walked = getWalked(p);
		double needed = getNeeded(lvl);
		double percent = (walked / needed) * 100;
		if (percent < 99 && percent > 92.5){
			return "§9▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍§7▍";
		}
		if (percent < 92.4 && percent > 85.9){
			return "§9▍▍▍▍▍▍▍▍▍▍▍▍▍▍§7▍▍";
		}
		if (percent < 85.8 && percent > 79.3){
			return "§9▍▍▍▍▍▍▍▍▍▍▍▍▍§7▍▍▍";
		}
		if (percent < 79.2 && percent > 72.7){
			return "§9▍▍▍▍▍▍▍▍▍▍▍▍§7▍▍▍▍";
		}
		if (percent < 72.6 && percent > 66.1){
			return "§9▍▍▍▍▍▍▍▍▍▍§7▍▍▍▍▍▍";
		}
		if (percent < 66 && percent > 59.5){
			return "§9▍▍▍▍▍▍▍▍▍§7▍▍▍▍▍▍▍";
		}
		if (percent < 59.4 && percent > 52.9){
			return "§9▍▍▍▍▍▍▍▍§7▍▍▍▍▍▍▍▍";
		}
		if (percent < 52.8 && percent > 46.3){
			return "§9▍▍▍▍▍▍▍§7▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 46.2 && percent > 39.7){
			return "§9▍▍▍▍▍▍§7▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 39.6 && percent > 33.1){
			return "§9▍▍▍▍▍§7▍▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 33 && percent > 26.5){
			return "§9▍▍▍▍§7▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 26.4 && percent > 19.9){
			return "§9▍▍▍§7▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 19.8 && percent > 13.3){
			return "§9▍▍§7▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 13.2 && percent > 6.7){
			return "§9▍§7▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		if (percent < 6.6){
			return "§7▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍▍";
		}
		return "§9▍▍▍▍▍▍▍▍▍▍▍▍▍▍§7▍";
	}
}
