package me.panda.minecraft.gungame.listeners;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.GoPoints;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.methods.Rank;
import me.panda.minecraft.gungame.objects.Game;
import me.panda.minecraft.gungame.objects.Kit;
import net.minecraft.server.v1_7_R4.EnumClientCommand;
import net.minecraft.server.v1_7_R4.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener{

	@EventHandler
	public static void onDeath(PlayerDeathEvent e){
		e.getDrops().clear();
		if(e.getEntity() instanceof Player){
			Player p = e.getEntity();
		if(Info.isOnArena(p)){
		
			if(e.getEntity().getKiller() instanceof Player){
			Player killer = e.getEntity().getKiller();
			if(Info.isOnArena(killer)){

				Game g = Info.getPlayerGame(p);
				g.addKill(killer);
				g.addToKillStreak(killer);
				g.addDeath(p);
				g.resetKillStreak(p);
				//update lvl zabojcy
				int currlvl = g.getLevel(killer);
				int kills = g.getKills(killer);
				killer.sendMessage(Main.pref + " Zabiles gracza " + Main.player + p.getName());
				//g.sendMessage(Main.pref + Main.player + p.getName() + Main.normal + " zostal zabity przez " + Main.player + killer.getName());

				Rank.updateRanking(p.getName(), killer.getName());
				
				int newlvl = Info.getLvLFromKills(kills);
				if(currlvl < newlvl){
					//update lvla
					g.addLevel(killer);
					killer.sendMessage(Main.pref + " Nowy poziom: " + Main.score + g.getLevel(killer));
					killer.playEffect(EntityEffect.FIREWORK_EXPLODE);
					killer.playSound(killer.getLocation(), Sound.FIREWORK_LARGE_BLAST, 20, 1);
					if(Info.isNewKIt(newlvl)){
						Kit kit = Info.getKit(newlvl);
						Manager.setKit(killer, kit);
					}
				}
				
				TabEvent.refreshRanking(p);
	            TabEvent.refreshRanking(p.getKiller());
				
				String name = Info.getArenaofGame(g);
				Main.map_game.put(name, g);
				
				//sprawdzic konczenie gry
				if(newlvl == Main.config.getInt("lvl")){
					Rank.addWin(killer);
				killer.sendMessage(Main.pref + " WYGRANA!!!");
				killer.sendMessage(Main.pref + " dostales " + Main.score + "50" + " GoPoint" + Main.normal + " za wygranie gry!");
				GoPoints.addPoints(killer, 50);

				for(Player l : g.getOnlineList()){
					if(l != killer)
						Rank.addLoose(l);
					
					l.sendMessage(Main.pref + " Gracz " + Main.player + killer.getName() + Main.normal + " wygral osiagajac lvl:" + Main.score +Main.config.getInt("lvl"));
				}
				g.finish();
				
				}
			}
			else{
				//tylko dodac dead
				Game g = Info.getPlayerGame(p);
				//g.sendMessage(Main.pref + Main.player + p.getName() + Main.normal + " zginal!");
				g.addDeath(p);
				g.resetKillStreak(p);
				String name = Info.getArenaofGame(g);
				Main.map_game.put(name, g);
				TabEvent.refreshRanking(p);
			}
			}
			else{
				//tylko dodac dead
				Game g = Info.getPlayerGame(p);
				//g.sendMessage(Main.pref + Main.player + p.getName() + Main.normal + " zginal!");
				g.addDeath(p);
				g.resetKillStreak(p);
				String name = Info.getArenaofGame(g);
				Main.map_game.put(name, g);
				TabEvent.refreshRanking(p);
			}
		}
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
                ((CraftPlayer) p).getHandle().playerConnection.a(packet);
            }
        },  1);
		}
	}
}
