package org.Prison.Main;


import java.lang.reflect.Field;

import me.BenLoe.Gadgets.PurchaseMenus.Purchase;
import me.BenLoe.Gadgets.Types.*;
import mkremins.fanciful.FancyMessage;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.IInventory;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.Booster.BoosterAPI;
import org.Prison.Main.Booster.BoosterCooldown;
import org.Prison.Main.Currency.CrystalAPI;
import org.Prison.Main.Currency.MoneyAPI;
import org.Prison.Main.Enchanter.ClickHandler;
import org.Prison.Main.Enchanter.SellMenu;
import org.Prison.Main.ItemBuyer.ItemBuyerMenu;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Menu.MainMenu;
import org.Prison.Main.Menu.MenuItem;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Options.OptionAPI;
import org.Prison.Main.Options.OptionType;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.CellBlockLines;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.RegionChecker.VisibleLines;
import org.Prison.Main.Storage.TonyGMenu;
import org.Prison.Main.Traits.SpeedTrait;
import org.Prison.Main.Tutorial.Tutorial;
import org.Prison.Punish.PunishAPI;
import org.PrisonMain.Achievement.AchievementAPI;
import org.PrisonMain.Achievement.Menu.AchievementMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.craftbukkit.v1_8_R1.block.CraftFurnace;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftInventoryFurnace;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.drtshock.playervaults.PlayerVaults;
import com.drtshock.playervaults.vaultmanagement.UUIDVaultManager;
import com.drtshock.playervaults.vaultmanagement.VaultViewInfo;


@SuppressWarnings("deprecation")
public class Events implements Listener{
	
	public static Main plugin;
	public Events(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void motd(ServerListPingEvent event){
		if (BoosterAPI.isActivated()){
			String Message = Files.config().getString("BoosterMessage") + " " + BoosterCooldown.getTimeLeft("Booster");
			event.setMotd("\u00A78                    \u00A7l\u00A7m---\u00A74\u00A7l[ The Pit MC ]\u00A78\u00A7l\u00A7m---\n\u00A7e   " + Message.replace("&", "\u00A7"));
		}
	}
	
	@EventHandler
	public void NpcClickEvent(NPCRightClickEvent event){
		Player p = event.getClicker();
		ClickHandler.checkClick(event);
		if (Files.config().contains("Buyers.ID" + event.getNPC().getId())){
			ItemBuyerMenu.open(event.getClicker(), LetterType.fromString(Files.config().getString("Buyers.ID" + event.getNPC().getId())));
			return;
		}
		if (Files.config().getString("Rankup").equals("ID" + event.getNPC().getId())){
			LetterType.attemptRankup(p);
			return;
		}
		if (Files.config().getString("Tutorial").equals("ID" + event.getNPC().getId())){
			Tutorial.start(p);
		}
		if (Files.config().getInt("TonyG") == event.getNPC().getId()){
			TonyGMenu.openMenu(p);
		}
	}
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent event){
		if (!PlayerMode.isInPlayerMode(event.getPlayer())){
		if (event.getPlayer().getInventory().getHeldItemSlot() == 7 || event.getPlayer().getInventory().getHeldItemSlot() == 8){
		event.setCancelled(true);
		}
		}
	}
	@EventHandler
	public void PickupItem(PlayerPickupItemEvent event){
		Player p = event.getPlayer();
		if (ItemBuyerMenu.inBuyerMenu.containsKey(p.getName())){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void blockbreak(BlockBreakEvent event){
		if (PartyGun.parties.contains(event.getBlock())){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void click(final PlayerInteractEvent event){
		ClickEvent.click(event);
	}
	
	@EventHandler
	public void inventoryChange(InventoryClickEvent event){
		InventoryChange.Check(event);
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent event){
		new JoinEvent().Join(event);
	}
	
	@EventHandler
	public void inventoryOpen(InventoryOpenEvent event){
			if (event.getInventory().getType().equals(InventoryType.ANVIL)){
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Anvil's are disabled.");
			}
	}
	
	@EventHandler
	public void leaveEvent(PlayerQuitEvent event){
		event.setQuitMessage(null);
		for (Player p : Bukkit.getOnlinePlayers()){
			if (FriendAPI.getFriendList(p.getName()).contains(event.getPlayer().getName())){
				p.sendMessage(ChatColor.YELLOW + event.getPlayer().getName() + " left the server.");
			}
		}
		if (Main.Tutorialint.containsKey(event.getPlayer().getName())){
			Main.Tutorialint.remove(event.getPlayer().getName());
		}
	}
	@EventHandler
	public void chatEvent(PlayerChatEvent event){
		ChatMessages.execute(event);
	}
	@EventHandler
	public void InventoryClose(InventoryCloseEvent event){
		Player p = (Player) event.getPlayer();
		if (Main.Menu.containsKey(event.getPlayer().getName())){
			Main.Menu.remove(event.getPlayer().getName());
		}
		if (SellMenu.inSellerMenu.containsKey(p.getName())){
			SellMenu.inSellerMenu.remove(p.getName());
			SellMenu.inventory.remove(p.getName());
		}
		if (ItemBuyerMenu.inBuyerMenu.containsKey(event.getPlayer().getName())){
			for (ItemStack item : event.getInventory().getContents()){
				if (item != null){
				if (item.getTypeId() != 35 && item.getTypeId() != 160){
					event.getPlayer().getInventory().addItem(item);
				}
			}
			}
			p.updateInventory();
			ItemBuyerMenu.inBuyerMenu.remove(p.getName());
		}
		if (TonyGMenu.inTonyMenu.contains(p.getName())){
			TonyGMenu.inTonyMenu.remove(p.getName());
		}
	}
	
	
	@EventHandler
	public void startFlying(PlayerToggleFlightEvent event){
		Player p = event.getPlayer();
		if (!p.getGameMode().equals(GameMode.CREATIVE)){
			if ((RankType.getRank(p).equals(RankType.ELITE) || RankType.getRank(p).equals(RankType.JRMOD) || RankType.getRank(p).equals(RankType.MODERATOR) || RankType.getRank(p).equals(RankType.ADMIN) || RankType.getRank(p).equals(RankType.OWNER))){
				if (!p.getWorld().getName().equals("PVP")){
				p.setVelocity(p.getLocation().getDirection().multiply(2).setY(p.getVelocity().getY() + 0.7));
				p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1.0f, 1.0f);
				p.setFlying(false);
				p.setAllowFlight(false);
				event.setCancelled(true);
				}else{
					p.setFlying(false);
					p.setAllowFlight(false);
					event.setCancelled(true);
				}
			}else{
				p.setFlying(false);
			}
		}
	}
	
	public static void Firework1(Location loc){
		Firework fw = (Firework) loc.getWorld().spawn(loc, Firework.class);
		FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.AQUA).with(Type.BURST).build();
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.clearEffects();
		fwm.addEffect(effect);
		Field f1;
		try {
			f1 = fwm.getClass().getDeclaredField("power");
			f1.setAccessible(true);
			try {
				f1.set(fwm, -1);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		fw.setFireworkMeta(fwm);
		}
	public static void Firework2(Location loc){
		Firework fw = (Firework) loc.getWorld().spawn(loc, Firework.class);
		FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED, Color.GREEN).with(Type.BALL_LARGE).build();
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.clearEffects();
		fwm.addEffect(effect);
		Field f1;
		try {
			f1 = fwm.getClass().getDeclaredField("power");
			f1.setAccessible(true);
			try {
				f1.set(fwm, -1);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		fw.setFireworkMeta(fwm);
		}
}
