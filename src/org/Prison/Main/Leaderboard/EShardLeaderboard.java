package org.Prison.Main.Leaderboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.TreeMap;

import me.BenLoe.quest.QuestAPI;

import org.Prison.Main.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class EShardLeaderboard {

	public static Map<String,Integer> getLeaderboard(){
		Map<String,Integer> moneys = new HashMap<String,Integer>();
		CompareValues comp = new CompareValues(moneys);
		TreeMap<String,Integer> sorted = new TreeMap<String,Integer>(comp);
		for (String s : Files.getDataFile().getStringList("MoneyPlayers")){
			
			moneys.put(s, QuestAPI.getFavor(s));
		}
		
		sorted.putAll(moneys);
		return sorted;
	}
	
	public static void updateSigns(){
		Map<String,Integer> leadersutil = getLeaderboard();
		List<String> leaders = new ArrayList<String>();
		for (Entry<String,Integer> e : leadersutil.entrySet()){
			leaders.add((String) e.getKey());
		}
		for (int i = 0; i <= 9 ; i++){
			int place = i + 1;
			Location loc = new Location(Bukkit.getWorld("PrisonMap"), -184, 60, 230).subtract(i, 2, 0);
			Sign sign = (Sign) loc.getBlock().getState();
			String name = Files.getDataFile().getString("Players." + leaders.get(i) + ".Name");
			String uuid = leaders.get(i);
			int money = QuestAPI.getFavor(uuid);
			sign.setLine(0, "§8§m--§e§l" + place + "§8§m--");
			sign.setLine(1, "§e§l" + trimName(name));
			sign.setLine(2, "§8" + money);
			sign.setLine(3, "§7§oMost Favors");
			sign.update();
		}
	}
	
	public static String trimName(String s){
		if (s.length() > 15){
			return s.substring(0, 15);
		}
		return s;
	}
}
