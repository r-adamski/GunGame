package me.panda.minecraft.gungame.objects;

import java.util.List;

import org.bukkit.Location;

public class Arena {
	private String name;
	private int size;
	private List<Location> spawns;
	
	public Arena(String name, int size, List<Location> spawns){
		this.name = name;
		this.size = size;
		this.spawns = spawns;
	}
	
	public String getName(){ return this.name; }
	public Integer getSize(){ return this.size; }
	public List<Location> getSpawnPoints(){ return this.spawns; }
}
