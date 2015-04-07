package org.Prison.Main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.InfoBoard.InfoBoard;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.VisibleLines;
import org.Prison.Main.Traits.SpeedTrait;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinEvent {
	
	public JoinEvent(){
		
	}
	public void Join(PlayerJoinEvent event){
		final Player p = event.getPlayer();
		SpeedTrait.setCorrectSpeed(p);
		event.setJoinMessage(null);
		if (Files.getDataFile().contains("Players." + p.getName() + ".Votes")){
			int current = Files.getDataFile().getInt("Players." + p.getName() + ".Votes");
			if (current >= 50){
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
					AchievementAPI.completeAchievement(p, AchievementMenu.THE_VOTING_KING);
					}
				},45l);
			}
		}
		if (RankType.getRank(p).equals(RankType.ELITE) || RankType.getRank(p).equals(RankType.VIP)){
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
				public void run(){
					AchievementAPI.completeAchievement(p, AchievementMenu.LIVING_THE_FANCY_LIFE);
					AchievementAPI.completeAchievement(p, AchievementMenu.LIVING_THE_EXTRA_FANCY_LIFE);
				}
			}, 45l);
		}
		ItemAPI.givePlayerItems(p);
		if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p.getName())){
			for (Player p1 : Bukkit.getOnlinePlayers()){
				if (!RankType.getRank(p).equals(RankType.NONE) && !RankType.getRank(p).equals(RankType.VIP)){
					p1.sendMessage(RankType.toNiceName(RankType.getRank(p)) + " " + RankType.getPlayerColor(RankType.getRank(p)) + p.getName() + " §ejoined.");
				}
				if (VisibleLines.in.contains(p1.getName())){
					p.hidePlayer(p1);
				}
				if (!FriendAPI.getFriendList(p.getName()).contains(p1.getName())){
					p.hidePlayer(p1);
				}else{
					p1.sendMessage(ChatColor.YELLOW + p.getName() + " joined the server.");
					p.showPlayer(p1);
					
				}
				if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
					if (!FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
					p1.hidePlayer(p);
					}else{
						p1.showPlayer(p);
					}
				}
			}
		}else{
			for (Player p1 : Bukkit.getOnlinePlayers()){
				if (!RankType.getRank(p).equals(RankType.NONE) && !RankType.getRank(p).equals(RankType.VIP)){
					p1.sendMessage(RankType.toNiceName(RankType.getRank(p)) + " " + RankType.getPlayerColor(RankType.getRank(p)) + p.getName() + " §ejoined.");
				}
				if (VisibleLines.in.contains(p1.getName())){
					p.hidePlayer(p1);
				}
				if (FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
					p1.sendMessage(ChatColor.YELLOW + p.getName() + " joined the server.");
				}
				if (!OptionAPI.isEnabled(OptionType.VISIBILITY, p1.getName())){
					if (FriendAPI.getFriendList(p1.getName()).contains(p.getName())){
					p1.showPlayer(p);
				}else{
				   p1.hidePlayer(p);
				   
				}
				}
			}
			
			CraftPlayer cp = (CraftPlayer) p;
			IChatBaseComponent Header = ChatSerializer.a(new FancyMessage("§8§l§m----§4§l[ The Pit MC ]§8§l§m----").toJSONString());
			IChatBaseComponent Footer = ChatSerializer.a(new FancyMessage("§eFor more information visit our website: §bwww.ThePitMc.com").toJSONString());
			 PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(Header);
			 
		        try {
		            Field field = headerPacket.getClass().getDeclaredField("b");
		            field.setAccessible(true);
		            field.set(headerPacket, Footer);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            cp.getHandle().playerConnection.sendPacket(headerPacket);
		        }
		}
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				p.setPlayerListName(RankType.getPlayerColor(RankType.getRank(p)) + p.getName());
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("§2§l§m----------------§2§l[§f§lWelcome§2§l]§2§l§m----------------");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("§eWelcome to §4§lThe Pit Mc!");
				p.sendMessage("§eClick your game menu§7§o (Nether star)");
				p.sendMessage("§eor go to a mine to start mining!");
				p.sendMessage("   ");
				p.sendMessage("   ");
				p.sendMessage("§2§l§m----------------------------------------");
				if (!Files.getDataFile().contains("Players." + p.getName())){
					p.sendMessage("§eHey there new player, we suggest taking the welcome tutorial to get yourself started. Just talk to the villager outside the cellblocks. Here are some ancient pickaxes, go and indetify them.");
					giveAncients(p);
					Files.getDataFile().set("Players." + p.getName() + ".Letter", "A");
					Files.saveDataFile();
				}
				p.updateInventory();
				p.teleport(Main.getLocation("spawn"));
			}
		}, 40l);
		if (p.getWorld().getName().equals("PVP")){
			me.BenLoe.PitPvP.InfoBoard.setBoard(p);
		}else{
			InfoBoard.setBoardFor(p);
		}
	}
	
	public static void giveAncients(Player p){
		ItemStack ancient = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta ancientm = ancient.getItemMeta();
		ancientm.setDisplayName(ChatColor.YELLOW + "Ancient Pickaxe");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Give this to the Identifier to see what");
		lore.add(ChatColor.GRAY + "type of pickaxe lies within.");
		ancientm.setLore(lore);
		ancient.setItemMeta(ancientm);
		p.getInventory().addItem(ancient);
		p.getInventory().addItem(ancient);
		p.updateInventory();
	}
	
	public static void goodJoin(PlayerJoinEvent event){
		
	}
}
