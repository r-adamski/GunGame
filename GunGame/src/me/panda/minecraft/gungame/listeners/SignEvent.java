package me.panda.minecraft.gungame.listeners;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.objects.Arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignEvent implements Listener{

	@EventHandler
	public static void onDestroySign(BlockBreakEvent e){
		Material mat = e.getBlock().getType();
		if(mat == Material.SIGN || mat == Material.SIGN_POST || mat == Material.WALL_SIGN){
			Sign sign = (Sign) e.getBlock().getState();
			if(sign.getLine(0).equals("§e§lGunGame")){
				if(e.getPlayer().isOp() || e.getPlayer().hasPermission("gungame.admin")){
					String name = sign.getLine(1).replace("§6§l", "");
					Arena a = Info.getArenaOnName(name);
					if(a != null){
						if(Info.isCorrectSign(sign, a)){
							Main.signs.remove(a.getName());
							Thread t = new Thread(new Runnable(){
								@Override
								public void run(){
									String insert = "DELETE FROM GunGameSigns WHERE name='"+a.getName()+"'";
									Main.mysql.query(insert);
								}});
								t.start();
							e.getPlayer().sendMessage(Main.pref + " Tabliczka usunieta!");
						}
					}
					else{
						e.getPlayer().sendMessage(Main.pref + " Arena do tabliczki nie istnieje. Usuwanie tabliczki..");
						Main.signs.remove(name);
						Thread t = new Thread(new Runnable(){
							@Override
							public void run(){
								String insert = "DELETE FROM GunGameSigns WHERE name='"+name+"'";
								Main.mysql.query(insert);
							}});
							t.start();
					}
			
				}
				else{
					e.setCancelled(true);
					e.getPlayer().sendMessage(Main.pref + " Nie masz uprawnien aby zniszczyc tabliczke!");
				}
					
			}
		}
	}
	
	@EventHandler
	public static void onSign(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("gungame")){
			if(e.getPlayer().isOp() || e.getPlayer().hasPermission("gungame.admin")){
			if(Info.getArenaOnName(e.getLine(1)) != null){
				final Arena a = Info.getArenaOnName(e.getLine(1));
				if(!Main.signs.containsKey(a.getName())){
				e.setLine(0, "§e§lGunGame");
				e.setLine(1, "§6§l"+a.getName());
				e.setLine(2, "§e§l0§8" + "/§e§l" + a.getSize());
				e.setLine(3, "§2§lDOLACZ PPM");
				
				e.getPlayer().sendMessage(Main.pref + " Stworzono tabliczke!");
				StringBuilder sb = new StringBuilder();
				Location loc = e.getBlock().getLocation();
				sb.append(loc.getWorld().getName()).append(":");
				sb.append(loc.getBlockX()).append(":");
				sb.append(loc.getBlockY()).append(":");
				sb.append(loc.getBlockZ());
				final String location = sb.toString();

				Main.signs.put(a.getName(), loc);
				Thread t = new Thread(new Runnable(){
					@Override
					public void run(){
						String insert = "INSERT INTO GunGameSigns (`name`, `location`) VALUES ('" + a.getName() + "', '"+location+"')";
						Main.mysql.query(insert);
					}});
					t.start();
				
				}
				else{
					e.getPlayer().sendMessage(Main.pref + " Blad: tabliczka do tej areny juz istnieje.");
					e.setCancelled(true);
				}
			}
			else{
				e.getPlayer().sendMessage(Main.pref + " Brak areny o podanej nazwie!");
				e.setCancelled(true);
			}
		}
		}
	}
	
	@EventHandler
	public static void onInterract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Material block = e.getClickedBlock().getType();
			if(block == Material.WALL_SIGN || block == Material.SIGN || block == Material.SIGN_POST){
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).equals("§e§lGunGame")){
				String name = sign.getLine(1).replace("§6§l", "");
				Arena a = Info.getArenaOnName(name);
					if(a != null){
						if(Info.isCorrectSign(sign, a)){
						Manager.joinArena(a, e.getPlayer());
						Manager.updateSign(a);
						}
						else{
							e.getPlayer().sendMessage(Main.pref + " Tabliczka do tej areny znajduje sie gdzie indziej. Usuwanie starej tabliczki..");
							e.getClickedBlock().setType(Material.AIR);
						}
					}
					else
						e.getPlayer().sendMessage(Main.pref + " Arena o podanej nazwie nie istnieje :z");
				}
			}
		}
	}
	
}
