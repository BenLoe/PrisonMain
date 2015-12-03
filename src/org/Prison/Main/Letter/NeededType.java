package org.Prison.Main.Letter;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.quest.QuestAPI;

import org.Prison.Main.Currency.MoneyAPI;
import org.bukkit.entity.Player;

public class NeededType {

	private int money,shards, favor;
	private LetterType t;
	
	public String tag = "§e[§bCorrupt Guard§e]: ";
	
	public NeededType(int money, int shards, int favor, LetterType t){
		this.money = money;
		this.shards = shards;
		this.favor = favor;
		this.t = t;
	}
	
	public int getMoney(){
		return money;
	}
	
	public int getShards(){
		return shards;
	}
	
	public int getFavor(){
		return favor;
	}
	
	public boolean needsShards(){
		if (shards > 0){
			return true;
		}
		return false;
	}
	
	
	public boolean needsFavor(){
		if (favor > 0){
			return true;
		}
		return false;
	}
	
	public boolean attemptFor(Player p){
		int money = getMoney();
		int shards = getShards();
		
		if (MoneyAPI.getMoney(p) >= money && Stats.getStats(p.getName()).getShards() >= shards && QuestAPI.getFavor(p) >= favor){
			return true;
		}else{
			String needed = "§a" + money + "$§c";
			if (needsShards()){
				needed += ", §9" + shards + " Pickaxe shards§c";
			}
			if (needsFavor()){
				needed += ", §e" + favor + " Favor points§c";
			}
			
			p.sendMessage(tag + "§cYou don't have enough to rankup to " + LetterType.getColoredString(t) + "§c. You need " + needed + ".");
			if ((money - MoneyAPI.getMoney(p) > 0)){
			p.sendMessage(tag + "§a" + (money - MoneyAPI.getMoney(p)) + "§c more dollars.");
			}
			if (needsShards() && (shards - Stats.getStats(p.getName()).getShards()) > 0){
				p.sendMessage(tag + "§9" + (shards - Stats.getStats(p.getName()).getShards()) + "§c more pickaxe shards.");
			}
			if (needsFavor() && (favor - QuestAPI.getFavor(p)) > 0){
				p.sendMessage(tag + "§e" + (favor - QuestAPI.getFavor(p)) + "§c more favor points.");
			}
			return false;
		}
	}
}
