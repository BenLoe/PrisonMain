package org.Prison.Main.Tutorial;

import org.Prison.Main.Main;
import org.Prison.Main.RegionChecker.VisibleLines;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Tutorial {

	public static void start(Player p){
		Main.Tutorialint.put(p.getName(), 1);
		p.teleport(Main.getLocation("Tutorial1").add(0.5, 0, 0.5));
		VisibleLines.in.add(p.getName());
		for (Player p1 : Bukkit.getOnlinePlayers()){
			p1.hidePlayer(p);
		}
		
	}
	
	public static void check(){
		if (!Main.Tutorialint.isEmpty()){
		for (final Player p : Bukkit.getOnlinePlayers()){
			if (Main.Tutorialint.containsKey(p.getName())){
				int i = Main.Tutorialint.get(p.getName());
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("§b§l§m----------------" + "§b§l[" + "§f§lTutorial" + "§b§l]" + "§b§l§m----------------");
				p.sendMessage("   ");
				switch(i){
				case 1:{
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 2:{
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 3:{
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 4:{
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 5:{
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage(" §fHey there this is a quick tutorial to");
					p.sendMessage(" §fexplain all the general aspects of our");
					p.sendMessage(" §fserver.");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 6:{
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage(" §fHey there this is a quick tutorial to");
					p.sendMessage(" §fexplain all the general aspects of our");
					p.sendMessage(" §fserver.");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 7:{
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage(" §fHey there this is a quick tutorial to");
					p.sendMessage(" §fexplain all the general aspects of our");
					p.sendMessage(" §fserver.");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 8:{
					p.sendMessage("§a§l>§fTutorial");
					p.sendMessage(" §fHey there this is a quick tutorial to");
					p.sendMessage(" §fexplain all the general aspects of our");
					p.sendMessage(" §fserver.");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 9:{
					p.teleport(Main.getLocation("Tutorial2").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 10:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 11:{
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fWhen mining in the mines you have a");
					p.sendMessage(" §fsmall chance of finding a ancient pickaxe.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 12:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fWhen mining in the mines you have a");
					p.sendMessage(" §fsmall chance of finding a ancient pickaxe.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 13:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fWhen mining in the mines you have a");
					p.sendMessage(" §fsmall chance of finding a ancient pickaxe.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 14:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fWhen mining in the mines you have a");
					p.sendMessage(" §fsmall chance of finding a ancient pickaxe.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 15:{
					p.teleport(Main.getLocation("Tutorial3").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 16:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 17:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 18:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");	
				}
				break;
				case 19:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");	
				}
				break;
				case 20:{
					p.sendMessage("§a§l>§fAncient Tools");
					p.sendMessage(" §fGive the item identifier the pickaxe,");
					p.sendMessage(" §fhe will identify what kind of pickaxe it is,");
					p.sendMessage(" §fand what stats it has.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp identify");
				}
				break;
				case 21:{
					p.teleport(Main.getLocation("Tutorial4").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 22:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 23:{
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 24:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 25:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 26:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 27:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 28:{
					p.sendMessage("§a§l>§fEnchanter");
					p.sendMessage(" §fSome pickaxes will allow you to enchant them.");
					p.sendMessage(" §fGive them to the enchanter to get them enchanted.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 29:{
					p.teleport(Main.getLocation("Tutorial5").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				}
				break;
				case 30:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
				break;
				case 31:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 32:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 33:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 34:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage(" §fThey will buy the items for an appropriate");
					p.sendMessage(" §fprice.");
					p.sendMessage("   ");
					break;
				case 35:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage(" §fThey will buy the items for an appropriate");
					p.sendMessage(" §fprice.");
					p.sendMessage("   ");
					break;
				case 36:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage(" §fThey will buy the items for an appropriate");
					p.sendMessage(" §fprice.");
					p.sendMessage("   ");
					break;
				case 37:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage(" §fThey will buy the items for an appropriate");
					p.sendMessage(" §fprice.");
					p.sendMessage("   ");
					break;
				case 38:
					p.sendMessage("§a§l>§fSelling Items");
					p.sendMessage(" §fEvery mine has some item buyers, give them");
					p.sendMessage(" §fthe items you want to sell.");
					p.sendMessage(" §fThey will buy the items for an appropriate");
					p.sendMessage(" §fprice.");
					p.sendMessage("   ");
					break;
				case 39:
					p.teleport(Main.getLocation("Tutorial6").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 40:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 41:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 42:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 43:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 44:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 45:
					p.teleport(Main.getLocation("Tutorial7").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 46:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 47:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 48:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 49:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 50:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 51:
					p.sendMessage("§a§l>§fCell Block");
					p.sendMessage(" §fThat cell block back there is yours.");
					p.sendMessage(" §fYou can store items in your enderchest, and");
					p.sendMessage(" §fif your a donator, you can use that chest.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp cell");
					break;
				case 52:
					p.teleport(Main.getLocation("Tutorial8").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 53:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 54:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 55:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 56:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 57:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 58:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 59:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 60:
					p.sendMessage("§a§l>§fRanking Up");
					p.sendMessage(" §fBack there is a corrupt cop,");
					p.sendMessage(" §fgive him enough money and he'll rank you");
					p.sendMessage(" §fup.");
					p.sendMessage("   ");
					p.sendMessage(" §b/warp rankup");
					break;
				case 61:
					p.teleport(Main.getLocation("Tutorial9").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 62:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 63:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 64:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 65:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 66:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 67:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 68:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 69:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 70:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 71:
					p.sendMessage("§a§l>§fSpeed Trait");
					p.sendMessage(" §fYour speed level changes how fast you are.");
					p.sendMessage(" §fTo level up, just simply walk around, the");
					p.sendMessage(" §fmore you walk, the faster you level up.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 72:
					p.teleport(Main.getLocation("Tutorial10").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 73:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 74:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 75:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 76:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 77:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 78:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage(" §fTo level up, just read these books (right click).");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 79:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage(" §fTo level up, just read these books (right click).");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 80:
					p.sendMessage("§a§l>§fIntellect Trait");
					p.sendMessage(" §fYour intellect level changes the chance of");
					p.sendMessage(" §fgetting a good pickaxe when identifying.");
					p.sendMessage(" §fTo level up, just read these books (right click).");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 81:
					p.teleport(Main.getLocation("Tutorial12").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 82:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 83:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 84:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 85:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 86:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 87:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 88:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage(" §fSmelting your pickaxes gives you");
					p.sendMessage(" §fpickaxe shards, used to repair pickaxes.");
					p.sendMessage("   ");
					break;
				case 89:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage(" §fSmelting your pickaxes gives you");
					p.sendMessage(" §fpickaxe shards, used to repair pickaxes.");
					p.sendMessage("   ");
					break;
				case 90:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage(" §fSmelting your pickaxes gives you");
					p.sendMessage(" §fpickaxe shards, used to repair pickaxes.");
					p.sendMessage("   ");
					break;
				case 91:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage(" §fSmelting your pickaxes gives you");
					p.sendMessage(" §fpickaxe shards, used to repair pickaxes.");
					p.sendMessage("   ");
					break;
				case 92:
					p.sendMessage("§a§l>§fBlackmarket");
					p.sendMessage(" §fThe blackmarket is the place where you");
					p.sendMessage(" §fcan repair and smelt your pickaxes.");
					p.sendMessage(" §fSmelting your pickaxes gives you");
					p.sendMessage(" §fpickaxe shards, used to repair pickaxes.");
					p.sendMessage("   ");
					break;
				case 93:
					p.teleport(Main.getLocation("Tutorial11").add(0.5, 0, 0.5));
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 94:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 95:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 96:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 97:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage("   ");
					p.sendMessage("   ");
					p.sendMessage("   ");
					break;
				case 98:
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage(" §fIf you have any more questions, just ask");
					p.sendMessage(" §fthe staff.");
					p.sendMessage("   ");
					break;
				case 99:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage(" §fIf you have any more questions, just ask");
					p.sendMessage(" §fthe staff.");
					p.sendMessage("   ");
					break;
				case 100:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage(" §fIf you have any more questions, just ask");
					p.sendMessage(" §fthe staff.");
					p.sendMessage("   ");
					break;
				case 101:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage(" §fIf you have any more questions, just ask");
					p.sendMessage(" §fthe staff.");
					p.sendMessage("   ");
					break;
				case 102:
					p.sendMessage("§a§l>§fEnd of Tutorial");
					p.sendMessage(" §fThats pretty much it, go on and have");
					p.sendMessage(" §ffun.");
					p.sendMessage(" §fIf you have any more questions, just ask");
					p.sendMessage(" §fthe staff.");
					p.sendMessage("   ");
					p.teleport(Main.getLocation("spawn").add(0.5, 0, 0.5));
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
						public void run(){
							p.sendMessage("§bYou can move now.");
							AchievementAPI.completeAchievement(p, AchievementMenu.READING_THE_MANUAL);
						}
					}, 20l);
					break;
				}
				p.sendMessage("   ");
				p.sendMessage("§b§l§m---------------" + "§7§o/exit to leave" + "§b§l§m---------------");
				int newi = i + 1;
				Main.Tutorialint.remove(p.getName());
				if (newi != 103){
					Main.Tutorialint.put(p.getName(), newi);
				}
			}
		}
		}
	}
}
