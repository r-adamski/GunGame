package me.panda.minecraft.gungame.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;



public class InvClickEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClicked(InventoryClickEvent e){
		final Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equals("§6§lVIP §2§lMENU")){
				if (e.getRawSlot() < 54 && e.getRawSlot() > -1){
					ItemStack clicked = e.getCurrentItem();
					ItemStack cursor = e.getCursor();
					e.setCancelled(true);
					if (cursor.getTypeId() == 0){
						if (clicked.getType() == Material.MONSTER_EGG){
							p.performCommand("skin");
						} else if (clicked.getType() == Material.LEATHER_HELMET){
							p.performCommand("disco");
						} else if (clicked.getType() == Material.DIAMOND_BOOTS){
							//p.setFlying(true);
							p.performCommand("buty");
						}
					}
				}
			} else if (e.getInventory().getName().equals("§4§lStatystyki")){
				if (e.getRawSlot() < 54 && e.getRawSlot() > -1){
					e.setCancelled(true);
				}
			}
		}

}
