package me.panda.minecraft.gungame.objects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.GoPoints;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Items;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.methods.Rank;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Game{
		//map plejer-level 
	private Map<Player, InGameStats> players;
	private boolean inProgress;
	private boolean countDown;
	private boolean shorted;
	
	public Game(){
		Map<Player, InGameStats> map = new HashMap<Player, InGameStats>();
		this.players = map;
		this.shorted = false;
		this.inProgress = false;
		this.countDown = false;
	}
	
	public void addKill(Player p){
		InGameStats stat = this.players.get(p);
		stat.addKill();
		this.players.put(p, stat);
		Rank.addKill(p);
		p.sendMessage(Main.pref + " dostales " + Main.score + "1" + " GoPoint" + Main.normal + " za zabicie gracza!");
		GoPoints.addPoints(p, 1);
	}
	
	public void addDeath(Player p){
		Rank.addDeath(p);
	}
	
	public void addToKillStreak(Player p){
		InGameStats stat = this.players.get(p);
		stat.addToKillStreak();
		this.players.put(p, stat);
	}
	
	public void resetKillStreak(Player p){
		InGameStats stat = this.players.get(p);
		stat.resetKillStreak();
		this.players.put(p, stat);
	}
	
	public void addLevel(Player p){
		InGameStats stat = this.players.get(p);
		stat.addLevel();
		this.players.put(p, stat);
		p.sendMessage(Main.pref + " dostales " + Main.score + "2" + " GoPoint" + Main.normal + " za awans na nastepny level!");
		GoPoints.addPoints(p, 2);
		Manager.updateScoreBoard(this);
	}
	
	public void removeLevel(Player p){
		InGameStats stat = this.players.get(p);
		stat.removeLevel();
		this.players.put(p, stat);
		Manager.updateScoreBoard(this);
	}
	
	public void finish(){
		Location loc = Main.spawn;
		for(Player p : this.getOnlineList()){
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			for(PotionEffect e : p.getActivePotionEffects()){
				p.removePotionEffect(e.getType());
			}
			p.teleport(loc);
			Items.addStartEQ(p);
			Manager.removeScoreBoard(p);
		}
		String name = Info.getArenaofGame(this);
		Main.map_game.remove(name);
		Arena a = Info.getArenaOnName(name);
		Manager.updateSign(a);
	}
	
	public boolean isInProgress(){
		return this.inProgress;
	}
	
	public int getOnlineAmount(){
		return this.players.size();
	}
	
	public Set<Player> getOnlineList(){
		return this.players.keySet();
	}
	
	public void remove(Player p){
		this.players.remove(p);
		Manager.updateScoreBoard(this);
		for(Player p1 : this.players.keySet())
			p1.sendMessage(Main.pref + " Gracz " + Main.player + p.getName() + Main.normal + " opuscil gre!");
	}
	
	public int getKills(Player p){
		return this.players.get(p).getKills();
	}
	
	public int getLevel(Player p){
		return this.players.get(p).getLevel();
	}
	public int getKillStreak(Player p){
		return this.players.get(p).getKillStreak();
	}
	public void sendMessage(String msg){
		for(Player p : getOnlineList())
			p.sendMessage(msg);
	}
	
	public void addPlayer(Player p){		
		
			String name = Info.getArenaofGame(this);
			Arena a = Info.getArenaOnName(name);
			this.players.put(p, new InGameStats());
			Manager.addScoreBoard(p);
			Manager.updateScoreBoard(this);

			for(Player p1 : this.players.keySet()){

			p1.sendMessage(Main.pref + " Gracz " + Main.player + p.getName() + Main.normal + " dolaczyl do gry!" 
					+ Main.special + " [" + Main.score + this.players.size() + Main.normal + "/" + Main.score + a.getSize() + Main.special + "]");
		}
	}
	
	public boolean isCountDown(){
		if(countDown)
			return true;
		return false;
	}
	
	public void StartCountDown(){
				Game g = this;
				for(Player p : g.getOnlineList()){
					p.sendMessage(Main.pref + Main.score + " Rozpoczeto odliczanie!");
				}
		Thread t = new Thread(new Runnable(){
		@Override
		public void run(){
			try {
				for(int i = 60 ; i >= 0 ; i--){
					if(!Thread.currentThread().isInterrupted()){
				Thread.sleep(1000);
				
				Set<Player> online = getOnlineList();
				
				if(!online.isEmpty()){
				String name = Info.getArenaofGame(g);
				Arena a = Info.getArenaOnName(name);
				float percent = online.size() / (float)a.getSize();
				if(percent > 0.8){
					if(g.shorted == false){
						g.shorted = true;
					i *=0.5;
					for(Player player : online)
						player.sendMessage(Main.pref + " Timer zostal skrocony!");
					}
				}
				else if(percent < 0.5){
					g.countDown = false;
					Main.map_game.put(name, g);
					Thread.currentThread().interrupt();
					for(Player player : online)
						player.sendMessage(Main.pref + " Timer zostal zatrzymany!");
					}
				
				}
				else
					Thread.currentThread().interrupt();
				
				for(Player p : online)
					p.setLevel(i);
				
				if(i == 30){
					for(Player p : online)
						p.sendMessage(Main.pref + Main.score + " 30 " + Main.normal + " sekund do rozpoczecia gry!");
				}
				else if(i == 20){
					for(Player p : online)
						p.sendMessage(Main.pref + Main.score + " 20 " + Main.normal + " sekund do rozpoczecia gry!");
				
				}
				else if(i == 10){
					for(Player p : online)
						p.sendMessage(Main.pref + Main.score + " 10 " + Main.normal + " sekund do rozpoczecia gry!");
				
				}
				else if(i == 5){
					for(Player p : online){
						p.sendMessage(Main.pref + Main.score + " 5 " + Main.normal + " sekund do rozpoczecia gry! Przygotuj sie!");
					p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					}
				}
				else if(i == 4){

					for(Player p : online){
						p.sendMessage(Main.pref + Main.score + " 4 ");
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					}
					
				}
				else if(i == 3){
					for(Player p : online){
						p.sendMessage(Main.pref + Main.score + " 3 ");
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					}
					
				}
				else if(i == 2){
					for(Player p : online){
						p.sendMessage(Main.pref + Main.score + " 2 ");
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					}
					
				}
				else if(i == 1){
					for(Player p : online){
						p.sendMessage(Main.pref + Main.score + " 1 ");
						p.playSound(p.getLocation(), Sound.CLICK, 20, 1);
					}
					
				}
				else if(i == 0){
					for(Player p : online){
						p.sendMessage(Main.pref + "§c§lPAMIETAJ ZE TO BETA TRYBU GUNGAME. MOGA BYC BUGI.");
						p.sendMessage(Main.pref + " Start!");
						p.playSound(p.getLocation(), Sound.CAT_MEOW, 20, 1);
					}
					StartGame();
				}
					}
				
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		});
		t.start();
		this.countDown = true;
	}
	
	public void StartGame(){
		
		Set<Player> list = this.getOnlineList();
		Arena a = Info.getArenaOnName(Info.getArenaofGame(this));
		
		Location locremove = a.getSpawnPoints().get(0);
		World w = locremove.getWorld();
		List<Entity> ent = w.getEntities();
		Iterator<Entity> entities = ent.iterator();
		while (entities.hasNext()) {
		    Entity entity = entities.next();
		    if (entity instanceof Item) {
		        entity.remove();
		    }
		}
		
		for(Player p : list){
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.removePotionEffect(PotionEffectType.JUMP);
			p.removePotionEffect(PotionEffectType.SPEED);
			
			Manager.setKit(p, Info.getKit(1));
			Bukkit.getScheduler().runTask(Main.plugin, new Runnable(){
				@Override
				public void run(){
			Location loc = Info.getRandomLocation(a);
			p.teleport(loc);
				}
			});
		}
		this.inProgress = true;
		Manager.updateSign(a);
		
	}
	
}


class InGameStats{
	private int kills;
	private int killstreak;
	private int level;
	
	protected InGameStats(){
		this.kills = 0;
		this.killstreak = 0;
		this.level = 1;
	}
	
	protected int getKills(){ return this.kills; }
	protected int getKillStreak(){ return this.killstreak; }
	protected int getLevel(){ return this.level; }
	
	protected void addKill(){
		this.kills+=1;
	}
	
	protected void addToKillStreak(){
		this.killstreak+=1;
	}
	
	protected void resetKillStreak(){
		this.killstreak = 0;
	}
	
	protected void addLevel(){
		this.level+=1;
	}
	
	protected void removeLevel(){
		this.level-=1;
	}
	
}