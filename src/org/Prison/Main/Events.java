package org.Prison.Main;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.BenLoe.Gadgets.Types.PartyGun;
import me.BenLoe.SuperSpleef.Game;
import me.BenLoe.quest.ActiveQuest;
import me.BenLoe.quest.NeededType;
import me.BenLoe.quest.QuestAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;

import org.Prison.Friends.FriendAPI;
import org.Prison.Main.Booster.BoosterAPI;
import org.Prison.Main.Booster.BoosterCooldown;
import org.Prison.Main.CorruptEvents.CorruptMenu;
import org.Prison.Main.CorruptEvents.FireRain;
import org.Prison.Main.CorruptEvents.GhastTerror;
import org.Prison.Main.Enchanter.ClickHandler;
import org.Prison.Main.Enchanter.SellMenu;
import org.Prison.Main.ItemBuyer.ItemBuyerMenu;
import org.Prison.Main.Items.ItemAPI;
import org.Prison.Main.Letter.LetterType;
import org.Prison.Main.Menu.CrafterMenu;
import org.Prison.Main.Menu.MenuType;
import org.Prison.Main.Menu.MonthMenu;
import org.Prison.Main.Ranks.RankType;
import org.Prison.Main.RegionChecker.DonatorCellLine;
import org.Prison.Main.Storage.TonyGMenu;
import org.Prison.Main.Tutorial.Tutorial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;


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
			event.setMotd("\u00A78                  \u00A7l\u00A7m---\u00A74\u00A7l[ The Pit MC ]\u00A78\u00A7l\u00A7m---\n\u00A7e   " + Message.replace("&", "\u00A7"));
		}
	}
	
	@EventHandler
	public void armorStand(PlayerArmorStandManipulateEvent event){
		ArmorStand ar = event.getRightClicked();
		ar.setBasePlate(false);
		ar.setArms(true);
		if (!event.getPlayer().isOp()){
			event.setCancelled(true);
		}else{
			if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerDamageEvent(EntityDamageByEntityEvent event){
		if (event.getEntity() instanceof PigZombie && event.getDamager() instanceof Player){
			LivingEntity e = (LivingEntity) event.getEntity();
			Player p = (Player) event.getDamager();
			if (event.getDamage() >= e.getHealth()){
				p.setExp(p.getExp() + 0.02f);
				if ((new Random().nextInt(4) + 1) == 1){
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.6f, 1f);
				}
				if ((new Random().nextInt(100) + 1) == 1){
					p.sendMessage(ChatColor.GREEN + "Found: " + ChatColor.YELLOW + "Corrupt Chest.");
				}
			}
		}
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
			Player p = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			if (QuestAPI.hasAActive(damager)){
				ActiveQuest aq = ActiveQuest.getActive(damager);
				if (aq.getNeededType() == NeededType.HIT_GUARD && aq.getNeededAmount() >= QuestAPI.getProgress(p)){
					if (RankType.getRank(p).equals(RankType.MODERATOR) || RankType.getRank(p).equals(RankType.JRMOD) || RankType.getRank(p).equals(RankType.ADMIN)|| RankType.getRank(p).equals(RankType.OWNER)){
						if (Main.guard.containsKey(damager.getName())){
							damager.sendMessage("§cYou punched a guard recently, please wait another " + Main.guard.get(damager.getName()) + " seconds.");
						}else{
						ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.REDSTONE_BLOCK, (byte)0), 0.5f, 0.5f, 0.5f, 0.1f, 100, p.getLocation().clone().add(0, 0.8, 0), 100);
						ParticleEffect.LAVA.display(0.4f, 0.4f, 0.4f, 0.1f, 20, p.getLocation().clone().add(0, 0.8, 0), 100);
						p.damage(0.0);
						p.playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
						damager.playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
						p.setVelocity(damager.getLocation().getDirection().multiply(0.5));
						QuestAPI.addProgress(damager, 1);
						Main.guard.put(damager.getName(), 120);
						}
					}
				}
			}
		}
		if (event.getEntity() instanceof Sheep && event.getDamager() instanceof Player){
			Sheep sheep = (Sheep) event.getEntity();
			Player damager = (Player) event.getDamager();
			if (Main.sheeps.contains(sheep.getUniqueId())){
				event.setCancelled(true);
				if (DonatorCellLine.ifPlayerIsIn(sheep.getLocation(), "SheepLine")){
					sheep.teleport(new Location(Bukkit.getWorld("PrisonMap"), -217, 70, 194));
				}else{
				Random r = new Random();
				int i = r.nextInt(9) + 1;
				int red = 1;
				int g = 1;
				int b = 1;
				switch(i){
				case 1:
					sheep.setColor(DyeColor.RED);
					sheep.setCustomName("§c§lPunch me!");
					red = 255;
					break;
				case 2: 
					sheep.setColor(DyeColor.BLUE);
					sheep.setCustomName("§9§lPunch me!");
					b = 255;
					g = 1;
					red = 1;
					break;
				case 3:
					sheep.setColor(DyeColor.YELLOW);
					sheep.setCustomName("§e§lPunch me!");
					red = 255;
					g = 255;
					break;
				case 4:
					sheep.setColor(DyeColor.PURPLE);
					sheep.setCustomName("§5§lPunch me!");
					red = 135;
					g = 4;
					b = 155;
					break;
				case 5:
					sheep.setColor(DyeColor.CYAN);
					sheep.setCustomName("§3§lPunch me!");
					g = 194;
					red = 1;
					b = 233;
					break;
				case 6:
					sheep.setColor(DyeColor.LIME);
					sheep.setCustomName("§a§lPunch me!");
					red = 20;
					g = 255;
					b = 3;
					break;
				case 7:
					sheep.setColor(DyeColor.LIGHT_BLUE);
					sheep.setCustomName("§b§lPunch me!");
					g = 197;
					b = 255;
					break;
				case 8:
					sheep.setColor(DyeColor.PINK);
					sheep.setCustomName("§d§lPunch me!");
					red = 255;
					b = 247;
					break;
				case 9:
					sheep.setColor(DyeColor.ORANGE);
					sheep.setCustomName("§6§lPunch me!");
					red = 255;
					g = 102;
				}
				for (int i1 = 0; i1 < 100; i1++){
					double x = (-0.7 + (0.7 - -0.7) * new Random().nextDouble());
					double y = (-0.7 + (0.7 - -0.7) * new Random().nextDouble()) + 0.7;
					double z = (-0.7 + (0.7 - -0.7) * new Random().nextDouble());
					ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(red, g, b), sheep.getLocation().clone().add(x, y, z), 15);
				}
				sheep.setVelocity(damager.getLocation().getDirection().multiply(1.5).setY(0.4));
				sheep.setHealth(8.0);
				}
			}
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
		if (event.getNPC().getId() == 143){
			CorruptMenu.attempt(p);
		}
		if (event.getNPC().getId() == 169){
			MonthMenu.open(p);
		}
		if (event.getNPC().getId() == 179){
			Inventory inv = Bukkit.createInventory(null, 54, "§d§lCrafter");
			CrafterMenu.open(p, inv);
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
		new InventoryChange().Check(event);
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
			if (Main.Menu.get(p.getName()).equals(MenuType.CRAFTER)){
				CrafterMenu.tools.remove(p.getName());
				CrafterMenu.money.remove(p.getName());
				CrafterMenu.shards.remove(p.getName());
				CrafterMenu.xp.remove(p.getName());
			}
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
		if (CorruptMenu.inMenu.contains(p.getName())){
			CorruptMenu.inMenu.remove(p.getName());
			p.sendMessage("§7[§3Shady Inmate§7]: §cGood choice, the corrupt pit is not a safe place.");
		}
	}
	
	
	@EventHandler
	public void startFlying(PlayerToggleFlightEvent event){
		Player p = event.getPlayer();
		if (!p.getGameMode().equals(GameMode.CREATIVE)){
			if ((RankType.getRank(p).equals(RankType.ELITE) || RankType.getRank(p).equals(RankType.JRMOD) || RankType.getRank(p).equals(RankType.MODERATOR) || RankType.getRank(p).equals(RankType.ADMIN) || RankType.getRank(p).equals(RankType.OWNER) || RankType.getRank(p).equals(RankType.ULTRA))){
				if (!p.getWorld().getName().equals("PVP") && !Game.ingame.contains(p.getName()) && !Game.watching.contains(p.getName())){
				p.setVelocity(p.getLocation().getDirection().multiply(2.6).setY(p.getVelocity().getY() + 1.2));
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
	
	@EventHandler
	public void entityDamage(EntityDamageEvent event){
		if (event.getEntity() instanceof Ghast){
			Ghast ghast = (Ghast) event.getEntity();
			if (GhastTerror.ghasts.contains(ghast.getUniqueId())){
				event.setCancelled(true);
			}
		}
		if (event.getEntity() instanceof Creeper){
			Creeper creeper = (Creeper) event.getEntity();
			if (FireRain.wolfs.contains(creeper.getUniqueId())){
				event.setCancelled(true);
			}
		}
		if (event.getEntity() instanceof Player && event.getEntity().getWorld().getName().equals("NetherMap")){
			if (event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.SUFFOCATION){
				event.setCancelled(true);
			}
		}
		if (event.getEntity() instanceof Sheep && Main.sheeps.contains(event.getEntity().getUniqueId())){
			event.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void EntityExplode(EntityExplodeEvent event){
		if (event.getEntity() instanceof Fireball){
			List<Block> blocks = event.blockList();
			final HashMap<Location,Material> util = new HashMap<Location,Material>();
			event.setCancelled(true);
			ParticleEffect.EXPLOSION_HUGE.display(0.8f, 0.8f, 0.8f, 0.1f, 20, event.getLocation(), 100);
			int radius = new Random().nextInt(1) + 2;
			int bX = event.getLocation().getBlockX();
			int bY = event.getLocation().getBlockY() - 2;
			int bZ = event.getLocation().getBlockZ();
			for (int x = bX - radius; x <= bX + radius; x++){
				for (int y = bY - radius; y <= bY + radius; y++){
					for (int z = bZ - radius; z <= bZ + radius; z++){
						double distance = ((bX-x)*(bX-x) + ((bZ-z)*(bZ-z)) + ((bY-y)*(bY-y)));
						if (distance < radius*radius){
							Location loc = new Location(event.getLocation().getWorld(), x, y, z);
								blocks.add(loc.getBlock());
						}
					}
				}
			}
			for (Block b : blocks){
				if (b.getType() == Material.NETHERRACK || b.getType() == Material.MYCEL || (b.getType() == Material.DIRT && b.getData() == (byte)2)){
					util.put(b.getLocation(), b.getType());
					double x = (-0.4 + (0.8 - -0.4) * new Random().nextDouble());
					double y = (0.2 + (0.3 - -0.3) * new Random().nextDouble());
					double z = (-0.4 + (0.8 - -0.4) * new Random().nextDouble());
					FallingBlock f = b.getWorld().spawnFallingBlock(b.getLocation().add(0, 1, 0), b.getType(), b.getData());
					f.setFallDistance(1000f);
					f.setVelocity(new Vector(x,y,z));
					f.setDropItem(false);
					b.setType(Material.AIR);
					GhastTerror.fallings.add(f);
				}
			}
			for (Entity e : event.getEntity().getNearbyEntities(7, 7, 7)){
				if (e instanceof Player){
					Location midPoint = event.getLocation();
					Vector direction = e.getLocation().toVector().subtract(midPoint.toVector()).normalize();
					direction.multiply(0.8).setY(0.4);
					e.setVelocity(direction);
					((Player) e).damage(3);
					if (e.getLocation().distance(event.getEntity().getLocation()) <= 5){
					e.setFireTicks(60);
					}
				}
			}
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
				public void run(){
					GhastTerror.regen.putAll(util);
				}
			}, 20l);
		}
	}
	
	@EventHandler
	public void deathEvent(PlayerDeathEvent event){
		Player p = event.getEntity();
		event.setDeathMessage(null);
		List<ItemStack> util = new ArrayList<>();
		for (ItemStack item : event.getDrops()){
			if (item != null){
				if (item.hasItemMeta()){
					if (item.getItemMeta().hasDisplayName()){
					if (item.getItemMeta().getDisplayName().contains("Uses") || item.getItemMeta().getDisplayName().contains("Gadget")  || item.getItemMeta().getDisplayName().contains("Game Menu") || item.getItemMeta().getDisplayName().contains("Rainbow")){
						util.add(item);
						p.updateInventory();
					}
					}
				}
			}
		}
		event.getDrops().removeAll(util);
		ItemAPI.givePlayerItems(p);
	}
	
	@EventHandler
	public void respawnEvent(PlayerRespawnEvent event){
		ItemAPI.givePlayerItems(event.getPlayer());
	}
	
	@EventHandler
	public void ArmorStandDestroy(EntityDamageByEntityEvent e){
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}

		final LivingEntity livingEntity = (LivingEntity)e.getEntity();
		if(!livingEntity.getType().equals(EntityType.ARMOR_STAND)){
			return;
		}

		if(e.getDamager() instanceof Player){
			e.setCancelled(true);
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
