package me.panda.minecraft.gungame.commands;


import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Info;
import me.panda.minecraft.gungame.methods.Manager;
import me.panda.minecraft.gungame.objects.Arena;
import me.panda.minecraft.gungame.objects.Game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class OpuscCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("opusc")){
				if(args.length == 0){
					if(sender instanceof Player){
					Player p = (Player)sender;
						if(Info.isOnArena(p)){
						Game g = Info.getPlayerGame(p);
						if(!g.isInProgress()){
							g.remove(p);
							String name = Info.getArenaofGame(g);
							Main.map_game.put(name, g);
							p.getInventory().clear();
							p.updateInventory();
							p.setLevel(0);
							p.removePotionEffect(PotionEffectType.SPEED);
							p.removePotionEffect(PotionEffectType.JUMP);
							p.teleport(Main.spawn);
							p.sendMessage(Main.pref + " Opusciles arene!");
							Arena a = Info.getArenaOnName(name);
							Manager.updateSign(a);
						}
						else
							sender.sendMessage(Main.pref + " Blad: Nie mozesz teraz opuscic areny. Gra juz wystartowala!");
						}
						else
							sender.sendMessage(Main.pref + " Blad: nie jestes na arenie.");
					}
					else
						sender.sendMessage(Main.pref + " Blad: komenda dostepna tylko dla graczy.");
				}
				else
					sender.sendMessage(Main.pref + " Blad: poprawna komenda /opusc");
				
	}
		return false;
	}
}
