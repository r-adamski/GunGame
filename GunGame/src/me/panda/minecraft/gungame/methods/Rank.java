package me.panda.minecraft.gungame.methods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Random;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.objects.Rating;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Rank {

public static void sendTopElo(Player p){
		
		int elo1 = 0;
		int elo2 = 0;
		int elo3 = 0;
		int elo4 = 0;
		int elo5 = 0;
		int elo6 = 0;
		int elo7 = 0;
		int elo8 = 0;
		int elo9 = 0;
		int elo10 = 0;
		
		String nick1 = "";
		String nick2 = "";
		String nick3 = "";
		String nick4 = "";
		String nick5 = "";
		String nick6 = "";
		String nick7 = "";
		String nick8 = "";
		String nick9 = "";
		String nick10 = "";
		
		for(Entry<String, Rating> e : Main.map_ranking.entrySet()){
			String nick = e.getKey();
			int elo = e.getValue().getElo();
						
			if(elo > elo10 && !(elo > elo9)){
				elo10 = elo;
				nick10 = nick;
			}
			if(elo > elo9 && !(elo > elo8)){
				
				elo10 = elo9;
				nick10 = nick9;

				elo9 = elo;
				nick9 = nick;
				
			}
			if(elo > elo8 && !(elo > elo7)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;

				elo8 = elo;
				nick8 = nick;
			}
			if(elo > elo7 && !(elo > elo6)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;

				elo7 = elo;
				nick7 = nick;
			}
			if(elo > elo6 && !(elo > elo5)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;

				elo6 = elo;
				nick6 = nick;
			}
			if(elo > elo5 && !(elo > elo4)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;

				elo5 = elo;
				nick5 = nick;
			}
			if(elo > elo4 && !(elo > elo3)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;

				elo4 = elo;
				nick4 = nick;
			}
			if(elo > elo3 && !(elo > elo2)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;

				elo3 = elo;
				nick3 = nick;
			}
			if(elo > elo2 && !(elo > elo1)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;

				elo2 = elo;
				nick2 = nick;
			}
			if(elo > elo1){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;
				
				elo2 = elo1;
				nick2 = nick1;
				
				elo1 = elo;
				nick1 = nick;
			}
		}
		
		p.sendMessage(Main.pref + " Ranking top 10 najlepszych graczy :");
		p.sendMessage(Main.normal + "1 - " + Main.player + nick1 + Main.special + " - " + Main.score + elo1);
		p.sendMessage(Main.normal + "2 - " + Main.player + nick2 + Main.special + " - " + Main.score + elo2);
		p.sendMessage(Main.normal + "3 - " + Main.player + nick3 + Main.special + " - " + Main.score + elo3);
		p.sendMessage(Main.normal + "4 - " + Main.player + nick4 + Main.special + " - " + Main.score + elo4);
		p.sendMessage(Main.normal + "5 - " + Main.player + nick5 + Main.special + " - " + Main.score + elo5);
		p.sendMessage(Main.normal + "6 - " + Main.player + nick6 + Main.special + " - " + Main.score + elo6);
		p.sendMessage(Main.normal + "7 - " + Main.player + nick7 + Main.special + " - " + Main.score + elo7);
		p.sendMessage(Main.normal + "8 - " + Main.player + nick8 + Main.special + " - " + Main.score + elo8);
		p.sendMessage(Main.normal + "9 - " + Main.player + nick9 + Main.special + " - " + Main.score + elo9);
		p.sendMessage(Main.normal + "10 - " + Main.player + nick10 + Main.special + " - " + Main.score + elo10);
	}
	
	public static void sendTopWins(Player p){
		
		int elo1 = 0;
		int elo2 = 0;
		int elo3 = 0;
		int elo4 = 0;
		int elo5 = 0;
		int elo6 = 0;
		int elo7 = 0;
		int elo8 = 0;
		int elo9 = 0;
		int elo10 = 0;
		
		String nick1 = "";
		String nick2 = "";
		String nick3 = "";
		String nick4 = "";
		String nick5 = "";
		String nick6 = "";
		String nick7 = "";
		String nick8 = "";
		String nick9 = "";
		String nick10 = "";
		
		for(Entry<String, Rating> e : Main.map_ranking.entrySet()){
			String nick = e.getKey();
			int elo = e.getValue().getWins();
						
			if(elo > elo10 && !(elo > elo9)){
				elo10 = elo;
				nick10 = nick;
			}
			if(elo > elo9 && !(elo > elo8)){
				
				elo10 = elo9;
				nick10 = nick9;

				elo9 = elo;
				nick9 = nick;
				
			}
			if(elo > elo8 && !(elo > elo7)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;

				elo8 = elo;
				nick8 = nick;
			}
			if(elo > elo7 && !(elo > elo6)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;

				elo7 = elo;
				nick7 = nick;
			}
			if(elo > elo6 && !(elo > elo5)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;

				elo6 = elo;
				nick6 = nick;
			}
			if(elo > elo5 && !(elo > elo4)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;

				elo5 = elo;
				nick5 = nick;
			}
			if(elo > elo4 && !(elo > elo3)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;

				elo4 = elo;
				nick4 = nick;
			}
			if(elo > elo3 && !(elo > elo2)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;

				elo3 = elo;
				nick3 = nick;
			}
			if(elo > elo2 && !(elo > elo1)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;

				elo2 = elo;
				nick2 = nick;
			}
			if(elo > elo1){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;
				
				elo2 = elo1;
				nick2 = nick1;
				
				elo1 = elo;
				nick1 = nick;
			}
		}
		
		p.sendMessage(Main.pref + " Ranking top 10 ilosci wygranych :");
		p.sendMessage(Main.normal + "1 - " + Main.player + nick1 + Main.special + " - " + Main.score + elo1);
		p.sendMessage(Main.normal + "2 - " + Main.player + nick2 + Main.special + " - " + Main.score + elo2);
		p.sendMessage(Main.normal + "3 - " + Main.player + nick3 + Main.special + " - " + Main.score + elo3);
		p.sendMessage(Main.normal + "4 - " + Main.player + nick4 + Main.special + " - " + Main.score + elo4);
		p.sendMessage(Main.normal + "5 - " + Main.player + nick5 + Main.special + " - " + Main.score + elo5);
		p.sendMessage(Main.normal + "6 - " + Main.player + nick6 + Main.special + " - " + Main.score + elo6);
		p.sendMessage(Main.normal + "7 - " + Main.player + nick7 + Main.special + " - " + Main.score + elo7);
		p.sendMessage(Main.normal + "8 - " + Main.player + nick8 + Main.special + " - " + Main.score + elo8);
		p.sendMessage(Main.normal + "9 - " + Main.player + nick9 + Main.special + " - " + Main.score + elo9);
		p.sendMessage(Main.normal + "10 - " + Main.player + nick10 + Main.special + " - " + Main.score + elo10);
	}
	
	public static void sendTopKills(Player p){
		
		int elo1 = 0;
		int elo2 = 0;
		int elo3 = 0;
		int elo4 = 0;
		int elo5 = 0;
		int elo6 = 0;
		int elo7 = 0;
		int elo8 = 0;
		int elo9 = 0;
		int elo10 = 0;
		
		String nick1 = "";
		String nick2 = "";
		String nick3 = "";
		String nick4 = "";
		String nick5 = "";
		String nick6 = "";
		String nick7 = "";
		String nick8 = "";
		String nick9 = "";
		String nick10 = "";
		
		for(Entry<String, Rating> e : Main.map_ranking.entrySet()){
			String nick = e.getKey();
			int elo = e.getValue().getKills();
						
			if(elo > elo10 && !(elo > elo9)){
				elo10 = elo;
				nick10 = nick;
			}
			if(elo > elo9 && !(elo > elo8)){
				
				elo10 = elo9;
				nick10 = nick9;

				elo9 = elo;
				nick9 = nick;
				
			}
			if(elo > elo8 && !(elo > elo7)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;

				elo8 = elo;
				nick8 = nick;
			}
			if(elo > elo7 && !(elo > elo6)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;

				elo7 = elo;
				nick7 = nick;
			}
			if(elo > elo6 && !(elo > elo5)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;

				elo6 = elo;
				nick6 = nick;
			}
			if(elo > elo5 && !(elo > elo4)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;

				elo5 = elo;
				nick5 = nick;
			}
			if(elo > elo4 && !(elo > elo3)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;

				elo4 = elo;
				nick4 = nick;
			}
			if(elo > elo3 && !(elo > elo2)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;

				elo3 = elo;
				nick3 = nick;
			}
			if(elo > elo2 && !(elo > elo1)){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;

				elo2 = elo;
				nick2 = nick;
			}
			if(elo > elo1){
				
				elo10 = elo9;
				nick10 = nick9;
				
				elo9 = elo8;
				nick9 = nick8;
				
				elo8 = elo7;
				nick8 = nick7;
				
				elo7 = elo6;
				nick7 = nick6;
				
				elo6 = elo5;
				nick6 = nick5;
				
				elo5 = elo4;
				nick5 = nick4;
				
				elo4 = elo3;
				nick4 = nick3;
				
				elo3 = elo2;
				nick3 = nick2;
				
				elo2 = elo1;
				nick2 = nick1;
				
				elo1 = elo;
				nick1 = nick;
			}
		}
		
		p.sendMessage(Main.pref + " Ranking top 10 killi :");
		p.sendMessage(Main.normal + "1 - " + Main.player + nick1 + Main.special + " - " + Main.score + elo1);
		p.sendMessage(Main.normal + "2 - " + Main.player + nick2 + Main.special + " - " + Main.score + elo2);
		p.sendMessage(Main.normal + "3 - " + Main.player + nick3 + Main.special + " - " + Main.score + elo3);
		p.sendMessage(Main.normal + "4 - " + Main.player + nick4 + Main.special + " - " + Main.score + elo4);
		p.sendMessage(Main.normal + "5 - " + Main.player + nick5 + Main.special + " - " + Main.score + elo5);
		p.sendMessage(Main.normal + "6 - " + Main.player + nick6 + Main.special + " - " + Main.score + elo6);
		p.sendMessage(Main.normal + "7 - " + Main.player + nick7 + Main.special + " - " + Main.score + elo7);
		p.sendMessage(Main.normal + "8 - " + Main.player + nick8 + Main.special + " - " + Main.score + elo8);
		p.sendMessage(Main.normal + "9 - " + Main.player + nick9 + Main.special + " - " + Main.score + elo9);
		p.sendMessage(Main.normal + "10 - " + Main.player + nick10 + Main.special + " - " + Main.score + elo10);
	}
	
	
	
	//getPosition
	public static int getPosition(String nick){
		int position = 1;
		int elo = Main.map_ranking.get(nick).getElo();
		
		for(String s : Main.map_ranking.keySet()){
			int elo2 = Main.map_ranking.get(s).getElo();
			if(elo2 > elo){
				position ++;
			}
		}
		return position;
	}
	//load ranking
	public static void loadRanking(){
		
		String select = "SELECT * FROM GunGameRating";
		ResultSet rs = Main.mysql.query(select);
		
		Main.map_ranking.clear();
		try {
			while(rs.next()){
				String nick = rs.getString("nick");
				int wins = rs.getInt("wins");
				int lost = rs.getInt("lost");
				int kills = rs.getInt("kills");
				int deaths = rs.getInt("deaths");
				int elo = rs.getInt("elo");
				Main.map_ranking.put(nick, new Rating(wins, lost, kills, deaths, elo));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addKill(final Player p){
		Rating rat = Main.map_ranking.get(p.getName());
		rat.addKill();
		final int kills = rat.getKills();
		Main.map_ranking.put(p.getName(), rat);
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GunGameRating SET kills='" + kills + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
	}
	
	public static void addDeath(final Player p){
		Rating rat = Main.map_ranking.get(p.getName());
		final int deaths = rat.getDeaths() + 1;
		rat.addDeath();
		Main.map_ranking.put(p.getName(), rat);

		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GunGameRating SET deaths='" + deaths + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void addWin(final Player p){
		Rating rat = Main.map_ranking.get(p.getName());
		final int wins = rat.getWins()+1;
		rat.addWin();
		Main.map_ranking.put(p.getName(), rat);

		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GunGameRating SET wins='" + wins + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	
	public static void addLoose(final Player p){
		Rating rat = Main.map_ranking.get(p.getName());
		final int lost = rat.getLost()+1;
		rat.addLost();
		Main.map_ranking.put(p.getName(), rat);

		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GunGameRating SET lost='" + lost + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	 public static void updateRanking(final String death, final String killer)
	  {
	    int deathelo = Main.map_ranking.get(death).getElo();
	    int killerelo = Main.map_ranking.get(killer).getElo();

	    int deathkills = Main.map_ranking.get(death).getKills();
	    int deathdeaths = Main.map_ranking.get(death).getDeaths();

	    int killerkills = Main.map_ranking.get(killer).getKills();
	    int killerdeaths = Main.map_ranking.get(killer).getDeaths();
	    
	    int deathwins = Main.map_ranking.get(death).getWins();
	    int deathlost = Main.map_ranking.get(death).getLost();
	    
	    int killerwins = Main.map_ranking.get(killer).getWins();
	    int killerlost = Main.map_ranking.get(killer).getLost();

	    int e = 0;
	    Random rand = new Random();

	    if (deathelo < killerelo) {
	      int dif = killerelo - deathelo;
	      if ((dif <= 20) && (dif >= 0)) {
	        e = rand.nextInt(8) + 1;
	      }
	      else if ((dif > 20) && (dif <= 60)) {
	        e = rand.nextInt(3) + 6;
	      }
	      else if ((dif > 60) && (dif <= 200)) {
	        e = rand.nextInt(4) + 3;
	      }
	      else if ((dif > 200) && (dif <= 500)) {
	        e = rand.nextInt(3) + 2;
	      }
	      else if ((dif > 500) && (dif <= 1000)) {
	        e = rand.nextInt(2) + 1;
	      }
	      else if (dif > 1000) {
	        e = rand.nextInt(2) + 1;
	      }

	    }
	    else if (deathelo > killerelo) {
	      int dif = deathelo - killerelo;

	      if ((dif <= 20) && (dif >= 0)) {
	        e = rand.nextInt(8) + 1;
	      }
	      else if ((dif > 20) && (dif <= 60)) {
	        e = rand.nextInt(7) + 8;
	      }
	      else if ((dif > 60) && (dif <= 200)) {
	        e = rand.nextInt(9) + 12;
	      }
	      else if ((dif > 200) && (dif <= 500)) {
	        e = rand.nextInt(16) + 20;
	      }
	      else if ((dif > 500) && (dif <= 1000)) {
	        e = rand.nextInt(16) + 30;
	      }
	      else if (dif > 1000) {
	        e = rand.nextInt(26) + 35;
	      }
	      else if (deathelo == killerelo) {
	        e = rand.nextInt(6) + 1;
	      }

	    }
	    else
	    {
	      e = rand.nextInt(6) + 1;
	    }

	    int deatheloupdate = deathelo - e;
	    final int killereloupdate = killerelo + e;

	    //Bukkit.getPlayer(death).sendMessage(Main.pref + " dostales " + Main.score + " -" + e + Main.normal + " Elo!");
	   // Bukkit.getPlayer(killer).sendMessage(Main.pref + " dostales " + Main.score + " +" + e + Main.normal + " Elo!");

	    Main.map_ranking.put(death, new Rating(deathwins, deathlost, deathkills, deathdeaths, deatheloupdate));
	    Main.map_ranking.put(killer, new Rating(killerwins, killerlost, killerkills, killerdeaths, killereloupdate));
	    Thread t = new Thread(new Runnable()
	    {
	      public void run() {
	        String update11 = "UPDATE GunGameRating SET elo='" + deatheloupdate + "' WHERE nick='" + death + "'";
	        Main.mysql.query(update11);
	        String update111 = "UPDATE GunGameRating SET elo='" + killereloupdate + "' WHERE nick='" + killer + "'";
	        Main.mysql.query(update111);
	      }
	    });
	    t.start();
	  }
	
}
