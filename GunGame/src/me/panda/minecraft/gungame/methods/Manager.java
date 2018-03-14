/**
 * 
 */
package me.panda.minecraft.gungame.methods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Effect;
import me.panda.minecraft.gungame.objects.Game;
import me.panda.minecraft.gungame.objects.Kit;

/**
 * @author Panda
 *
 */
public class Manager {

	public static void joinArena(Arena a, Player p){
		if(Main.map_game.containsKey(a.getName())){
			//game exist adding player
			
			Game g = Main.map_game.get(a.getName());
			if(!g.isInProgress()){
			if(g.getOnlineAmount() < a.getSize()){
				
			g.addPlayer(p);
			Main.map_game.put(a.getName(), g);
			Location spawn = Info.getRandomLocation(a);
			p.teleport(spawn);
			p.sendMessage(Main.pref + " Dolaczyles do areny! Poczekaj chwile na reszte graczy.");
			p.sendMessage(Main.pref + Main.score + " Znajdujesz sie w lobbby. Gra zacznie sie za chwile! Czekanie na reszte graczy..");
			addAfterStartInv(p);
						
			float percent = g.getOnlineAmount() / (float)a.getSize();

			if(percent >= 0.5){
				if(!g.isCountDown())
				g.StartCountDown();
			}
			
			Main.curr_joined.add(p.getName());
			Thread t = new Thread(new Runnable(){
				@Override
				public void run(){
				try {
					Thread.sleep(2000);
					Main.curr_joined.remove(p.getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
			});
			t.start();
			
			}
			else if(p.isOp() || p.hasPermission("gungame.admin") || p.hasPermission("gungame.vip")){
				
				g.addPlayer(p);
				Main.map_game.put(a.getName(), g);
				Location spawn = Info.getRandomLocation(a);
				p.teleport(spawn);
				p.sendMessage(Main.pref + " Dolaczyles do areny!");
				addAfterStartInv(p);
				Main.curr_joined.add(p.getName());
				Thread t = new Thread(new Runnable(){
					@Override
					public void run(){
					try {
						Thread.sleep(2000);
						Main.curr_joined.remove(p.getName());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					}
				});
				t.start();
			}
			else{
				p.sendMessage(Main.pref + " Arena jest pelna! Jezeli chcesz dolaczac do pelnych aren kup "
						+Main.score + "VIPA"+Main.normal+" na naszej stronie " + Main.score +"http://gopvp.pl/");
			}
			}
			else
				p.sendMessage(Main.pref + " Gra na tej arenie juz wystartowala! Poczekaj chwile lub dolacz do innej areny.");
			
		}
		else{
			//game doesnt exist, createing game , adding player
			
			Game g = new Game();
			Main.map_game.put(a.getName(), g);
			g.addPlayer(p);
			Main.map_game.put(a.getName(), g);

			Location spawn = Info.getRandomLocation(a);
			p.teleport(spawn);
			p.sendMessage(Main.pref + " Dolaczyles do areny! Poczekaj chwile na reszte graczy.");
			addAfterStartInv(p);
			Main.curr_joined.add(p.getName());
			Thread t = new Thread(new Runnable(){
				@Override
				public void run(){
				try {
					Thread.sleep(2000);
					Main.curr_joined.remove(p.getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
			});
			t.start();
		}
		
		
	}
	
	private static void addAfterStartInv(Player p){
		p.getInventory().clear();
		p.setExp(0);
		p.setHealth(20d);
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().setArmorContents(null);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1));

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta imbook = (BookMeta) book.getItemMeta();
        imbook.setAuthor("GoPvP");
        imbook.setTitle("§a§lInformacje ogolne");
        imbook.addPage("1"); imbook.addPage("2"); imbook.addPage("3");imbook.addPage("4");
        imbook.setPage(1, "§1§lWitaj na \n GunGame GoPvP.PL!  \n\n\n§5SPIS TRESCI\n\n§91. §cinformacje o trybie\n§92. §ckomendy\n§93. §ckonto §6§lVIP");
        imbook.setPage(2, "§4§lINFORMACJE OGOLNE\n\n§2GunGame to tryb w ktorym walczysz na arenie kazdy na kazdego. Co kilka zabojstw dostajesz lvl. Toje eq zmienia sie na coraz lepsze. Kto pierwszy osiagnie 10 lvl wygrywa!");
        imbook.setPage(3, "§4§lPODSTAWOWE KOMENDY\n\n§9/opusc§7 - opuszcza arene\n§9/elo§7 - twoj ranking\n§9/elo <nick>§7 - ranking gracza\n§9/top§7 - top elo\n§9/top kill/win§7 - topki winow/killi\n");
        imbook.setPage(4, "§4§lKONTO VIP\n\n§9tu bedzie info o VIP");

		book.setItemMeta(imbook);
		p.getInventory().addItem(book);
		
		ItemStack shop = new ItemStack(Material.CHEST, 1);
		ItemMeta smeta = shop.getItemMeta();
		smeta.setDisplayName("§6§lSKLEP");
		List<String> slore= new ArrayList<String>();
		slore.add("§cKliknij ppm aby otworzyc skep!");
		smeta.setLore(slore);
		shop.setItemMeta(smeta);
		p.getInventory().addItem(shop);
		
		@SuppressWarnings("deprecation")
		ItemStack quit = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
		ItemMeta qmeta = shop.getItemMeta();
		qmeta.setDisplayName("§c§lWYJSCIE");
		List<String> qlore= new ArrayList<String>();
		qlore.add("§cKliknij ppm aby wyjsc z areny!");
		qmeta.setLore(qlore);
		quit.setItemMeta(qmeta);
		p.getInventory().addItem(quit);
		p.updateInventory();
	}
	
	public static void updateSign(Arena a){
		Bukkit.getScheduler().runTask(Main.plugin, new Runnable(){
			@Override
			public void run(){
		Location loc = Main.signs.get(a.getName());
		Sign s = (Sign)loc.getBlock().getState();
		
		if(Main.map_game.containsKey(a.getName())){
			Game g = Main.map_game.get(a.getName());
			s.setLine(2, "§e§l"+g.getOnlineAmount()+"§8" + "/§e§l" + a.getSize());
			if(g.isInProgress())
				s.setLine(3, "§c§lW TRAKCIE");

			s.update();
		}
		else if(Main.list_arena.contains(a)){
			s.setLine(2, "§e§l0§8" + "/§e§l" + a.getSize());
			s.setLine(3, "§2§lDOLACZ PPM");
			s.update();
		} 
		else{
			s.setLine(3, "§4§lUSUNIETA");
			s.update();
		}
			}});
	}

	public static void setKit(Player p, Kit kit) {
		
		p.getInventory().clear();
		if(kit != null){
		for(ItemStack is : kit.getItems()){
			if(isHelmet(is)){
				p.getInventory().setHelmet(is);
			}
			else if(isChestPlate(is)){
				p.getInventory().setChestplate(is);
			}
			else if(isLeggings(is)){
				p.getInventory().setLeggings(is);
			}
			else if(isBoots(is)){
				p.getInventory().setBoots(is);
			}
			else
			p.getInventory().addItem(is);
		}
		for(Effect e : kit.getEffects()){
			p.addPotionEffect(new PotionEffect(e.getPotion(), e.getTIme(), e.getLvl()));
		}
		}
	}
	
	private static boolean isHelmet(ItemStack is){
		if(is.getType() == Material.LEATHER_HELMET || is.getType() == Material.CHAINMAIL_HELMET || is.getType() == Material.IRON_HELMET || is.getType() == Material.DIAMOND_HELMET|| is.getType() == Material.GOLD_HELMET)
			return true;
		return false;
	}
	private static boolean isBoots(ItemStack is){
		if(is.getType() == Material.LEATHER_BOOTS || is.getType() == Material.CHAINMAIL_BOOTS || is.getType() == Material.IRON_BOOTS || is.getType() == Material.DIAMOND_BOOTS || is.getType() == Material.GOLD_BOOTS)
			return true;
		return false;
	}
	private static boolean isChestPlate(ItemStack is){
		if(is.getType() == Material.LEATHER_CHESTPLATE || is.getType() == Material.CHAINMAIL_CHESTPLATE || is.getType() == Material.IRON_CHESTPLATE || is.getType() == Material.DIAMOND_CHESTPLATE || is.getType() == Material.GOLD_CHESTPLATE)
			return true;
		return false;
	}
	private static boolean isLeggings(ItemStack is){
		if(is.getType() == Material.LEATHER_LEGGINGS || is.getType() == Material.CHAINMAIL_LEGGINGS || is.getType() == Material.IRON_LEGGINGS || is.getType() == Material.DIAMOND_LEGGINGS || is.getType() == Material.DIAMOND_LEGGINGS)
			return true;
		return false;
	}
	/*
	 * can be used only when
	 * game object exist in map in Main.java
	 */
	public static void addScoreBoard(Player p){
		Game g = Info.getPlayerGame(p);
		
	        Scoreboard sb = p.getScoreboard();
		// ScoreboardManager sbm = Bukkit.getScoreboardManager();
		 //Scoreboard sb = sbm.getNewScoreboard();
	        Objective objective;
	        if(sb.getObjective("info") != null){ sb.getObjective("info").unregister(); }
	        objective = sb.registerNewObjective("info", "dummy");
	        objective.setDisplayName("§2§lNick <-> Level");
	        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	       /* }
	        else{
	        	objective = sb.getObjective("info");
	        }*/
	        
	        for(Player p1 : g.getOnlineList()){
	        	String nick = p1.getName();
	        	if(nick.length() > 14)
	        		nick = nick.substring(0, 14);
	        	objective.getScore("§e"+nick).setScore(1);
	        }

	        p.setScoreboard(sb);
	}
	
	
	
	public static void updateScoreBoard(Game g){
		for(Player p : g.getOnlineList()){
			Scoreboard sb = p.getScoreboard();
			Objective obj = sb.getObjective("info");
			
	        for(Player p1 : g.getOnlineList()){
	        	String nick = p1.getName();
	        	if(nick.length() > 14){
	        		nick = nick.substring(0, 14);
	        	}

	        	obj.getScore("§e"+nick).setScore(g.getLevel(p1));
	        }
			p.setScoreboard(sb);
		}
	}
	
	
	public static void removeScoreBoard(Player p){
		if(Info.isOnArena(p)){
		
			/*Game g = Info.getPlayerGame(p);
			for(Player p1 : g.getOnlineList()){
				if(p1 != p)
				addScoreBoard(p1);
			}*/
			
		}
		Scoreboard sb = p.getScoreboard();
		if(sb.getObjective("info")!=null)
		sb.getObjective("info").unregister();
		p.setScoreboard(sb);
	}
}
