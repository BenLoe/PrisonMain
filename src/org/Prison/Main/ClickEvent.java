package org.Prison.Main;

import java.util.AbstractMap;

import me.BenLoe.Gadgets.Types.DeviceType;
import me.BenLoe.SuperSpleef.Game;

import org.Prison.Main.CorruptEvents.CorruptMenu;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Menu.MainMenu;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.Storage.SignClickManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.vaultmanagement.UUIDVaultManager;
import com.drtshock.playervaults.vaultmanagement.VaultViewInfo;

public class ClickEvent {

	public static void click(final PlayerInteractEvent event){
		Player p = event.getPlayer();
		SignClickManager.clickHandler(event);
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
		if (CellBlockLines.ifPlayerIsIn(p.getLocation(), "Visible")){
		if (event.getClickedBlock().getType().equals(Material.CHEST)){	
			event.setCancelled(true);
		if (RankType.getRank(p).equals(RankType.NONE)){
			p.sendMessage(ChatColor.RED + "Want more chest space? Only §3§lVIP §cand above can use this double chest.");
		}else{
			if (Main.playerv){
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1f, 1f);
			PlayerVaults.getInstance().getInVault().put(p.getUniqueId().toString(), new VaultViewInfo(p.getUniqueId(), 1));
			Inventory inv = UUIDVaultManager.getInstance().loadOwnVault(p, 1, 54);
            p.openInventory(inv);
			}else{
				p.sendMessage(ChatColor.RED + "Can't use chests immediately after reboot.");
			}
		}
		}
		if (event.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
			p.openInventory(p.getEnderChest());
			p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1f, 1f);
		}
		}else{
			if (event.getClickedBlock().getType().equals(Material.CHEST)){
				if (!PlayerMode.isInPlayerMode(p)){
				event.setCancelled(true);
				}
			}	
		}
		}
		if (p.getInventory().getItemInHand().getType().equals(Material.BLAZE_ROD)){
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				p.sendMessage(ChatColor.GREEN + "Place 1 set.");
				CellBlockLines.place1.put(p.getName(), event.getClickedBlock().getLocation());
			}
			if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				p.sendMessage(ChatColor.GREEN + "Place 2 set.");
				CellBlockLines.place2.put(p.getName(), event.getClickedBlock().getLocation());
				event.setCancelled(true);
			}
		}
		if (!PlayerMode.isInPlayerMode(p)){
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if (p.getInventory().getHeldItemSlot() == 8 && !Game.playerInGame(p) && !org.Prison.Lucky.Game.playerInGame(p)){
				event.setCancelled(true);
				MainMenu.open(p);
			}
		}
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if (event.getClickedBlock().getType().equals(Material.BOOKSHELF)){
				if (!Main.bookshelf.containsKey(p.getName())){
					if (Main.antibook.containsKey(p.getName())){
						Location loc = Main.antibook.get(p.getName()).getKey();
						Integer i = Main.antibook.get(p.getName()).getValue();
						if (loc.equals(event.getClickedBlock().getLocation())){
							if (i == 45){
								p.sendMessage(ChatColor.RED + "You have already read all the books in this bookshelf, please choose another.");
								return;
							}
							Main.antibook.put(p.getName(), new AbstractMap.SimpleEntry<Location, Integer>(loc, i + 1));
						}else{
							Main.antibook.put(p.getName(), new AbstractMap.SimpleEntry<Location, Integer>(event.getClickedBlock().getLocation(), 1));
						}
					}else{
						Main.antibook.put(p.getName(), new AbstractMap.SimpleEntry<Location, Integer>(event.getClickedBlock().getLocation(), 1));
					}
					Main.bookshelf.put(p.getName(), 1);
					Main.bookshelfBlock.put(p.getName(), event.getClickedBlock());
				}
			}
			if (event.getClickedBlock().getType().equals(Material.LOG) && CellBlockLines.ifPlayerIsIn(p.getLocation(), "Wood")){
				if (!Main.wood.containsKey(p.getName())){
					Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
						public void run(){
							event.getClickedBlock().getWorld().dropItem(event.getClickedBlock().getLocation().add(0.5, 1.8, 0.5), new ItemStack(Material.LOG));
						}
					}, 50l);
					Main.wood.put(p.getName(), 1);
					Main.woodBlock.put(p.getName(), event.getClickedBlock());
				}
			}
		}
		if (event.getItem() != null && !p.getWorld().getName().equals("NetherMap")){
			if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()){
				if (event.getItem().getItemMeta().getDisplayName().contains("§4§l Corrupt Shard") ){
					if (LetterType.getPlayerLetter(p).getInt() > 12){
						CorruptMenu.teleport(p);
					}else{
						p.sendMessage("§cYou are not experienced enough to use this.");
					}
				}
			}
		}
	}
}
