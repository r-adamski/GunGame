package me.panda.minecraft.gungame.methods;

import me.panda.minecraft.gungame.Main;

import org.bukkit.entity.Player;

public class GoPoints {

	public static void addPoints(final Player p, int value){

		int points = Main.map_points.get(p.getName());
		points += value;
		Main.map_points.put(p.getName(), points);
		
		final int u = points;
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GoPoints SET points='" + u + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
		
	}
	
	public static void removePoints(final Player p, int value){

		int points = Main.map_points.get(p.getName());
		points -= value;
		Main.map_points.put(p.getName(), points);
		
		final int u = points;
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				String update = "UPDATE GoPoints SET points='" + u + "' WHERE nick='" + p.getName() + "'";
				Main.mysql.query(update);
			}
		});
		t.start();
		
	}
}
