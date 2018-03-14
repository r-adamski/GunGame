package me.panda.minecraft.gungame.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Items;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Game;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InvEvent implements Listener{
	public static List<String> pfire = new ArrayList<String>();

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onInterract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			ItemStack b = e.getPlayer().getItemInHand();
			if(b.hasItemMeta() && b.getItemMeta().getDisplayName() != null){
				if(Info.isOnArena(e.getPlayer())){
					if(b.getItemMeta().getDisplayName().equals("§6§lSKLEP")){
						e.setCancelled(true);
						e.getPlayer().sendMessage(Main.pref + " Sklep niedlugo dodamy :)!");
						e.getPlayer().updateInventory();
						//open shop
					} else if( b.getItemMeta().getDisplayName().equals("§c§lWYJSCIE")){
						e.setCancelled(true);
						Player p = e.getPlayer();
						if(!Main.curr_joined.contains(p.getName())){
						Game g = Info.getPlayerGame(p);
							g.remove(p);
							String name = Info.getArenaofGame(g);
							Main.map_game.put(name, g);
							p.getInventory().clear();
							p.setLevel(0);
							Items.addStartEQ(p);
							p.updateInventory();
							p.removePotionEffect(PotionEffectType.SPEED);
							p.removePotionEffect(PotionEffectType.JUMP);
							p.teleport(Main.spawn);
							p.sendMessage(Main.pref + " Opusciles arene!");
							Arena a = Info.getArenaOnName(name);
							Manager.updateSign(a);
							
						}
					}
				} else if (b.getType() == Material.NETHER_STAR){
					Player p = e.getPlayer();
					if (b.getItemMeta().getDisplayName()!=null){
						if (b.getItemMeta().getDisplayName().contains("VIP")){
							e.setCancelled(true);
							if (!p.hasPermission("gopvp.vip")){
								p.sendMessage("§cOpcja dostepna tylko dla kont §6§lVIP§c! Mozesz je kupic na stronie §a§lgopvp.pl");
								return;
							}
							Items.openVipEQ(p);
						}
					}
				} else if (b.getType() == Material.POTION){
					Player p = e.getPlayer();
					if (b.getItemMeta().getDisplayName()!=null){
						if (b.getItemMeta().getDisplayName().contains("Skakanie")){
							e.setCancelled(true);
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 15), true);
							p.sendMessage(Main.pref + " Aktywowano efekt skakania!");
						}
					}
				} else if (b.getTypeId() == 175){
					Player p = e.getPlayer();
					if (b.getItemMeta().getDisplayName()!=null){
						if (b.getItemMeta().getDisplayName().contains("Statystyki")){
							e.setCancelled(true);
							Items.openStatsEQ(p);
						}
					}
				} else if (b.getType() == Material.FIREWORK){
					Player p = e.getPlayer();
					if (pfire.contains(p.getName())){
						e.setCancelled(true);
						p.sendMessage("§3§lGoPvP §9§l>> §cDostepne co 5 sekund!");
					} else {
						e.setCancelled(true);
						 Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
				            FireworkMeta fwm = fw.getFireworkMeta();
				           
				            //Our random generator
				            Random r = new Random();   
				 
				            //Get the type
				            int rt = r.nextInt(4) + 1;
				            Type type = Type.BALL;       
				            if (rt == 1) type = Type.BALL;
				            if (rt == 2) type = Type.BALL_LARGE;
				            if (rt == 3) type = Type.BURST;
				            if (rt == 4) type = Type.CREEPER;
				            if (rt == 5) type = Type.STAR;
				           
				            //Get our random colours   
				            @SuppressWarnings("unused")
							int r1i = r.nextInt(17) + 1;
				            @SuppressWarnings("unused")
							int r2i = r.nextInt(17) + 1;
				            Color c1 = Color.GREEN;
				            Color c2 = Color.YELLOW;
				            Color c3 = Color.AQUA;

				           
				            //Create our effect with this
				            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withColor(c3).withFade(c2).with(type).trail(r.nextBoolean()).build();
				           
				            //Then apply the effect to the meta
				            fwm.addEffect(effect);
				           
				            //Generate some random power and set it
				            int rp = 1;
				            fwm.setPower(rp);
				           
				            //Then apply this to our rocket
				            fw.setFireworkMeta(fwm);     
				            
				            final ItemStack fire = new ItemStack(Material.FIREWORK);
						 	ItemMeta imfire = fire.getItemMeta();
						    imfire.setDisplayName("§a§lFajerwerki");
						    imfire.setLore(Arrays.asList(new String[]{"§6Pokaz wszystkim gdzie jestes i spraw, aby serwer mienil sie kolorami!"}));
						    fire.setItemMeta(imfire);
						    
						    pfire.add(p.getName());

						    Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){
								@Override
								public void run() {
									p.getInventory().setHelmet(null);
									p.getInventory().setItem(5, fire);
									p.updateInventory();
								}
							}, 10L);
						    
						    Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){
								@Override
								public void run() {
									if (pfire.contains(p.getName())) pfire.remove(p.getName());
								}
							}, 100L);
					}
					
				}
			}
		}
	}
}
