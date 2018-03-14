package me.panda.minecraft.gungame.objects;

import java.util.List;

import org.bukkit.inventory.ItemStack;


public class Kit {
	
	private int lvl;
	private List<ItemStack> is;
	private List<Effect> effect;
	
		public Kit(List<ItemStack> is, List<Effect> effect, int lvl){
			this.is = is;
			this.effect = effect;
			this.lvl = lvl;
	    }
		
		public List<ItemStack> getItems(){ return this.is; }
		public List<Effect> getEffects(){ return this.effect; }
		public int getLvl(){ return this.lvl; }
}
