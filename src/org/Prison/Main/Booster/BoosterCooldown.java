package org.Prison.Main.Booster;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.Prison.Main.Files;


public class BoosterCooldown {
	public static boolean hasCooldown(String type){
		if(Files.getDataFile().contains("Cooldowns." + type)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setCooldown(int minutes, String type){
		Calendar c = Calendar.getInstance();
		int currentday = c.get(Calendar.DAY_OF_YEAR);
		int currenthours = c.get(Calendar.HOUR_OF_DAY);
		int currentminutes = c.get(Calendar.MINUTE);
		
		int newday = 0 + currentday;
		int newhours = 0 + currenthours;
		int newminutes = minutes + currentminutes;
		
		if (newminutes >= 60){
			newminutes = newminutes - 60;
			newhours = newhours + 1;
		}
		if (newhours >= 24){
			newhours = newhours - 24;
			newday = newday + 1;
		}
		Files.getDataFile().set("Cooldowns." + type + ".Day", newday);
		Files.getDataFile().set("Cooldowns." + type + ".Hour", newhours);
		Files.getDataFile().set("Cooldowns." + type + ".Minute", newminutes);
		Files.getDataFile().set("Cooldowns." + type + ".Second", c.get(Calendar.SECOND));
		Files.saveDataFile();
	}
	
	public static String getTimeLeft(String type){
		Calendar c = Calendar.getInstance();
		int day = Files.getDataFile().getInt("Cooldowns." + type + ".Day");
		int hours = Files.getDataFile().getInt("Cooldowns." + type + ".Hour");
		int minutes = Files.getDataFile().getInt("Cooldowns." + type + ".Minute");
		int seconds = Files.getDataFile().getInt("Cooldowns." + type + ".Second");
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
			
			return "§e" + diffDays + ":" + diffHours + ":" + diffMinutes + ":" + diffSeconds;
 
		} catch (Exception e) {
			e.printStackTrace();
			return "BROKEN";
		}
	}
	
	public static boolean checkCooldown(String type){
		Calendar c = Calendar.getInstance();
		int day = Files.getDataFile().getInt("Cooldowns." + type + ".Day");
		int hours = Files.getDataFile().getInt("Cooldowns." + type + ".Hour");
		int minutes = Files.getDataFile().getInt("Cooldowns." + type + ".Minute");
		int seconds = Files.getDataFile().getInt("Cooldowns." + type + ".Second");
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
				Files.getDataFile().set("Cooldowns." + type, null);
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
	
	public static int getMinutes(String type, String p){
		if (hasCooldown(type)){
			Calendar c = Calendar.getInstance();
			int day = Files.getDataFile().getInt("Cooldowns." + type + ".Day");
			int hours = Files.getDataFile().getInt("Cooldowns." + type + ".Hour");
			int minutes = Files.getDataFile().getInt("Cooldowns." + type + ".Minute");
			int seconds = Files.getDataFile().getInt("Cooldowns." + type + ".Second");
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
				return (int) ((int) (diffMinutes * 60) + diffSeconds);
			}catch(Exception e){
				return 0;
			}
		}
		return 0;
	}
	
	public static void resetCooldown(String type){
		Files.getDataFile().set("Cooldowns." + type, null);
		Files.saveDataFile();
	}
}
