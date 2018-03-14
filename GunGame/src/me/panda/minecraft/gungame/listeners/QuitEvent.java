package me.panda.minecraft.gungame.listeners;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.GoPoints;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.methods.Rank;
import me.panda.minecraft.gungame.objects.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener{

	@EventHandler
	public static void OnQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(Info.isOnArena(p)){
			p.setLevel(0);
			Game g = Info.getPlayerGame(p);
			String name = Info.getArenaofGame(g);
			g.remove(p);
			Main.map_game.put(name, g);
			Manager.removeScoreBoard(p);

			Main.left_game.add(p.getName());
			//sprawdzic konczenie gry
			if(g.isInProgress()){
			if(g.getOnlineAmount() == 1){
				Object[] list = g.getOnlineList().toArray();
				Player winner = (Player) list[0];
				
				winner.sendMessage(Main.pref + " WYGRANA!!!");
				winner.sendMessage(Main.pref + " dostales " + Main.score + "50" + " GoPoint" + Main.normal + " za wygranie gry!");
				GoPoints.addPoints(winner, 50);
				g.finish();
				Rank.addWin(winner);
			}
			}
		}
	}
	
}
