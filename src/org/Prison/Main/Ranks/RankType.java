package org.Prison.Main.Ranks;

import org.bukkit.entity.Player;

public enum RankType {

	VIP, ADMIN, OWNER, ELITE, ULTRA, MODERATOR, JRMOD, NONE;
	
	

	public static RankType getRank(Player p){
		if (p.hasPermission("Rank.Owner")){
			return RankType.OWNER;
		}else if (p.hasPermission("Rank.Admin")){
			return RankType.ADMIN;
		}else if (p.hasPermission("Rank.Moderator")){
			return RankType.MODERATOR;
		}else if(p.hasPermission("Rank.JrMod")){
			return RankType.JRMOD;
		}else if(p.hasPermission("Rank.Ultra")){
			return RankType.ULTRA;
		}else if (p.hasPermission("Rank.Elite")){
			return RankType.ELITE;
		}else if(p.hasPermission("Rank.Vip")){
			return RankType.VIP;
		}else{
			return RankType.NONE;
		}
	}
	public static String toNiceName(RankType r){
		switch(r){
		case VIP:{
			return "§3§lVIP";
		}
		case ELITE:{
			return "§a§lELITE";
		}
		case MODERATOR:{
			return "§4§lMOD";
		}
		case ADMIN:{
			return "§c§lADMIN";
		}
		case OWNER:{
			return "§c§lOWNER";
		}
		case ULTRA:{
			return "§6§lULTRA";
		}
		case NONE:{
			return "NONE";
		}
		case JRMOD:{
			return "§4§lJR.MOD";
		}
		}
		return null;
	}
	
	public static String getPlayerColor(RankType r){
		switch(r){
		case VIP:{
			return "§3";
		}
		case ELITE:{
			return "§a";
		}
		case MODERATOR:{
			return "§4";
		}
		case ADMIN:{
			return "§c";
		}
		case OWNER:{
			return "§c";
		}
		case ULTRA:{
			return "§6";
		}
		case NONE:{
			return "§7";
		}
		case JRMOD:{
			return "§4";
		}
		}
		return null;
	}
}
