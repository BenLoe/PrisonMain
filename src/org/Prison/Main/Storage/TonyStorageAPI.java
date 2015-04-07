package org.Prison.Main.Storage;

import org.Prison.Main.Files;
import org.bukkit.entity.Player;

public class TonyStorageAPI {

	
	public static boolean hasAccessTo(Player p, int id){
		if (Files.getDataFile().contains("Players." + p.getName() + ".Chest" + id)){
			if (Files.getDataFile().getBoolean("Players." + p.getName() + ".Chest" + id)){
				return true;
			}
		}
		return false;
	}
}
