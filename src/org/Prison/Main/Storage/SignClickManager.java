package org.Prison.Main.Storage;

import net.md_5.bungee.api.ChatColor;

import org.Prison.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.vaultmanagement.UUIDVaultManager;
import com.drtshock.playervaults.vaultmanagement.VaultViewInfo;

public class SignClickManager {

	public static void clickHandler(PlayerInteractEvent event){
		Player p = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if (event.getClickedBlock().getState() instanceof Sign){
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(1).contains("Click to") && sign.getLine(2).contains("access chest")){
					int id = Integer.parseInt(String.valueOf(sign.getLine(2).charAt(13)));
					if (TonyStorageAPI.hasAccessTo(p, id)){
						if (Main.playerv){
						p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1f, 1f);
						PlayerVaults.getInstance().getInVault().put(p.getUniqueId().toString(), new VaultViewInfo(p.getUniqueId(), id + 1));
						Inventory inv = UUIDVaultManager.getInstance().loadOwnVault(p, id + 1, 54);
			            p.openInventory(inv);
						}else{
							p.sendMessage(ChatColor.RED + "Can't use chests immediately after reboot.");
						}
					}else{
						p.sendMessage(ChatColor.RED + "You have not bought this chest yet, talk to Tony G about buying this chest.");
					}
				}
			}
		}
	}
}
