package org.Prison.Main;

import me.BenLoe.Gadgets.PurchaseMenus.Purchase;
import me.BenLoe.Gadgets.Types.RainbowShotgun;
import net.md_5.bungee.api.ChatColor;

import org.Prison.Lucky.Game;
import org.Prison.Main.CorruptEvents.CorruptMenu;
import org.Prison.Main.Enchanter.ClickHandler;
import org.Prison.Main.ItemBuyer.ItemBuyerMenu;
import org.Prison.Main.Menu.MenuItem;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Storage.TonyGMenu;
import org.Prison.Main.Traits.SpeedTrait;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemFlag;

public class InventoryChange {

	public void Check(InventoryClickEvent event){
		Player p = (Player) event.getWhoClicked();
		if (!Game.playerInGame(p) && !me.BenLoe.SuperSpleef.Game.playerInGame(p) && !p.getWorld().getName().equals("Build") && !p.getWorld().getName().equals("PVP")){
			SpeedTrait.setCorrectSpeed(p);
		}
		if (p.getOpenInventory().getTopInventory().getTitle().contains("Vault")){
			if (event.getCurrentItem() != null){
				if (event.getCurrentItem().hasItemMeta()){
					if (event.getCurrentItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
						event.setCancelled(true);
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "This tool won't work in this chest, please hold the tool and do /convert.");
					}
				}
			}
		}
		if (!p.isOp()){
			if (p.getOpenInventory().getTopInventory().getTitle().contains("Reindeer")){
				event.setCancelled(true);
			}
		}
		if (Main.Menu.containsKey(p.getName())){
			event.setCancelled(true);
			if (MenuItem.wasAItem(event.getRawSlot(), Main.Menu.get(p.getName()))){
				MenuType m = Main.Menu.get(p.getName());
				MenuItem.getItemFromSlot(event.getRawSlot(), m).wasClicked(p);
			}
		}else if(me.BenLoe.Gadgets.Main.inGadgetMenu.contains(p.getName())){
			event.setCancelled(true);
			if (me.BenLoe.Gadgets.Menu.MenuItem.wasAItem(event.getRawSlot())){
				if (event.getClick().equals(ClickType.RIGHT)){
					me.BenLoe.Gadgets.Menu.MenuItem.getItemFromSlot(event.getRawSlot()).wasClicked(p, true);
				}else{
					me.BenLoe.Gadgets.Menu.MenuItem.getItemFromSlot(event.getRawSlot()).wasClicked(p, false);
				}
			}
		}else{
			if (Purchase.purchaseMenu.containsKey(p.getName())){
				Purchase.somethingWasClicked(p, event.getRawSlot());
				event.setCancelled(true);
			}
		}
		if (!PlayerMode.isInPlayerMode(p)){
			if (event.getCurrentItem() != null){
				if (event.getCurrentItem().hasItemMeta()){
					if (event.getCurrentItem().getItemMeta().hasDisplayName()){
					if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Uses") || event.getCurrentItem().getItemMeta().getDisplayName().contains("Gadget")  || event.getCurrentItem().getItemMeta().getDisplayName().contains("Game Menu") || event.getCurrentItem().getItemMeta().getDisplayName().contains("Rainbow") || event.getCurrentItem().getItemMeta().getDisplayName().contains("§2§lH§4§lA")){
						event.setCancelled(true);
						p.updateInventory();
				}
					}
			}
			}
			if (event.getAction().equals(InventoryAction.HOTBAR_SWAP)){
				if (event.getHotbarButton() == 7 || event.getHotbarButton() == 8){
					event.setCancelled(true);
					p.updateInventory();
				}
			}
		}
		if (ItemBuyerMenu.inBuyerMenu.containsKey(p.getName())){
			if (event.getClickedInventory().getName() != null){
			if (event.getClickedInventory().getName().contains("Sell Items")){
			if (event.getRawSlot() >= 45){
				event.setCancelled(true);
				if (event.getRawSlot() == 49){
					ItemBuyerMenu.sellItems(p, event.getInventory());
				}
			}
		}
		}
		}
		if (TonyGMenu.inTonyMenu.contains(p.getName())){
			event.setCancelled(true);
			TonyGMenu.menuClick(p, event.getRawSlot());
		}
		if (CorruptMenu.inMenu.contains(p.getName())){
			event.setCancelled(true);
			if (event.getRawSlot() == 13){
				CorruptMenu.wasClicked(p);
			}
		}
	}
}
