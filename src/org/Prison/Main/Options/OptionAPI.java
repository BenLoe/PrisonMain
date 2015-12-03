package org.Prison.Main.Options;

import java.util.UUID;

import org.Prison.Main.Files;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OptionAPI {

	public static boolean isEnabled(OptionType t, String name){
		UUID uuid = Bukkit.getPlayer(name).getUniqueId();
		switch(t){
		case FRIENDS:{
			if (Files.getDataFile().contains("Players." + uuid + ".Friend")){
				if (Files.getDataFile().getBoolean("Players." + uuid + ".Friend")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case VISIBILITY:{
			if (Files.getDataFile().contains("Players." + uuid + ".Visibility")){
				if (Files.getDataFile().getBoolean("Players." + uuid + ".Visibility")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case LETTER:{
			if (Files.getDataFile().contains("Players." + uuid + ".Letter1")){
				if (Files.getDataFile().getBoolean("Players." + uuid + ".Letter1")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case ACHIEVEMENTS:{
			if (Files.getDataFile().contains("Players." + uuid + ".Achievements")){
				if (Files.getDataFile().getBoolean("Players." + uuid + ".Achievements")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case FIXED:{
			if (Files.getDataFile().contains("Players." + uuid + ".Fixed")){
				if (Files.getDataFile().getBoolean("Players." + uuid + ".Fixed")){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		}
		return true;
	}
	
	public static void reverse(OptionType t, Player p){
		String name = p.getUniqueId().toString();
		switch(t){
		case FRIENDS:{
			if (isEnabled(OptionType.FRIENDS, p.getName())){
				Files.getDataFile().set("Players." + name + ".Friend", false);
			}else{
				Files.getDataFile().set("Players." + name + ".Friend", true);
			}
		}
		break;
		case ACHIEVEMENTS:{
			if (isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				Files.getDataFile().set("Players." + name + ".Achievements", false);
			}else{
				Files.getDataFile().set("Players." + name + ".Achievements", true);
			}
		}
		break;
		case VISIBILITY:{
			if (isEnabled(OptionType.VISIBILITY, p.getName())){
				Files.getDataFile().set("Players." + name + ".Visibility", false);
			}else{
				Files.getDataFile().set("Players." + name + ".Visibility", true);
			}
		}
		break;
		case LETTER:{
			if (isEnabled(OptionType.LETTER, p.getName())){
				Files.getDataFile().set("Players." + name + ".Letter1", false);
			}else{
				Files.getDataFile().set("Players." + name + ".Letter1", true);
			}
		}
		case FIXED:{
			if (isEnabled(OptionType.FIXED, p.getName())){
				Files.getDataFile().set("Players." + name + ".Fixed", false);
			}else{
				Files.getDataFile().set("Players." + name + ".Fixed", true);
			}
		}
		}
		Files.saveDataFile();
	}
}
