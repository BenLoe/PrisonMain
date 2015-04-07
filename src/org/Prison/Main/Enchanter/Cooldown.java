package org.Prison.Main.Enchanter;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.Prison.Main.Files;
import org.Prison.Main.Letter.LetterType;
import org.bukkit.entity.Player;

public class Cooldown {

	public static boolean hasCooldown(Player p, LetterType t, int id){
		if(Files.getDataFile().contains("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setCooldown(Player p, LetterType t, int id){
		Calendar c = Calendar.getInstance();
		int days = Files.config().getInt("Cooldown." + t.getName() + "." + id + ".Day");
		int hours = Files.config().getInt("Cooldown." + t.getName() + "." + id +  ".Hour");
		int minutes = Files.config().getInt("Cooldown." + t.getName() + "." + id + ".Minute");

		int currentday = c.get(Calendar.DAY_OF_YEAR);
		int currenthours = c.get(Calendar.HOUR_OF_DAY);
		int currentminutes = c.get(Calendar.MINUTE);
		
		int newday = days + currentday;
		int newhours = hours + currenthours;
		int newminutes = minutes + currentminutes;
		
		if (newminutes >= 60){
			newminutes = newminutes - 60;
			newhours = newhours + 1;
		}
		if (newhours >= 24){
			newhours = newhours - 24;
			newday = newday + 1;
		}
		Files.getDataFile().set("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id +  ".Day", newday);
		Files.getDataFile().set("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Hour", newhours);
		Files.getDataFile().set("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Minute", newminutes);
		Files.getDataFile().set("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Second", c.get(Calendar.SECOND));
		Files.saveDataFile();
	}
	
	public static String getTimeLeft(Player p, LetterType t, int id){
		Calendar c = Calendar.getInstance();
		int day = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Day");
		int hours = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Hour");
		int minutes = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Minute");
		int seconds = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Second");
		String dateStart = c.get(Calendar.DAY_OF_YEAR) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		String dateStop = day + "/" + c.get(Calendar.YEAR) + " " + hours + ":" + minutes + ":" + seconds;
 
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("dd/yyyy HH:mm:ss");
 
		Date d1 = null;
		Date d2 = null;
 
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			
			
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			return "§9" + diffDays + "§9§lD§9 " + diffHours + "§9§lH§9 " + diffMinutes + "§9§lM§9 " + diffSeconds + "§9§lS";
 
		} catch (Exception e) {
			e.printStackTrace();
			return "BROKEN";
		}
	}
	
	public static boolean checkCooldown(Player p, LetterType t, int id){
		Calendar c = Calendar.getInstance();
		int day = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Day");
		int hours = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Hour");
		int minutes = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Minute");
		int seconds = Files.getDataFile().getInt("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id + ".Second");
		String dateStart = c.get(Calendar.DAY_OF_YEAR) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		String dateStop = day + "/" + c.get(Calendar.YEAR) + " " + hours + ":" + minutes + ":" + seconds;
 
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("dd/yyyy HH:mm:ss");
 
		Date d1 = null;
		Date d2 = null;
 
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			if (diff <= 0){
				Files.getDataFile().set("Players." + p.getName() + ".Cooldowns." + t.getName() + "." + id, null);
				Files.saveDataFile();
				return true;
			}else{
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getCooldown(LetterType t, int id){
		int days = Files.getDataFile().getInt("Cooldown." + t.getName() + "." + id + ".Day");
		int hours = Files.getDataFile().getInt("Cooldown." + t.getName() + "." + id +  ".Hour");
		int minutes = Files.getDataFile().getInt("Cooldown." + t.getName() + "." + id + ".Minute");
		
		if (days > 0 && hours > 0 && minutes > 0){
			return " §e§l" + days + "D " + hours + "H " + minutes + "M"; 
		}
		if (days > 0 && hours > 0 && minutes == 0){
			return " §e§l" + days + "D " + hours + "H";
		}
		if (hours > 0 && minutes > 0 && days == 0){
			return " §e§l" + hours + "H " + minutes + "M";
		}
		if (days > 0 && minutes > 0 && hours == 0){
			return " §e§l" + days + "D " + minutes + "M";
		}
		if (days > 0 && hours == 0 && minutes == 0){
			return " §e§l" + days + " Days"; 
		}
		if (hours > 0 && days == 0 && minutes == 0){
			return " §e§l" + days + " Hours"; 
		}
		if (minutes > 0 && days == 0 && hours == 0){
			return " §e§l" + minutes + " Minutes"; 
		}
		return "BROKEN";
	}
}
