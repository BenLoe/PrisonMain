package org.Prison.Main.ItemBuyer;

import java.util.HashMap;

import org.Prison.Main.Files;
import org.Prison.Main.Booster.BoosterAPI;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Ranks.RankType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuyerMenu {

	public static HashMap<String,LetterType> inBuyerMenu = new HashMap<String,LetterType>();
	
	@SuppressWarnings("deprecation")
	public static void open(Player p, LetterType t){
		Inventory inv = Bukkit.createInventory(null, 9 * 6, ChatColor.BLUE + "Sell Items | " + LetterType.getColoredString(t));
		ItemStack barrier = new ItemStack(160, 1, (byte)15);
		ItemStack confirm = new ItemStack(35, 1, (byte)5);
		ItemMeta barrierm = barrier.getItemMeta();
		ItemMeta confirmm = confirm.getItemMeta();
		barrierm.setDisplayName("§c§lBarrier");
		confirmm.setDisplayName("§a§lSell Items");
		barrier.setItemMeta(barrierm);
		confirm.setItemMeta(confirmm);
		inv.setItem(45, barrier);
		inv.setItem(46, barrier);
		inv.setItem(47, barrier);
		inv.setItem(48, barrier);
		inv.setItem(49, confirm);
		inv.setItem(50, barrier);
		inv.setItem(51, barrier);
		inv.setItem(52, barrier);
		inv.setItem(53, barrier);
		p.openInventory(inv);
		inBuyerMenu.put(p.getName(), t);
	}
	
	@SuppressWarnings("deprecation")
	public static void sellItems(Player p, Inventory inv){
		int amount = 0;
		LetterType t = inBuyerMenu.get(p.getName());
		for (ItemStack item : inv.getContents()){
			if (item != null){
			if (item.getTypeId() != 35 && item.getTypeId() != 160){
				if (Files.config().contains("Sell." + t.getName() + ".ID" + item.getTypeId())){
					amount = amount + Files.config().getInt("Sell." + t.getName() + ".ID" + item.getTypeId()) * item.getAmount();
				}else{
					p.getInventory().addItem(item);
				}
			}
			}
		}
		double booster = 0;
		if (amount > 0){
			String boostermessage = "";
			if (BoosterAPI.isActivated()){
				booster += BoosterAPI.getBooster();
				boostermessage = "§7" + BoosterAPI.getBooster() + "x global booster";
			}
			if (!RankType.getRank(p).equals(RankType.NONE)){
				if (RankType.getRank(p).equals(RankType.VIP)){
					booster += 1.3;
					boostermessage += ", 1.3x VIP booster";
				}else{
					booster += 1.7;
					boostermessage += ", 1.7x ELITE booster";
				}
			}
			if (RankType.getRank(p).equals(RankType.NONE) && !BoosterAPI.isActivated()){
				p.sendMessage("§e[§bItem Buyer§e]: §aI'll take that for §b" + amount + "$.");
			}else{
				p.sendMessage("§e[§bItem Buyer§e]: §aI'll take that for §b" + (int) Math.round(amount * booster) + "§a$ §7(" + boostermessage + ")§a.");
			}
		}else{
			p.sendMessage("§e[§bItem Buyer§e]: §aCome back anytime!");
		}
		inv.clear();
		if (booster > 1){
		MoneyAPI.addMoney(p, (int) Math.round(amount * booster));
		}else{
			MoneyAPI.addMoney(p, (int) Math.round(amount));
		}
		p.updateInventory();
		p.closeInventory();
	}
}
