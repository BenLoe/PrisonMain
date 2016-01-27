package org.Prison.Main.Ranks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public enum RankType {

	VIP, ADMIN, OWNER, ELITE, ULTRA, MODERATOR, JRMOD, BUILDER, NONE;
	
	

	public static RankType getRank(Player p){
		if (p.hasPermission("Rank.Owner")){
			return RankType.OWNER;
		}else if (p.hasPermission("Rank.Admin")){
			return RankType.ADMIN;
		}else if (p.hasPermission("Rank.Moderator")){
			return RankType.MODERATOR;
		}else if(p.hasPermission("Rank.JrMod")){
			return RankType.JRMOD;
		}else if (p.hasPermission("Rank.Builder")){
			return RankType.BUILDER;
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
		case BUILDER:{
			return "§9§lBUILDER";
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
		case BUILDER:{
			return "§9";
		}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public void setPlayerName(Player p){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();
		Team team = board.getTeam("Normal");
		switch(this){
		case ADMIN:
			team = board.getTeam("Admin");
			break;
		case ELITE:
			team = board.getTeam("Elite");
			break;
		case JRMOD:
			team = board.getTeam("JrMod");
			break;
		case MODERATOR:
			team = board.getTeam("Mod");
			break;
		case NONE:
			break;
		case OWNER:
			team = board.getTeam("Owner");
			break;
		case ULTRA:
			team = board.getTeam("Ultra");
			break;
		case VIP:
			team = board.getTeam("Vip");
			break;
		default:
			break;
		
		}
		team.addPlayer(Bukkit.getOfflinePlayer(p.getName()));
	}
}
