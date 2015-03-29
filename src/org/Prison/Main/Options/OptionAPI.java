package org.Prison.Main.Options;

import org.Prison.Main.Files;
import org.bukkit.entity.Player;

public class OptionAPI {

	public static boolean isEnabled(OptionType t, String name){
		switch(t){
		case FRIENDS:{
			if (Files.config().contains("Players." + name + ".Friend")){
				if (Files.config().getBoolean("Players." + name + ".Friend")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case VISIBILITY:{
			if (Files.config().contains("Players." + name + ".Visibility")){
				if (Files.config().getBoolean("Players." + name + ".Visibility")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case LETTER:{
			if (Files.config().contains("Players." + name + ".Letter1")){
				if (Files.config().getBoolean("Players." + name + ".Letter1")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case ACHIEVEMENTS:{
			if (Files.config().contains("Players." + name + ".Achievements")){
				if (Files.config().getBoolean("Players." + name + ".Achievements")){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}
		case FIXED:{
			if (Files.config().contains("Players." + name + ".Fixed")){
				if (Files.config().getBoolean("Players." + name + ".Fixed")){
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
		String name = p.getName();
		switch(t){
		case FRIENDS:{
			if (isEnabled(OptionType.FRIENDS, p.getName())){
				Files.config().set("Players." + name + ".Friend", false);
			}else{
				Files.config().set("Players." + name + ".Friend", true);
			}
		}
		break;
		case ACHIEVEMENTS:{
			if (isEnabled(OptionType.ACHIEVEMENTS, p.getName())){
				Files.config().set("Players." + name + ".Achievements", false);
			}else{
				Files.config().set("Players." + name + ".Achievements", true);
			}
		}
		break;
		case VISIBILITY:{
			if (isEnabled(OptionType.VISIBILITY, p.getName())){
				Files.config().set("Players." + name + ".Visibility", false);
			}else{
				Files.config().set("Players." + name + ".Visibility", true);
			}
		}
		break;
		case LETTER:{
			if (isEnabled(OptionType.LETTER, p.getName())){
				Files.config().set("Players." + name + ".Letter1", false);
			}else{
				Files.config().set("Players." + name + ".Letter1", true);
			}
		}
		case FIXED:{
			if (isEnabled(OptionType.FIXED, p.getName())){
				Files.config().set("Players." + name + ".Fixed", false);
			}else{
				Files.config().set("Players." + name + ".Fixed", true);
			}
		}
		}
		Files.saveConfig();
	}
}
