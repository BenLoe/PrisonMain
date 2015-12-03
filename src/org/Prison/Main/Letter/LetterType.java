package org.Prison.Main.Letter;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.quest.ActiveQuest;
import me.BenLoe.quest.QuestAPI;

import org.Prison.Main.Files;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Ranks.RankType;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public enum LetterType {

	A("A", 1), B("B", 2), C("C", 3), D("D", 4), E("E", 5), F("F", 6), G("G", 7), H("H", 8), I("I", 9), J("J", 10), K("K", 11),
	L("L", 12), M("M", 13), N("N", 14), O("O", 15), P("P", 16), Q("Q", 17), R("R", 18), S("S", 19), T("T", 20), U("U", 21), V("V", 22),
	W("W", 23), X("X", 24), Y("Y", 25), Z("Z", 26);
	
	private final String name;
	private final int i;
	
	private LetterType(String name, int i){
		this.name = name;
		this.i = i;
	}
	
	public static LetterType fromString(String name){
		for (LetterType t: values()){
			if (t.name.equalsIgnoreCase(name)){
				return t;
			}
		}
		return LetterType.A;
	}
	
	public int getInt(){
		return i;
	}
	public String getName(){
		return this.name;
	}
	public NeededType getNeeded(){
		switch(this.name){
		case "B":{
			return new NeededType(7500, 0, 0, this);
		}
		case "C":{
			return new NeededType(17000, 0, 0, this);
		}
		case "D":{
			return new NeededType(40000, 0, 0, this);
		}
		case "E":{
			return new NeededType(80000, 0, 0, this);
		}
		case "F":{
			return new NeededType(120000, 0, 0, this);
		}
		case "G":{
			return new NeededType(150000, 0, 0, this);
		}
		case "H":{
			return new NeededType(190000, 0, 0, this);
		}
		case "I":{
			return new NeededType(250000, 0, 0, this);
		}
		case "J":{
			return new NeededType(400000, 0, 0, this);
		}
		case "K":{
			return new NeededType(700000, 0, 0, this);
		}
		case "L":{
			return new NeededType(800000, 0, 0, this);
		}
		case "M":{
			return new NeededType(1300000, 65, 0, this);
		}
		case "N":{
			return new NeededType(2000000, 100, 0, this);
		}
		case "O":{
			return new NeededType(2800000, 160, 0, this);
		}
		case "P":{
			return new NeededType(3200000, 200, 6, this);
		}
		case "Q":{
			return new NeededType(4000000, 220, 10, this);
		}
		case "R":{
			return new NeededType(5000000, 260, 13, this);
		}
		case "S":{
			return new NeededType(8000000, 300, 20, this);
		}
		case "T":{
			return new NeededType(10000000, 340, 24, this);
		}
		case "U":{
			return new NeededType(11000000, 355, 28, this);
		}
		case "V":{
			return new NeededType(13000000, 365, 30, this);
		}
		case "W":{
			return new NeededType(15000000, 380, 34, this);
		}
		case "X":{
			return new NeededType(19000000, 395, 37, this);
		}
		case "Y":{
			return new NeededType(21000000, 420, 40, this);
		}
		case "Z":{
			return new NeededType(30000000, 500, 43, this);
		}
		}
		return new NeededType(1000000000, 1000000000, 1000000000, this);
	}
	public static void attemptRankup(Player p){
		LetterType t = getPlayerLetter(p);
		if (t.i == 26){
			p.sendMessage("§e[§bCorrupt Guard§e]: §aSeems like you are already in the max cell block!");
		}else{
			LetterType next = LetterType.A;
			for (LetterType l : values()){
				if (l.i == t.i + 1){
					next = l;
					break;
				}
			}
			if (t.i == 21){
				p.sendMessage("§e[§bCorrupt Guard§e]: §aLooks like your faster then the owners... That cell block isn't created yet.");
				return;
			}
			if (next.getNeeded().attemptFor(p)){
				Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
				Score oldscore = o.getScore(t.name);
				Score newscore = o.getScore(next.name);
				newscore.setScore(8);
				p.getScoreboard().resetScores(oldscore.getEntry());
				rankUp(p);
				p.sendMessage("§e[§bCorrupt Guard§e]: §aI see you have enough money to rankup! Let's get you moved to " + LetterType.getColoredString(next) + " §ablock.");
				MoneyAPI.removeMoney(p, next.getNeeded().getMoney());
				Stats.getStats(p.getName()).removeShards(next.getNeeded().getShards());
				QuestAPI.removeFavor(p, next.getNeeded().getFavor());
				if (QuestAPI.hasAActive(p)){
					ActiveQuest aq = ActiveQuest.getActive(p);
					if (aq.getNeededType() == me.BenLoe.quest.NeededType.RANKUP){
						QuestAPI.addProgress(p, 1);
					}
				}
			}
		}
	}
	public static LetterType getPlayerLetter(Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId() + ".Letter")){
			return fromString(Files.getDataFile().getString("Players." + p.getUniqueId() + ".Letter"));
		}else{
			Files.getDataFile().set("Players." + p.getUniqueId() + ".Letter", "A");
			return LetterType.A;
		}
	}
	
	public static String getColoredString(LetterType l){
		switch(l){
		case A:{
			return "§2§lA";
		}
		case B:{
			return "§2§lB";
		}
		case C:{
			return "§a§lC";
		}
		case D:{
			return "§a§lD";
		}
		case E:{
			return "§e§lE";
		}
		case F:{
			return "§e§lF";
		}
		case G:{
			return "§6§lG";
		}
		case H:{
			return "§6§lH";
		}
		case I:{
			return "§4§lI";
		}
		case J:{
			return "§4§lJ";
		}
		case K:{
			return "§d§lK";
		}
		case L:{
			return "§d§lL";
		}
		case M:{
			return "§5§lM";
		}
		case N:{
			return "§5§lN";
		}
		case O:{
			return "§1§lO";
		}
		case P:{
			return "§1§lP";
		}
		case Q:{
			return "§9§lQ";
		}
		case R:{
			return "§9§lR";
		}
		case S:{
			return "§3§lS";
		}
		case T:{
			return "§3§lT";
		}
		case U:{
			return "§b§lU";
		}
		case V:{
			return "§b§lV";
		}
		case W:{
			return "§0§lW";
		}
		case X:{
			return "§0§lX";
		}
		case Y:{
			return "§a§k|§8§lY§a§k|";
		}
		case Z:{
			return "§a§k|§8§lZ§a§k|";
		}
		}
		return null;
	}
	
	public static void rankUp(Player p){
		int rank = LetterType.getPlayerLetter(p).getInt();
		LetterType letter = LetterType.getPlayerLetter(p);
		if (letter == A){
			AchievementAPI.completeAchievement(p, AchievementMenu.STEPPING_UP);
		}
		for (LetterType t : values()){
			if (t.i == rank + 1){
				Files.getDataFile().set("Players." + p.getUniqueId() + ".Letter", t.name);
				Files.saveDataFile();
			}
		}
	}
}
