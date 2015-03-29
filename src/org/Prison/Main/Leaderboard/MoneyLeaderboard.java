package org.Prison.Main.Leaderboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.Prison.Main.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class MoneyLeaderboard {

	@SuppressWarnings("deprecation")
	public static Map<String,Integer> getLeaderboard(){
		Map<String,Integer> moneys = new HashMap<String,Integer>();
		CompareValues comp = new CompareValues(moneys);
		TreeMap<String,Integer> sorted = new TreeMap<String,Integer>(comp);
		for (String s : Files.getDataFile().getStringList("MoneyPlayers")){
			try {
				moneys.put(s, (int) Math.round(Economy.getMoney(s)));
			} catch (UserDoesNotExistException e) {
				moneys.put(s, 0);
			}
		}
		
		sorted.putAll(moneys);
		return sorted;
	}
	
	@SuppressWarnings("deprecation")
	public static void updateSigns(){
		Map<String,Integer> leadersutil = getLeaderboard();
		List<String> leaders = new ArrayList<String>();
		for (Entry<String,Integer> e : leadersutil.entrySet()){
			leaders.add((String) e.getKey());
		}
		for (int i = 0; i <= 9 ; i++){
			int place = i + 1;
			Location loc = new Location(Bukkit.getWorld("PrisonMap"), Files.getDataFile().getInt("MoneySign" + place + ".x"), Files.getDataFile().getInt("MoneySign" + place + ".y"), Files.getDataFile().getInt("MoneySign" + place + ".z"));
			Sign sign = (Sign) loc.getBlock().getState();
			String name = leaders.get(i);
			int money = 0;
			try {
				money = (int) Math.round(Economy.getMoney(name));
			} catch (UserDoesNotExistException e1) {
				money = 0;
			}
			sign.setLine(0, "§8§m--§a§l" + place + "§8§m--");
			sign.setLine(1, "§a§l" + trimName(name));
			sign.setLine(2, "§8" + money + "§a$");
			sign.setLine(3, "§7§oMost money");
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
