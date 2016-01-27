package org.Prison.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.mkremins.fanciful.FancyMessage;
import org.Prison.Punish.PunishAPI;
import org.Prison.Punish.Stats;
import org.Stats.main.PlayerFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class ChatMessages {

	public static List<String> inStaff = new ArrayList<String>();
	
	public static void execute(PlayerChatEvent event){
		event.setCancelled(true);
		if (inStaff.contains(event.getPlayer().getName())){
			sendStaffMessage(event.getPlayer(), event.getMessage().replace("&", "§"));
			return;
		}
		if (Main.chatSilenced){
			event.getPlayer().sendMessage(ChatColor.RED + "Chat has temporarily been silenced by staff.");
			return;
		}
		if (PunishAPI.ifPlayerIsMuted(event.getPlayer().getUniqueId().toString())){
			event.getPlayer().sendMessage("§c§lYou are muted for" + org.Prison.Punish.Cooldown.getTimeLeft(event.getPlayer().getUniqueId().toString(), "Mute") + "§c§l.");
		}else{
			if (event.getPlayer().hasPermission("Stats.trackmessage")){
				PlayerFile pf = org.Stats.main.Files.getPlayerFile(event.getPlayer().getName());
				List<String> log = new ArrayList<>();
				Calendar c = Calendar.getInstance();
				String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH);
				if (pf.getFile().contains(date + ".MessageLog")){
					log.addAll(pf.getFile().getStringList(date + ".MessageLog"));
				}
				log.add(0, event.getMessage());
				pf.getFile().set(date + ".MessageLog", log);
				pf.saveFile();
			}
			if (RankType.getRank(event.getPlayer()).equals(RankType.OWNER) || RankType.getRank(event.getPlayer()).equals(RankType.ADMIN)){
				event.setMessage(event.getMessage().replace("&", "§"));
			}
		for (Player p : Bukkit.getOnlinePlayers()){
			if (!Main.Tutorialint.containsKey(p.getName())){
				if (p.hasPermission("Punish.Notify")){
					if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
						if (RankType.getRank(event.getPlayer()).equals(RankType.NONE)){
							sendMessage1(p, event.getPlayer(), event.getMessage());
						}else if (RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ADMIN)){
							sendMessage2(p, event.getPlayer(), event.getMessage());
						}else{
						sendMessage2(p, event.getPlayer(), event.getMessage());
						}
						}else{
							if (RankType.getRank(event.getPlayer()).equals(RankType.NONE)){
								sendMessage3(p, event.getPlayer(), event.getMessage());
							}else if (RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ADMIN)){
								sendMessage4(p, event.getPlayer(), event.getMessage());
							}else{
								sendMessage4(p, event.getPlayer(), event.getMessage());
							}
						}
				}else{
			if (OptionAPI.isEnabled(OptionType.LETTER, p.getName())){
			if (RankType.getRank(event.getPlayer()).equals(RankType.NONE)){
				p.sendMessage("§8[" + LetterType.getColoredString(LetterType.getPlayerLetter(event.getPlayer())) + "§8] " + RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + event.getPlayer().getName() + ":" + "§f " + event.getMessage());	
			}else if (RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ADMIN)){
				p.sendMessage("§8[" + LetterType.getColoredString(LetterType.getPlayerLetter(event.getPlayer())) + "§8] " + RankType.toNiceName(RankType.getRank(event.getPlayer())) + RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + " " + event.getPlayer().getName() + ":" + "§f " + event.getMessage());
			}else{
			p.sendMessage("§8[" + LetterType.getColoredString(LetterType.getPlayerLetter(event.getPlayer())) + "§8] " + RankType.toNiceName(RankType.getRank(event.getPlayer())) + RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + " " + event.getPlayer().getName() + ":" + "§f " + event.getMessage());
			}
			}else{
				if (RankType.getRank(event.getPlayer()).equals(RankType.NONE)){
					p.sendMessage(RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + event.getPlayer().getName() + ":" + "§f " + event.getMessage());	
				}else if (RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ADMIN)){
					p.sendMessage(RankType.toNiceName(RankType.getRank(event.getPlayer())) + RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + " " + event.getPlayer().getName() + ":" + "§f " + event.getMessage());
				}else{
				p.sendMessage(RankType.toNiceName(RankType.getRank(event.getPlayer())) + RankType.getPlayerColor(RankType.getRank(event.getPlayer())) + " " + event.getPlayer().getName() + ":" + "§f " + event.getMessage());
				}
			}
				}
		}
		}
		}
	}
	
	
	public static void sendMessage1(Player p, Player p1, String message){
		FancyMessage f = new FancyMessage("§8[" + LetterType.getColoredString(LetterType.getPlayerLetter(p1)) + "§8] " + RankType.getPlayerColor(RankType.getRank(p1)))
			.then(RankType.getPlayerColor(RankType.getRank(p1)) + p1.getName())
				.tooltip("§cBans: x" + Stats.getBans(p1.getName()), "§6TempBans: x" + Stats.getTemps(p1.getName()), "§eMutes: x" + Stats.getMutes(p1.getName()), "§aKicks: x" + Stats.getKicks(p1.getName()), "§f§lClick to quick mute §b" + p1.getName() + "§f§l for 2 minutes.")
				.command("/mute " + p1.getName() + " 2 Quick mute")
			.then(":" + "§f ");
		List<String> s = Arrays.asList(message.split(" "));
		int at = 1;
		for (String word : s){
			int i = 0;
			for (char util : word.toCharArray()){
				if (Character.toString(util).equals(".")){
					i++;
				}
			}
			if (at == s.size()){
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word);
				}
			}else{
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word + " ");
				}	
			}
			at++;
		}
		f.send(p);
	}
	
	public static void sendMessage2(Player p, Player p1, String message){
		FancyMessage f = new FancyMessage("§8[" + LetterType.getColoredString(LetterType.getPlayerLetter(p1)) + "§8] " + RankType.toNiceName(RankType.getRank(p1)))
			.then(RankType.getPlayerColor(RankType.getRank(p1)) + " " + p1.getName())
				.tooltip("§cBans: x" + Stats.getBans(p1.getName()), "§6TempBans: x" + Stats.getTemps(p1.getName()), "§eMutes: x" + Stats.getMutes(p1.getName()), "§aKicks: x" + Stats.getKicks(p1.getName()), "§f§lClick to quick mute §b" + p1.getName() + "§f§l for 2 minutes.")
				.command("/mute " + p1.getName() + " 2 Quick mute")
				.then(":" + "§f ");
		List<String> s = Arrays.asList(message.split(" "));
		int at = 1;
		for (String word : s){
			int i = 0;
			for (char util : word.toCharArray()){
				if (Character.toString(util).equals(".")){
					i++;
				}
			}
			if (at == s.size()){
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word);
				}
			}else{
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word + " ");
				}	
			}
			at++;
		}
		f.send(p);
	}
	
	public static void sendMessage3(Player p, Player p1, String message){
		FancyMessage f = new FancyMessage(RankType.getPlayerColor(RankType.getRank(p1)) + p1.getName())
				.tooltip("§cBans: x" + Stats.getBans(p1.getName()), "§6TempBans: x" + Stats.getTemps(p1.getName()), "§eMutes: x" + Stats.getMutes(p1.getName()), "§aKicks: x" + Stats.getKicks(p1.getName()), "§f§lClick to quick mute §b" + p1.getName() + "§f§l for 2 minutes.")
				.command("/mute " + p1.getName() + " 2 Quick mute")
				.then(":" + "§f ");
		List<String> s = Arrays.asList(message.split(" "));
		int at = 1;
		for (String word : s){
			int i = 0;
			for (char util : word.toCharArray()){
				if (Character.toString(util).equals(".")){
					i++;
				}
			}
			if (at == s.size()){
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word);
				}
			}else{
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word + " ");
				}	
			}
			at++;
		}
		f.send(p);
	}
	
	public static void sendMessage4(Player p, Player p1, String message){
		FancyMessage f = new FancyMessage(RankType.toNiceName(RankType.getRank(p1)))
		.then(RankType.getPlayerColor(RankType.getRank(p1)) + " " + p1.getName())
			.tooltip("§cBans: x" + Stats.getBans(p1.getName()), "§6TempBans: x" + Stats.getTemps(p1.getName()), "§eMutes: x" + Stats.getMutes(p1.getName()), "§aKicks: x" + Stats.getKicks(p1.getName()), "§f§lClick to quick mute §b" + p1.getName() + "§f§l for 2 minutes.")
			.command("/mute " + p1.getName() + " 2 Quick mute")
			.then(":" + "§f ");
		List<String> s = Arrays.asList(message.split(" "));
		int at = 1;
		for (String word : s){
			int i = 0;
			for (char util : word.toCharArray()){
				if (Character.toString(util).equals(".")){
					i++;
				}
			}
			if (at == s.size()){
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word);
				}
			}else{
				if (i >= 2 || word.contains(".com") || word.contains(".ca")){
					if (!word.contains("http://") && !word.contains("https://")){
						f = f.then(word).link("http://" + word);	
					}else{
						f = f.then(word).link(word);	
					}
				}else{
					f = f.then(word + " ");
				}	
			}
			at++;
		}
		f.send(p);
	}
	
	public static void sendStaffMessage(Player sender, String message){
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.hasPermission("Staff.Chat")){
			p.sendMessage("§c§lStaff ➜ §8" + sender.getName() + ": §f" + message);
			}
		}
	}
}
