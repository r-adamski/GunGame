package me.panda.minecraft.gungame.listeners;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.UUID;
import java.util.Map.Entry;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Items;
import me.panda.minecraft.gungame.objects.Rating;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TabEvent implements Listener{

	public static String o = "dsdtu";
    public static LinkedList<String> list = new LinkedList<String>();
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
            final Player p = e.getPlayer();
           
    		if(!Main.map_ranking.containsKey(p.getName())){
    			Thread t = new Thread(new Runnable(){
    			@Override
    			public void run(){
    				String insert = "INSERT INTO GunGameRating (`nick`, `wins`, `lost`, `kills`, `deaths`, `elo`) VALUES ('" + p.getName() + "', '0', '0', '0', '0', '1000')";
    				Main.mysql.query(insert);
    			}});
    			t.start();
    				
    			Rating newrating = new Rating(0, 0, 0, 0, 1000);
    			Main.map_ranking.put(p.getName(), newrating);
    		}
            
    		if(!Main.map_points.containsKey(p.getName())){
    			Thread t1 = new Thread(new Runnable(){
    			@Override
    			public void run(){
    				String insert = "INSERT INTO GoPoints (`nick`, `points`) VALUES ('" + p.getName() + "', '0')";
    				Main.mysql.query(insert);
    			}});
    			t1.start();
    			Main.map_points.put(p.getName(), 0);
    		}
    		
            Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){@SuppressWarnings("deprecation")
			@Override
            public void run() {
                   
                    setPlayerTab(p);       
                    removeAllNick(p);
                    p.setHealth(20); p.setFoodLevel(20); p.setFireTicks(0); p.getInventory().clear(); for (PotionEffect pe : p.getActivePotionEffects()) p.removePotionEffect(pe.getType());
                    
            		p.teleport(Main.spawn);
            		Items.addStartEQ(p);
            		
                    for (Player g : Bukkit.getOnlinePlayers()){
                            if (g.getScoreboard().getTeam("§3Online:§c ")!=null)
                            g.getScoreboard().getTeam("§3Online:§c ").setSuffix(Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers());
                    }
            }}, 30L);
           
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onQuit(PlayerQuitEvent e){
                    Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable(){
						@Override
                            public void run() {
                                   
                                    for (Player g : Bukkit.getOnlinePlayers()){
                                            if (g.getScoreboard().getTeam("§3Online:§c ")!=null)
                                            g.getScoreboard().getTeam("§3Online:§c ").setSuffix(Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers());
                                    }
                            }}, 30L);
    }
    public static void refreshTop(Player p){
    		
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

            p.getScoreboard().getTeam("§2§91. §7").setSuffix(nick1);
            p.getScoreboard().getTeam("§2§92. §7").setSuffix(nick2);
            p.getScoreboard().getTeam("§2§93. §7").setSuffix(nick3);
            p.getScoreboard().getTeam("§2§94. §7").setSuffix(nick4);
            p.getScoreboard().getTeam("§2§95. §7").setSuffix(nick5);
            p.getScoreboard().getTeam("§2§96. §7").setSuffix(nick6);
            p.getScoreboard().getTeam("§2§97. §7").setSuffix(nick7);
            p.getScoreboard().getTeam("§2§98. §7").setSuffix(nick8);
            p.getScoreboard().getTeam("§2§99. §7").setSuffix(nick9);
            p.getScoreboard().getTeam("§2§910. §7").setSuffix(nick10);

            
             elo1 = 0;
    		 elo2 = 0;
    		 elo3 = 0;
    		 elo4 = 0;
    		 elo5 = 0;
    		 elo6 = 0;
    		 elo7 = 0;
    		 elo8 = 0;
    		 elo9 = 0;
    		 elo10 = 0;
    		
    		 nick1 = "";
    		 nick2 = "";
    		 nick3 = "";
    		 nick4 = "";
    		 nick5 = "";
    		 nick6 = "";
    		 nick7 = "";
    		 nick8 = "";
    		 nick9 = "";
    		 nick10 = "";
    		
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
    		
    		 p.getScoreboard().getTeam("§a§91. §7").setSuffix(nick1);
             p.getScoreboard().getTeam("§a§92. §7").setSuffix(nick2);
             p.getScoreboard().getTeam("§a§93. §7").setSuffix(nick3);
             p.getScoreboard().getTeam("§a§94. §7").setSuffix(nick4);
             p.getScoreboard().getTeam("§a§95. §7").setSuffix(nick5);
             p.getScoreboard().getTeam("§a§96. §7").setSuffix(nick6);
             p.getScoreboard().getTeam("§a§97. §7").setSuffix(nick7);
             p.getScoreboard().getTeam("§a§98. §7").setSuffix(nick8);
             p.getScoreboard().getTeam("§a§99. §7").setSuffix(nick9);
             p.getScoreboard().getTeam("§a§910. §7").setSuffix(nick10);
    	
            /*try {
                    String select = "SELECT * FROM xVSx_Ranking ORDER BY points ASC LIMIT 10";
                    ResultSet rs = Main.mysql.query(select);
                    int i=1;
                    while (rs.next()){
                            p.getScoreboard().getTeam("§2§9"+i+". §7").setSuffix(p.getName());
                            i++;
                    }
                    i=1;
                    String select1 = "SELECT * FROM xVSx_Ranking ORDER BY points ASC LIMIT 10";
                    ResultSet rs1 = Main.mysql.query(select1);
                    while (rs1.next()){
                            p.getScoreboard().getTeam("§a§9"+i+". §7").setSuffix(p.getName());
                            i++;
                    }
            } catch (Exception e){
                    e.printStackTrace();
            }*/
    }
    public static void refreshRanking(Player p){
        p.getScoreboard().getTeam("§3Wygrane: ").setSuffix("§c" + Main.map_ranking.get(p.getName()).getWins());
        p.getScoreboard().getTeam("§3Przegrane: ").setSuffix("§c" + Main.map_ranking.get(p.getName()).getLost());
            p.getScoreboard().getTeam("§3Elo: ").setSuffix("§c" + Main.map_ranking.get(p.getName()).getElo());
            int kills = Main.map_ranking.get(p.getName()).getKills();
            int deaths = Main.map_ranking.get(p.getName()).getDeaths();
            p.getScoreboard().getTeam("§3Zabojstwa: ").setSuffix("§c" + kills);
            p.getScoreboard().getTeam("§3Smierci: ").setSuffix("§c" + deaths);
            refreshTop(p);
    }
    @SuppressWarnings("deprecation")
	public static void setPlayerTab(Player p){
        int kills = Main.map_ranking.get(p.getName()).getKills();
        int deaths = Main.map_ranking.get(p.getName()).getDeaths();
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard sb = manager.getNewScoreboard();
            addLine(sb, new String[]{"§c", "=--=--=--=--=--="}, new String[]{"§d§c", "=--=--=--=--=--="}, new String[]{"§e§c", "=--=--=--=--=--="});
            addLine(sb, new String[]{"§e§a§c", ""}, new String[]{"§3§l", "GoPvP - GunGame"}, new String[]{"§e§c§e", ""});
            addLine(sb, new String[]{"§a§c", "=--=--=--=--=--="}, new String[]{"§2§d§c", "=--=--=--=--=--="}, new String[]{"§4§c", "=--=--=--=--=--="});
            addLine(sb, new String[]{"§1§3§2", ""}, new String[]{"§1§3§4", ""}, new String[]{"§1§3§5", ""});
            addLine(sb, new String[]{"§e§2", "TOP 10 ELO"}, new String[]{"§3Nick: §c", p.getName()}, new String[]{"§a§2", "TOP 10 WINOW"});
            addLine(sb, new String[]{"§2§91. §7", ""}, new String[]{"§3Elo: ", "§c"+Main.map_ranking.get(p.getName()).getElo()}, new String[]{"§a§91. §7", ""});
            addLine(sb, new String[]{"§2§92. §7", ""}, new String[]{"§3Wygrane: ", "§c"+Main.map_ranking.get(p.getName()).getWins()}, new String[]{"§a§92. §7", ""});
            addLine(sb, new String[]{"§2§93. §7", ""}, new String[]{"§3Przegrane: ", "§c"+Main.map_ranking.get(p.getName()).getLost()}, new String[]{"§a§93. §7", ""});
            addLine(sb, new String[]{"§2§94. §7", ""}, new String[]{"§3Zabojstwa: ", "§c"+kills}, new String[]{"§a§94. §7", ""});
            addLine(sb, new String[]{"§2§95. §7", ""}, new String[]{"§3Smierci: ", "§c"+deaths}, new String[]{"§a§95. §7", ""});
            addLine(sb, new String[]{"§2§96. §7", ""}, new String[]{"§5§6§4§6",""}, new String[]{"§a§96. §7", ""});
            addLine(sb, new String[]{"§2§97. §7", ""}, new String[]{"§3Online:§c ", Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers()}, new String[]{"§a§97. §7", ""});
            addLine(sb, new String[]{"§2§98. §7", ""}, new String[]{"§1§1§1§2", ""}, new String[]{"§a§98. §7", ""});
            addLine(sb, new String[]{"§2§99. §7", ""}, new String[]{"§2 Informacje ", "VIP"}, new String[]{"§a§99. §7", ""});
            addLine(sb, new String[]{"§2§910. §7", ""}, new String[]{"§6§l", "      /vip"}, new String[]{"§a§910. §7", ""});
            addLine(sb, new String[]{"§1§2§3", ""}, new String[]{"§5§3§3", ""}, new String[]{"§1§2§2", ""});
            addLine(sb, new String[]{"§9§1§c", "=--=--=--=--=--="}, new String[]{"§8§d§1§c", "=--=--=--=--=--="}, new String[]{"§7§1§c", "=--=--=--=--=--="});
            addLine(sb, new String[]{"§a§3", "TeamSpeak"}, new String[]{"§c§3", "Strona"}, new String[]{"§e§3", "FunPage"});
            addLine(sb, new String[]{"§2§7", "ts.gopvp.pl"}, new String[]{"§a§7", "www.gopvp.pl"}, new String[]{"§e§7", "fb.com/gopvppl"});
            addLine(sb, new String[]{"§9§c", "=--=--=--=--=--="}, new String[]{"§8§d§c", "=--=--=--=--=--="}, new String[]{"§7§c", "=--=--=--=--=--="});

            //removeNick(p);

            p.setScoreboard(sb);
            rowLines(p);
            refreshTop(p);
    }
    public static void rowLines(Player p){
            for (int i=0; i<list.size(); i=i+3){
                    //System.out.println("row: " + list.get(i)+" "+list.get(i+1)+" "+list.get(i+2));
                    row(p, list.get(i), list.get(i+1), list.get(i+2));
            }
           
    }
           
    public static void addLine(Scoreboard sb, String[] s1, String[] s2, String[] s3){
            registerTeam(sb, s1[0], s1[1]);
            registerTeam(sb, s2[0], s2[1]);
            registerTeam(sb, s3[0], s3[1]);
    }
    @SuppressWarnings("deprecation")
	public static void registerTeam(Scoreboard sb, String name, String suffix){
            //System.out.println("Tworze team: " + name + " suffix: " + suffix);
            if (!list.contains(name))list.add(name);
            Team t = sb.registerNewTeam(name);
            t.addPlayer(Bukkit.getOfflinePlayer(name));
            t.setSuffix(suffix);
    }


    public static void row(Player p, String s1, String s2, String s3){
            if (s1.length()<16) s1=s1.substring(0, s1.length()); else s1=s1.substring(0, 15);
            if (s2.length()<16) s2=s2.substring(0, s2.length()); else s2=s2.substring(0, 15);
            if (s3.length()<16) s3=s3.substring(0, s3.length()); else s3=s3.substring(0, 15);
           

            GameProfile gm1 = new GameProfile(UUID.randomUUID(), s1);
            GameProfile gm2 = new GameProfile(UUID.randomUUID(), s2);
            GameProfile gm3 = new GameProfile(UUID.randomUUID(), s3);


        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(getPacket(gm1));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(getPacket(gm2));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(getPacket(gm3));


    }
    @SuppressWarnings("deprecation")
	public static void removeAllNick(Player p){
            try {
                    for (Player g : Bukkit.getOnlinePlayers()){
                    //      System.out.println("usuwam gracza " + g.getName());
                            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
                            Field action = packet.getClass().getDeclaredField("action");
                            action.setAccessible(true);
                            action.setInt(packet, 4);
                            Field nick = packet.getClass().getDeclaredField("username");
                            nick.setAccessible(true);
                            nick.set(packet, g.getName());
                            Field player = packet.getClass().getDeclaredField("player");
                            player.setAccessible(true);
                            GameProfile gm = new GameProfile(g.getUniqueId(), g.getName());
                            player.set(packet, gm);
                           
                            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
                    }
            } catch (Exception e){
                    e.printStackTrace();
            }
    }
    public static void removeNick(Player p){
            try {
                   
                    PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
                    Field action = packet.getClass().getDeclaredField("action");
                    action.setAccessible(true);
                    action.setInt(packet, 4);
                    Field nick = packet.getClass().getDeclaredField("username");
                    nick.setAccessible(true);
                    nick.set(packet, p.getName());
                    Field player = packet.getClass().getDeclaredField("player");
                    player.setAccessible(true);
                    GameProfile gm = new GameProfile(p.getUniqueId(), p.getName());
                    player.set(packet, gm);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
                   
                   
                   
            } catch (Exception e){
                    e.printStackTrace();
            }
    }
    public static PacketPlayOutPlayerInfo getPacket(GameProfile profile){          
            try {
                   
                    PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();

                    Field action = packet.getClass().getDeclaredField("action");
                    action.setAccessible(true);
                    action.setInt(packet, 0);
           
                    Field ping = packet.getClass().getDeclaredField("ping");
                    ping.setAccessible(true);
                    ping.setInt(packet, 0);
           
                    Field gm = packet.getClass().getDeclaredField("gamemode");
                    gm.setAccessible(true);
                    gm.setInt(packet, 0);
           
                    Field nick = packet.getClass().getDeclaredField("username");
                    nick.setAccessible(true);
                    nick.set(packet, profile.getName());
           
                    Field player = packet.getClass().getDeclaredField("player");
                    player.setAccessible(true);
                    player.set(packet, profile);
                   
                    return packet;
                   
            } catch (Exception e){
                    e.printStackTrace();
            }
            return null;
    }
	
}
