package org.Prison.Main.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.BenLoe.Blackmarket.Stats.Stats;
import me.BenLoe.Gadgets.Types.DeviceType;
import me.BenLoe.quest.QuestAPI;

import org.Prison.Tools.Files;
import org.Prison.Main.Currency.CrystalAPI;
import org.Prison.Main.Letter.LetterType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public enum PresentType {

	CORRUPT, ULTRA, FREE_RANKUP, CURRENCY;
	
	public static PresentType getKit(Player p){
		if (org.Prison.Main.Files.getDataFile().contains("Players." + p.getUniqueId() + ".BigPresent")){
			String s = org.Prison.Main.Files.getDataFile().getString("Players." + p.getUniqueId() + ".BigPresent");
			for (PresentType k : values()){
				if (s == k.name()){
					return k;
				}
			}
		}
		return CURRENCY;
	}
	
	public void setKit(Player p){
		org.Prison.Main.Files.getDataFile().set("Players." + p.getUniqueId() + ".BigPresent", this.name());
		org.Prison.Main.Files.saveDataFile();
	}
	
	public void givePresent(Player p){
		boolean received = false;
		switch(this){
		case CORRUPT:{
			p.sendMessage("§2§l[§4§lSanta§2§l]: §bDid somebody ask for some corrupt chests! Here you go!");
			DeviceType.CORRUPT_CHEST.addAmount(7, p);
			DeviceType.KEY.addAmount(7, p);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			received = true;
		}
		break;
		case ULTRA:{
			Random r = new Random();
			String type = "Ultra";
			int efficiency = (int) Math.round(((Files.config().getInt(type + ".MinEfficiency") + (Files.config().getInt(type + ".MaxEfficiency") - Files.config().getInt(type + ".MinEfficiency")) * Math.random()) + Math.round(0.2 * LetterType.getPlayerLetter(p).getInt())));
			int fortune = (int) Math.round(((Files.config().getInt(type + ".MinFortune") + (Files.config().getInt(type + ".MaxFortune") - Files.config().getInt(type + ".MinFortune")) * Math.random()) + Math.round(0.1 * LetterType.getPlayerLetter(p).getInt())));
			int unbreaking =(int) Math.round(((Files.config().getInt(type + ".MinUnbreaking") + (Files.config().getInt(type + ".MaxUnbreaking") - Files.config().getInt(type + ".MinUnbreaking")) * Math.random())));
			String ExtraDrops = String.valueOf(getRandomDouble(Files.config().getString(type + ".MinExtraDrops"), Files.config().getString(type + ".MaxExtraDrops")));
			String AncientChance = String.valueOf(getRandomDouble(Files.config().getString(type + ".MinAncient"), Files.config().getString(type + ".MaxAncient")));
			int Speed = (int) Math.round(((Files.config().getInt(type + ".MinSpeed") + (Files.config().getInt(type + ".MaxSpeed") - Files.config().getInt(type + ".MinSpeed")) * Math.random()) + Math.round(0.03 * LetterType.getPlayerLetter(p).getInt())));
			int Enchants = (int) Math.round(((Files.config().getInt(type + ".MinEnchants") + (Files.config().getInt(type + ".MaxEnchants") - Files.config().getInt(type + ".MinEnchants")) * Math.random()) + Math.round(0.1 * LetterType.getPlayerLetter(p).getInt())));
			String extrautil = String.valueOf(ExtraDrops);
			if (extrautil.length() == 3){
				extrautil = extrautil + "0";
				ExtraDrops = extrautil;
			}
			String ancientutil = String.valueOf(AncientChance);
			if (ancientutil.length() == 3){
				ancientutil = ancientutil + "0";
				AncientChance = ancientutil;
			}
			
			List<String> names = Files.config().getStringList(type + ".Names");
			String name = names.get(r.nextInt(names.size()));
			
			ChatColor c = ChatColor.DARK_RED;
			ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
			ItemMeta itemm = item.getItemMeta();
			itemm.setDisplayName(c + name);
			List<String> lore = new ArrayList<String>();
			lore.add("§9§lStatistics:");
			lore.add(ChatColor.GRAY + " Efficiency: " + ChatColor.GREEN + efficiency);
			lore.add(ChatColor.GRAY + " Fortune: " + ChatColor.GREEN + "" + fortune);
			lore.add(ChatColor.GRAY + " Unbreaking: " + ChatColor.GREEN + unbreaking);
			lore.add(" ");
			lore.add(ChatColor.GRAY + " Extra Drops: " + ChatColor.RED + String.valueOf(ExtraDrops) + "%");
			lore.add(ChatColor.GRAY + " Ancient Chance: " + ChatColor.RED + AncientChance + "%");
			lore.add(ChatColor.GRAY + " Speed: " + ChatColor.YELLOW + "+" + Speed);
			lore.add(ChatColor.GREEN + " Enchants Left: " + ChatColor.YELLOW + Enchants);
			itemm.setLore(lore);
			itemm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(itemm);
			item.addUnsafeEnchantment(Enchantment.DIG_SPEED, efficiency);
			item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, fortune);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, unbreaking);
			p.getInventory().setItem(p.getInventory().getHeldItemSlot(), item);
			p.updateInventory();
			received = true;
			p.sendMessage("§2§l[§4§lSanta§2§l]: §bDid somebody ask for a Ultra tool! Here you go!");
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
		}
		break;
		case FREE_RANKUP:{
			LetterType Current = LetterType.getPlayerLetter(p);
			LetterType next = LetterType.A;
			for (LetterType l : LetterType.values()){
				if (l.getInt() == Current.getInt() + 1){
					next = l;
					break;
				}
			}
			if (Current.getInt() != 26 && Current.getInt() != 22){
				Objective o = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);	
				Score oldscore = o.getScore(Current.getName());
				Score newscore = o.getScore(next.getName());
				newscore.setScore(8);
				p.getScoreboard().resetScores(oldscore.getEntry());
				LetterType.rankUp(p);
				received = true;
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
				p.sendMessage("§2§l[§4§lSanta§2§l]: §bDid somebody ask for a Free Rankup! Here you go!");
			}else{
				p.sendMessage("§2§l[§4§lSanta§2§l]: §cIm sorry but you are at the max cell block so far so I can't let you rankup.");
			}
		}
		break;
		case CURRENCY:{
			Stats.getStats(p.getUniqueId()).addShards(200);
			CrystalAPI.addCrystals(p, 5000);
			QuestAPI.addFavor(p, 20);
			received = true;
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			p.sendMessage("§2§l[§4§lSanta§2§l]: §bDid somebody ask for a Currency Package! Here you go!");
			p.sendMessage("§2§l[§4§lSanta§2§l]: §9+ 200 Shards");
			p.sendMessage("§2§l[§4§lSanta§2§l]: §e+ 20 Favor points");
			p.sendMessage("§2§l[§4§lSanta§2§l]: §b+ 5000 Crystals");
		}
		break;
		}
		if (received){
			org.Prison.Main.Files.getDataFile().set("Players." + p.getUniqueId() + ".Received", true);
			org.Prison.Main.Files.saveDataFile();
		}
	}
	
	public static double getRandomDouble(String min, String max){
		int mainmin = Integer.parseInt((min).split(",")[0]);
		int mainmax = Integer.parseInt((max).split(",")[0]);
		int secmin = Integer.parseInt((min).split(",")[1]);
		int secmax = Integer.parseInt((max).split(",")[1]);
		
		double main = (int) Math.round(mainmin + (mainmax - mainmin) * Math.random());
		double sec = (int) Math.round((secmin + (secmax - secmin) * Math.random()));
		
		
		double random = main + (sec/100);
		return random;
	}
	
}
