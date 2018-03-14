package me.panda.minecraft.gungame.methods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Effect;
import me.panda.minecraft.gungame.objects.Kit;

public class Load {

	public static void loadMysql(){
		Main.list_arena.clear();
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				
				String select = "SELECT * FROM GunGameArena";
				ResultSet rs = Main.mysql.query(select);
				try {
					while(rs.next()){
						String name = rs.getString("name");
						int size = rs.getInt("size");
						
						String spawnpoints = rs.getString("spawnpoints");
						//world:x:y:z,world:x:y:z
						List<Location> spawns = new ArrayList<Location>();
						String[] map = spawnpoints.split(",");
						for(String s : map){
							String[] point = s.split(":");
							
							String world = point[0];
							int x = Integer.parseInt(point[1]);
							int y = Integer.parseInt(point[2]);
							int z = Integer.parseInt(point[3]);
							
							Location loc = new Location(Bukkit.getWorld(world), x, y, z);
							spawns.add(loc);
						}
						
						Main.list_arena.add(new Arena(name, size, spawns));
						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		t.start();
		
		Main.signs.clear();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
				
				String select = "SELECT * FROM GunGameSigns";
				ResultSet rs = Main.mysql.query(select);
				try {
					while(rs.next()){
						String name = rs.getString("name");
						
						String loc = rs.getString("location");
						//world:x:y:z
						
							String[] point = loc.split(":");
							
							String world = point[0];
							int x = Integer.parseInt(point[1]);
							int y = Integer.parseInt(point[2]);
							int z = Integer.parseInt(point[3]);
							
							Location sign = new Location(Bukkit.getWorld(world), x, y, z);
							Main.signs.put(name, sign);						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		t1.start();
		
		Main.map_points.clear();
		Thread t11 = new Thread(new Runnable(){
			@Override
			public void run(){
				
				String select = "SELECT * FROM GoPoints";
				ResultSet rs = Main.mysql.query(select);
				try {
					while(rs.next()){
						String name = rs.getString("nick");
						int points = rs.getInt("points");					
						Main.map_points.put(name, points);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		t11.start();
		
	}
	
public static void loadItems() {
	FileConfiguration config = Main.config;	
	
	//loading spawn loc
	
	World w = Bukkit.getWorld(config.getString("spawn.world"));
	int x = config.getInt("spawn.x");
	int y = config.getInt("spawn.y");
	int z = config.getInt("spawn.z");
	Main.spawn = new Location (w, x, y, z);
	Main.spawn.setYaw(-90);
	//loading kits
		int i = 1;
		while(config.contains(i + "")){
			
			int level = config.getInt(i+".lvl");
			List<ItemStack> islist = new ArrayList<ItemStack>();
			List<Effect> effectlist = new ArrayList<Effect>();
			int o = 1;
			while(config.contains(i+".items."+o)){
				
				@SuppressWarnings("deprecation")
				Material mat = Material.getMaterial(config.getInt(i+".items."+o+".id"));
				int amount = config.getInt(i+".items."+o+".amount");
				String nameitem =  config.getString(i+".items."+o+".name").replaceAll("&", "§");
				
				ItemStack is = new ItemStack(mat, amount);
				if(!nameitem.equals("0")){
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(nameitem);
				is.setItemMeta(im);
				}
				int e = 1;
				while(config.contains(i+".items."+o+".enchants."+e)){
					
					String nameenchant = config.getString(i+".items."+o+".enchants."+e+".enchant");
					int power = config.getInt(i+".items."+o+".enchants."+e+".power");
					if(!nameenchant.equals("0")){
						is.addEnchantment(Enchantment.getByName(nameenchant), power);
					}
					e++;
				}
				islist.add(is);
				o++;
			}
			
			int eff = 1;
			while(config.contains(i + ".effects."+eff)){
				
				String nameeffect = config.getString(i + ".effects."+eff+".name");
				if(!nameeffect.equals("0")){
					
					int time = config.getInt(i + ".effects."+eff+".time");
					int lvl = config.getInt(i + ".effects."+eff+".lvl");
					
					Effect effect = new Effect(PotionEffectType.getByName(nameeffect), time, lvl);
					effectlist.add(effect);
				}
				
				eff++;
			}
			
			Kit kit = new Kit(islist, effectlist, level);
			
			Main.kits.add(kit);
			
			i++;
		}
}
	
}
