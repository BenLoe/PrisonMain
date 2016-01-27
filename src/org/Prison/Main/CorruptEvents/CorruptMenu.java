package org.Prison.Main.CorruptEvents;

import java.util.ArrayList;
import java.util.List;

import me.BenLoe.quest.QuestAPI;
import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Files;
import org.Prison.Main.Main;
import org.Prison.Main.ParticleEffect;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Letter.LetterType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CorruptMenu {
	
	public static List<String> inMenu = new ArrayList<>();
	public static List<String> teleporting = new ArrayList<>();

	public static void attempt(Player p){
		if (LetterType.getPlayerLetter(p).getInt() > 12){
			openMenu(p);
		}else{
			p.sendMessage("§7[§3Shady Inmate§7]: §cLegend of another pit, a corrupt pit, is written in many ancient scrolls. It's a dangerous... but rewarding place. Come back when you are atleast §5§lM block §cand I might be able to get you there.");
		}
	}
	
	public static void openMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§3Shady Inmate");
		ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta itemm = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§7This shard is said to be able to bring you");
		lore.add("§7to the corrupt pit when activated. §7§o(One time purchase)");
		lore.add("");
		lore.add("§cWarning: The corrupt pit is not a place for");
		lore.add("§cweak players, damage is enabled and strong mobs");
		lore.add("§cspawn. Armor and weapons are needed.");
		lore.add("");
		lore.add("§7Cost:");
		lore.add("§e25 favor points.");
		lore.add("§a1,000,000$");
		if (MoneyAPI.getMoney(p) >= 1000000 && QuestAPI.getFavor(p) >= 25){
			lore.add("§aClick to buy 1 corrupt shard.");
		}else{
			lore.add("§cClick to buy 1 corrupt shard.");
		}
		itemm.setLore(lore);
		itemm.setDisplayName("§4§l§khh§4§l Corrupt Shard §4§l§khh");
		item.setItemMeta(itemm);
		item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
		inv.setItem(13, item);
		p.openInventory(inv);
		inMenu.add(p.getName());
	}
	
	public static void wasClicked(Player p){
		if (MoneyAPI.getMoney(p) >= 1000000 && QuestAPI.getFavor(p) >= 25){
			MoneyAPI.removeMoney(p, 1000000);
			QuestAPI.removeFavor(p, 25);
			p.sendMessage("§7[§3Shady Inmate§7]: §aI wish you luck. §a§o(Shard purchased)");
			ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
			ItemMeta itemm = item.getItemMeta();
			List<String> lore = new ArrayList<>();
			lore.add("");
			lore.add("§7This shard is said to be able to bring you");
			lore.add("§7to the corrupt pit when activated. §7§o(One time purchase)");
			lore.add("");
			lore.add("§cWarning: The corrupt pit is not a place for");
			lore.add("§cweak players, damage is enabled and strong mobs");
			lore.add("§cspawn. Armor and weapons are needed.");
			lore.add("");
			itemm.setLore(lore);
			itemm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itemm.setDisplayName("§4§l§khh§4§l Corrupt Shard §4§l§khh");
			item.setItemMeta(itemm);
			item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
			p.getInventory().addItem(item);
			p.updateInventory();
			inMenu.remove(p.getName());
			p.closeInventory();
			Files.getDataFile().set("Players." + p.getUniqueId().toString() + ".CorruptAcess", true);
			Files.saveDataFile();
		}else{
			p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
		}
	}
	
	public static void teleport(final Player p){
		if (Files.getDataFile().contains("Players." + p.getUniqueId().toString() + ".CorruptAcess")){
			p.sendMessage(ChatColor.RED + "You've never bought a corrupt shard so you can't use one.");
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 12));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5 * 20, -20));
		p.playSound(p.getLocation(), Sound.PORTAL, 1f, 1f);
		ParticleEffect.PORTAL.display(0.5f, 1f, 0.5f, 0.3f, 300, p.getLocation(), 20);
		teleporting.add(p.getName());
		final List<Location> util = new ArrayList<>();
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
	                final double size = 0.4;
	                final int points = 30; //amount of points to be generated
	                for (int i = 0; i < 360; i += 360 / points){
	                double angle = (i * Math.PI / 180);
	                double x = size * Math.cos(angle);
	                double z = size * Math.sin(angle);
	                    Main.particles.add(p.getLocation().clone().add(x, 0.2, z));
	                    Main.particles.add(p.getLocation().clone().add(x, 1.1, z));
	                    util.add(p.getLocation().clone().add(x, 0.2, z));
	                    util.add(p.getLocation().clone().add(x, 1.1, z));
	                }
			}
		}, 20l);
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				p.teleport(Main.getLocation("NetherSpawn"));
				p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1f, 1f);
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 45, 2));
				ParticleEffect.SPELL_WITCH.display(1f, 1f, 1f, 0.5f, 50, p.getLocation(), 20);
				Main.particles.removeAll(util);
				teleporting.remove(p.getName());
			}
		}, 5 * 20l);
	}
	
}
