package me.panda.minecraft.gungame.methods;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Game;
import me.panda.minecraft.gungame.objects.Kit;

public class Info {

	public static Arena getArenaOnName(String name){
		for(Arena a : Main.list_arena){
			if(a.getName().equals(name))
				return a;
		}
		return null;
	}
	
	public static Location getRandomLocation(Arena a){
		Random rand = new Random();
		List<Location> spawns = a.getSpawnPoints();
		int i = rand.nextInt(spawns.size());
		return spawns.get(i);
	}
	
	public static boolean isOnArena(Player p){
		
		for(Game g : Main.map_game.values()){
			if(g.getOnlineList().contains(p)){
				return true;
			}
		}
		
		return false;
	}
	
	public static Game getPlayerGame(Player p){
		for(Game g : Main.map_game.values()){
			if(g.getOnlineList().contains(p))
				return g;
		}
		return null;
	}
	
	public static boolean isCorrectSign(Sign s, Arena a){
		
		int x = s.getX();
		int y = s.getY();
		int z = s.getZ();
		World w = s.getWorld();
		
		Location loc = Main.signs.get(a.getName());
		
		if(w == loc.getWorld() && x == loc.getBlockX() && y == loc.getBlockY() && z == loc.getBlockZ())
			return true;
		
		return false;
	}
	
	public static String getArenaofGame(Game g){
		for(Entry<String, Game> e : Main.map_game.entrySet()){
			if(e.getValue() == g)
				return e.getKey();
		}
		
		return null;
	}

	public static int getLvLFromKills(int kills){
		int lvl = 1;
		int needkills = Main.config.getInt("lvlup");
		kills+=needkills;
		lvl = kills / needkills;
		
		return lvl;
	}
	
	public static boolean isNewKIt(int lvl){
		for(Kit k : Main.kits){
			if(k.getLvl() == lvl)
				return true;
		}
		
		return false;
	}
	
	public static Kit getKit(int lvl) {
		Kit kit = null;
		for(Kit k : Main.kits){
			if(k.getLvl() <= lvl){
				kit = k;
			}
		}
		
		return kit;
	}
	
}
