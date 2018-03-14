package me.panda.minecraft.gungame.listeners;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Game;
import me.panda.minecraft.gungame.objects.Kit;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEvent implements Listener{

	@EventHandler
	public static void onResp(PlayerRespawnEvent e){
		
		Player p = e.getPlayer();
		if(Info.isOnArena(p)){
		Game g = Info.getPlayerGame(p);
		if(g.isInProgress()){
		int lvl = g.getLevel(p);
		
		String name = Info.getArenaofGame(g);
		Arena a = Info.getArenaOnName(name);
		
		Location spawn = Info.getRandomLocation(a);
		e.setRespawnLocation(spawn);
		Kit kit = Info.getKit(lvl);
		Manager.setKit(p, kit);
		}
		}
		else{
			e.setRespawnLocation(Main.spawn);
		}
	}
}
