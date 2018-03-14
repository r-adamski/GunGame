package me.panda.minecraft.gungame.objects;

public class Rating {
		public  int wins;
		public int lost;
		public int kills;
		public int deaths;
		public int elo;

		public Rating(int wins, int lost, int kills, int deaths, int elo){
	        this.wins = wins;
	        this.lost = lost;
	        this.kills = kills;
	        this.deaths = deaths;
	        this.elo = elo;
	    }
		public int getWins(){ return this.wins; }
		public int getLost(){ return this.lost; }
		public int getKills(){ return this.kills; }
		public int getDeaths(){ return this.deaths; }
		public int getElo(){ return this.elo; }
		
		public void addWin(){
			this.wins++;
		}
		
		public void addLost(){
			this.lost++;
		}
		
		public void addKill(){
			this.kills++;
		}
		
		public void addDeath(){
			this.deaths++;
		}
		
		public void setElo(int elo){
			this.elo = elo;
		}
}

