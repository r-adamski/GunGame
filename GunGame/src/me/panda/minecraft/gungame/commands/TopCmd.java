package me.panda.minecraft.gungame.commands;


import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("Top")){
				if(args.length <= 1){
					if(args.length == 0){
						Player p = (Player)sender;
						Rank.sendTopElo(p);
						}
					else if(args.length == 1){
						if(args[0].equalsIgnoreCase("win")){
							Player p = (Player)sender;
							Rank.sendTopWins(p);
						}
						else if(args[0].equalsIgnoreCase("kill")){
							Player p = (Player)sender;
							Rank.sendTopKills(p);
						}
						else
							sender.sendMessage(Main.pref + " Poprawna komenda /top lub /top win/kill");

					}
				}
				else
					sender.sendMessage(Main.pref + " Poprawna komenda /top lub /top win/kill");
	}
		return false;
	}
}
