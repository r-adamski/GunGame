package me.panda.minecraft.gungame.commands;


import me.panda.minecraft.gungame.Main;
import me.panda.minecraft.gungame.methods.Rank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EloCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("elo")){
				if(args.length <= 1){
					if(args.length == 0){
						if(sender instanceof Player){
							sender.sendMessage(Main.pref + " Twoj poziom Elo Ratingu  wynosi: " + Main.score + Main.map_ranking.get(sender.getName()).getElo() + 
						Main.special + " | " + Main.normal + "jestes " + Main.score + Rank.getPosition(sender.getName()) + Main.normal + " w rankingu" );
							sender.sendMessage(Main.normal + " Zwyciestwa: " + Main.score + Main.map_ranking.get(sender.getName()).getWins());
							sender.sendMessage(Main.normal + " Porazeki: " + Main.score + Main.map_ranking.get(sender.getName()).getLost());
							sender.sendMessage(Main.normal + " Kille: " + Main.score + Main.map_ranking.get(sender.getName()).getKills());
							sender.sendMessage(Main.normal + " Smierci: " + Main.score + Main.map_ranking.get(sender.getName()).getDeaths());
							if(Main.map_ranking.get(sender.getName()).getDeaths() != 0){
								double kd = Main.map_ranking.get(sender.getName()).getKills() / Main.map_ranking.get(sender.getName()).getDeaths();
								sender.sendMessage(Main.normal + " K/D ratio: " + Main.score + kd);
							}
							else{
								sender.sendMessage(Main.normal + " K/D ratio: " + Main.score + Main.map_ranking.get(sender.getName()).getKills());
							}
							if(Main.map_ranking.get(sender.getName()).getLost() != 0){
								double winratio = Main.map_ranking.get(sender.getName()).getWins() / Main.map_ranking.get(sender.getName()).getLost();
								sender.sendMessage(Main.normal + " WIN ratio: " + Main.score + winratio);

							}
							else{
								sender.sendMessage(Main.normal + " WIN ratio: " + Main.score + Main.map_ranking.get(sender.getName()).getWins());

							}

						}
						else
							sender.sendMessage("Blad: Musisz byc graczem aby wykonac ta komende!");
					}
					else{
						String nick_other = args[0];
						if(Main.map_ranking.containsKey(nick_other)){
							sender.sendMessage(Main.pref + " Poziom Elo Ratingu " + Main.player + nick_other + Main.normal+" wynosi: " + Main.score + Main.map_ranking.get(nick_other).getElo() + 
									Main.special + " | " + Main.normal + "jest " + Main.score + Rank.getPosition(nick_other) + Main.normal + " w rankingu" );
										sender.sendMessage(Main.normal + " Zwyciestwa: " + Main.score + Main.map_ranking.get(nick_other).getWins());
										sender.sendMessage(Main.normal + " Porazeki: " + Main.score + Main.map_ranking.get(nick_other).getLost());
										sender.sendMessage(Main.normal + " Kille: " + Main.score + Main.map_ranking.get(nick_other).getKills());
										sender.sendMessage(Main.normal + " Smierci: " + Main.score + Main.map_ranking.get(nick_other).getDeaths());
										if(Main.map_ranking.get(nick_other).getDeaths() != 0){
											double kd = Main.map_ranking.get(nick_other).getKills() / Main.map_ranking.get(nick_other).getDeaths();
											sender.sendMessage(Main.normal + " K/D ratio: " + Main.score + kd);
										}
										else{
											sender.sendMessage(Main.normal + " K/D ratio: " + Main.score + Main.map_ranking.get(nick_other).getKills());
										}
										if(Main.map_ranking.get(nick_other).getLost() != 0){
											double winratio = Main.map_ranking.get(nick_other).getWins() / Main.map_ranking.get(nick_other).getLost();
											sender.sendMessage(Main.normal + " WIN ratio: " + Main.score + winratio);

										}
										else{
											sender.sendMessage(Main.normal + " WIN ratio: " + Main.score + Main.map_ranking.get(nick_other).getWins());

										}
						}
						else
							sender.sendMessage(Main.pref + " Blad: Gracz o podanym nicku nie istnieje!");
					}
					
					
				}
				else
					sender.sendMessage(Main.pref + " Blad: poprawna komenda /elo lub /elo <nick>");
				
	}
		return false;
	}
}
