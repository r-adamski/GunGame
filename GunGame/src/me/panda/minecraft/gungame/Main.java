package me.panda.minecraft.gungame;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.panda.minecraft.gungame.commands.EloCmd;
import me.panda.minecraft.gungame.commands.OpuscCmd;
import me.panda.minecraft.gungame.commands.TopCmd;
import me.panda.minecraft.gungame.listeners.DeathEvent;
import me.panda.minecraft.gungame.listeners.EntityEvents;
import me.panda.minecraft.gungame.listeners.InvClickEvent;
import me.panda.minecraft.gungame.listeners.InvEvent;
import me.panda.minecraft.gungame.listeners.QuitEvent;
import me.panda.minecraft.gungame.listeners.RespawnEvent;
import me.panda.minecraft.gungame.listeners.SignEvent;
import me.panda.minecraft.gungame.listeners.TabEvent;
import me.panda.minecraft.gungame.methods.Load;
import me.panda.minecraft.gungame.methods.MySQLDatabase;
import me.panda.minecraft.gungame.methods.Rank;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Game;
import me.panda.minecraft.gungame.objects.Kit;
import me.panda.minecraft.gungame.objects.Rating;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Main extends JavaPlugin{
	
	
	public static String pref = "§3§lGoPvP§9§l>>§7";
	public static String player ="§3§l";
	public static String score = "§b§l";
	public static String special = "§8";
	public static String normal = "§7";
	
    public static MySQLDatabase mysql;
    public static boolean MySQL = true;
    public static File configFile;
    public static FileConfiguration config;
    public static Plugin plugin;
    
    //map - name of arena - location of sign
    public static Map<String, Location> signs = new HashMap<String, Location>();
    public static List<Arena> list_arena = new ArrayList<Arena>();
    //map - name of arena - game
    public static Map<String, Game> map_game = new HashMap<String, Game>();
    public static List<String> cd = new ArrayList<String>();
    //list of kits
    public static List<Kit> kits = new ArrayList<Kit>();
	//spawn loc
	public static Location spawn;
	//list to fix a bug with leave when join game
	public static List<String> curr_joined= new ArrayList<String>();
	//list of players who left the game and they need to be tp on spawn when join
	public static List<String> left_game = new ArrayList<String>();
    //list of ranking
    public static Map<String, Rating> map_ranking = new HashMap<String, Rating>();
    //gopoints
    public static Map<String, Integer> map_points = new HashMap<String, Integer>();
    
	
    public void onEnable() {
    	plugin = this;
    	//====================================================================
    	configFile = new File(getDataFolder(), "config.yml");
    	
    	try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
    	config = new YamlConfiguration();
    	loadYamls();
    	//===================================================================
    	
    	if (config.getString("MySQL.Host") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw host w configu");
            }
            if (config.getString("MySQL.User") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw usera w configu");
                        }
            if (config.getString("MySQL.Password") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw haslo w configu");
                      }
            if (config.getString("MySQL.Database") == null) {
              MySQL = false;
              addLog(pref + "MySQL-ustaw nazwe bazy danych w configu");
                     }
            if (config.getString("MySQL.Port") == null) {
                  MySQL = false;
                  addLog(pref + "MySQL-ustaw port w configu");
                              }

            if (MySQL) {
            	mysql = new MySQLDatabase(this, config.getString("MySQL.Host"), config.getString("MySQL.User"), config.getString("MySQL.Port"), config.getString("MySQL.Password"), config.getString("MySQL.Database"));
              addLog(pref + "MySQL-Laczenie...");
              try
              {
            	  mysql.open();
              } catch (Exception e) {
                addLog(pref + "MySQL-probemy z laczeniem..." + e.getMessage());
              }

              if (mysql.checkConnection()) {
                addLog(pref + "MySQL-pomyslnie polaczono z baza danych");

                if (!mysql.checkTable("GunGameArena")) {
                 addLog(pref + "MySQL- Tabela 'GunGameArena' nie istnieje. Tworzenie...");
                  String query = "CREATE TABLE GunGameArena (name text, spawnpoints text, size int)";
                  mysql.createTable(query);
                }
                if (!mysql.checkTable("GunGameSigns")) {
                    addLog(pref + "MySQL- Tabela 'GunGameSigns' nie istnieje. Tworzenie...");
                     String query = "CREATE TABLE GunGameSigns (name text, location text)";
                     mysql.createTable(query);
                   }
                if (!mysql.checkTable("GunGameRating")) {
                    addLog(pref + "MySQL- Tabela 'GunGameRating' nie istnieje. Tworzenie...");
                     String query = "CREATE TABLE GunGameRating (nick char(50), wins int, lost int, kills int, deaths int, elo int)";
                     mysql.createTable(query);
                   }
                if (!mysql.checkTable("GoPoints")) {
                    addLog(pref + "MySQL- Tabela 'GoPoints' nie istnieje. Tworzenie...");
                     String query = "CREATE TABLE GoPoints (nick char(50), points int)";
                     mysql.createTable(query);
                   }
              }
              else {
                addLog(pref + "MySQL-polaczenie nieudane");
              }
    	//====================================================================
              
       Bukkit.getServer().getPluginManager().registerEvents(new SignEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new InvEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new QuitEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new EntityEvents(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new RespawnEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new TabEvent(), this);
       Bukkit.getServer().getPluginManager().registerEvents(new InvClickEvent(), this);

        getCommand("opusc").setExecutor(new OpuscCmd());
        getCommand("top").setExecutor(new TopCmd());
        getCommand("elo").setExecutor(new EloCmd());

        
              Load.loadMysql();
              Load.loadItems();
              Rank.loadRanking();
    }
    }
	public void onDisable() {
    }
	//METHODS
	public static void addLog(String string){
		System.out.println(string); 
	}
	//-------------
    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //---------------
    public void saveYamls() {
        try {
            config.save(configFile);
        } catch (IOException e) {
        	addLog(pref + "blad przy zapisie pliku konfiguracyjnego");
            e.printStackTrace();
        }
    }
    //-----------------
    public void loadYamls() {
        try {
            config.load(configFile);
        } catch (Exception e) {
        	addLog(pref + "blad przy ladowaniu pliku konfiguracyjnego");
            e.printStackTrace();
        }
    }
    private void firstRun() throws Exception {
        if(!configFile.exists()){
        	addLog(pref + "brak pliku konfiguracyjnego. Generowanie...");
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }  
    }
    
}