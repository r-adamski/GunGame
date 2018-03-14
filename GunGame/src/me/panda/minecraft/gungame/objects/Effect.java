package me.panda.minecraft.gungame.objects;

import org.bukkit.potion.PotionEffectType;


public class Effect {
	
	PotionEffectType potion;
	int time;
	int lvl;
	
		public Effect(PotionEffectType potion, int time, int lvl){
			this.potion = potion;
			this.time = time;
			this.lvl = lvl;
	    }
		
		public PotionEffectType getPotion(){ return this.potion; }
		public int getTIme(){ return this.time; }
		public int getLvl(){ return this.lvl; }
}
