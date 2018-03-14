package me.panda.minecraft.gungame.listeners;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.objects.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EntityEvents implements Listener{

	@EventHandler
	public void onDrop(PlayerDropItemEvent  e){
		Player p = e.getPlayer();
		if(Info.isOnArena(p)){
			e.setCancelled(true);
			p.sendMessage(Main.pref + " Niemozesz wyrzucac itemkow na arenia!");
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Location loc = e.getTo();
		if(loc.getY() < -5 && !e.getPlayer().isOp()){
			if(!Info.isOnArena(e.getPlayer())){
				e.getPlayer().teleport(Main.spawn);
			}
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		Player p = (Player)e.getEntity();
		if(Info.isOnArena(p)){
			e.setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onDestroy(PlayerItemBreakEvent e){
		Player p = e.getPlayer();
		if(Info.isOnArena(p)){
			e.getBrokenItem().setAmount(1);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(Main.left_game.contains(p.getName()))
		{
			Main.left_game.remove(p.getName());
			p.teleport(Main.spawn);
			p.sendMessage(Main.pref + " dostales " + Main.score + "-10" + " GoPoint" + Main.normal + " za wychodzenie podczas gry!");
		}
		
	}
	
	@EventHandler
	public void onDmg(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player)e.getEntity();
			if(Info.isOnArena(p)){
				Game g = Info.getPlayerGame(p);
				if(!g.isInProgress()){
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		if(Info.isOnArena(p)){
			if(e.getMessage().length() >= 4){
			if(!e.getMessage().equalsIgnoreCase("/opusc") && !e.getMessage().substring(0, 4).equalsIgnoreCase("/top") && !e.getMessage().substring(0, 4).equalsIgnoreCase("/elo") && !e.getPlayer().hasPermission("gungame.admin")){
				e.setCancelled(true);
				p.sendMessage(Main.pref + " Nie mozesz uzywac komend podczas gry!");
			}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDmg(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
		Player p = (Player)e.getEntity();
		if(Info.isOnArena(p)){
		if(e.getCause() == DamageCause.FALL){
			e.setDamage(0);
			e.setCancelled(true);
		}
		}
		}
	}
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
    	Player p = e.getPlayer();
    	if(Info.isOnArena(p)){
    		e.setCancelled(true);
    		Game g = Info.getPlayerGame(p);
    		
    		for(Player p1 : g.getOnlineList()){
    			p1.sendMessage( "§2§lGameChat: §8" + p.getName() + "§9§l>> §7"+ e.getMessage());
    		}
    	}
    }
}
