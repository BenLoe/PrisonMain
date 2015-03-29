package org.Prison.Main.Letter;

import me.BenLoe.Blackmarket.Stats.Stats;

import org.Prison.Main.Currency.MoneyAPI;
import org.bukkit.entity.Player;

public class NeededType {

	private int money,shards,eshards, favor;
	private LetterType t;
	
	public String tag = "§e[§bCorrupt Guard§e]: ";
	
	public NeededType(int money, int shards, int eshards,  int favor, LetterType t){
		this.money = money;
		this.shards = shards;
		this.eshards = eshards;
		this.favor = favor;
		this.t = t;
	}
	
	public int getMoney(){
		return money;
	}
	
	public int getShards(){
		return shards;
	}
	
	public int getEShards(){
		return eshards;
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
	
	public boolean needsEShards(){
		if (eshards > 0){
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
		int eshards = getEShards();
		
		if (MoneyAPI.getMoney(p) >= money && Stats.getStats(p.getName()).getShards() >= shards && Stats.getStats(p.getName()).getEnchantedShards() >= eshards){
			return true;
		}else{
			String needed = "§a" + money + "$§c";
			if (needsShards()){
				needed += ", §9" + shards + " Pickaxe shards§c";
			}
			if (needsEShards()){
				needed += ", §b" + eshards + " Enchanted shards§c";
			}
			
			p.sendMessage(tag + "§cYou don't have enough to rankup to " + LetterType.getColoredString(t) + "§c. You need " + needed + ".");
			if ((money - MoneyAPI.getMoney(p) > 0)){
			p.sendMessage(tag + "§a" + (money - MoneyAPI.getMoney(p)) + "§c more dollars.");
			}
			if (needsShards() && (shards - Stats.getStats(p.getName()).getShards()) > 0){
				p.sendMessage(tag + "§9" + (shards - Stats.getStats(p.getName()).getShards()) + "§c more pickaxe shards.");
			}
			if (needsEShards() && (eshards - Stats.getStats(p.getName()).getEnchantedShards()) > 0){
				p.sendMessage(tag + "§9" + (eshards - Stats.getStats(p.getName()).getEnchantedShards()) + "§c more enchanted shards.");
			}
			return false;
		}
	}
}
