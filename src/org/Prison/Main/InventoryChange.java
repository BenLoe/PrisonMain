package org.Prison.Main;

import me.BenLoe.Gadgets.PurchaseMenus.Purchase;
import me.BenLoe.Gadgets.Types.RainbowShotgun;

import org.Prison.Main.Enchanter.ClickHandler;
import org.Prison.Main.ItemBuyer.ItemBuyerMenu;
import org.Prison.Main.Menu.MenuItem;
import org.Prison.Main.Menu.MenuType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class InventoryChange {

	public static void Check(InventoryClickEvent event){
		Player p = (Player) event.getWhoClicked();
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
					if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Uses") || event.getCurrentItem().getItemMeta().getDisplayName().contains("Gadget")  || event.getCurrentItem().getItemMeta().getDisplayName().contains("Game Menu") || event.getCurrentItem().getItemMeta().getDisplayName().contains("Rainbow")){
						event.setCancelled(true);
						p.updateInventory();
				}
					}
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
	}
}
