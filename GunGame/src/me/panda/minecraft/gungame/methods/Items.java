package me.panda.minecraft.gungame.methods;

import java.util.Arrays;

import me.panda.minecraft.gungame.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;

public class Items {
	@SuppressWarnings("deprecation")
	public static void addStartEQ(Player p){
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		for (PotionEffect pe : p.getActivePotionEffects()) p.removePotionEffect(pe.getType());
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFireTicks(0);
		
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
			ItemMeta imcompass = compass.getItemMeta();
			imcompass.setDisplayName("§b§lWybierz tryb");
			imcompass.setLore(Arrays.asList(new String[]{"§6§lWybierz server, na ktorym chcesz grac!"}));
			compass.setItemMeta(imcompass);
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
			BookMeta imbook = (BookMeta) book.getItemMeta();
			imbook.setAuthor("GoPvP");
			imbook.setTitle("§a§lInformacje ogolne");
			imbook.addPage("1"); imbook.addPage("2"); imbook.addPage("3"); imbook.addPage("4");
			imbook.setPage(1, "§1§lWitaj na \n GunGame GoPvP.PL!  \n\n\n§5SPIS TRESCI\n\n§91. §cinformacje ogolne\n§92. §ckomendy\n§93. §cprzedmioty\n§94. §ckonto §6§lVIP");
			imbook.setPage(2, "§4§lINFORMACJE OGOLNE\n\n§2GunGame to tryb, w ktorym zabijajac innych zdobywasz punkty i nabijasz levele. Im wiekszy lv tym lepsza masz eq");
			imbook.setPage(3, "§4§lPODSTAWOWE KOMENDY\n\n§9/gungame§7 - informacje\n\n§9/opusc§7 - opuszcza arene\n§9/vip§7 - pokazuje info o VIP");
			imbook.setPage(4, "§4§lKONTO VIP\n\n§2PRZYWILEJE\n§9Aby zobaczyc przywileje VIP'a wpisz §6§l/vip!\n\n§2KUPNO\n§9Aby kupic konto VIP wejdz na strone §cGoPvP.PL!");
			book.setItemMeta(imbook);
		ItemStack star = new ItemStack(Material.NETHER_STAR);
			ItemMeta imstar = star.getItemMeta();
			imstar.setDisplayName("§6§lVIP§2§l MENU");
			imstar.setLore(Arrays.asList(new String[]{"§5Specjalnie dla VIP", "§b§l● §6§lMagiczne buty §e(niesamowite efekty gdy chodzisz!)", "§b§l● §6§lZamiana w moba §e(walcz z kims bedac mobem!)", "§b§l● §a§lD§5§li§c§ls§6§lc§7§lo §2§lZ§1§lb§4§lr§e§lo§3§lj§8§la §e(mien sie na wszystkie kolory teczy!)"}));
			star.setItemMeta(imstar);
		ItemStack fire = new ItemStack(Material.FIREWORK);
		 	ItemMeta imfire = fire.getItemMeta();
		    imfire.setDisplayName("§a§lFajerwerki");
		    imfire.setLore(Arrays.asList(new String[]{"§6Pokaz wszystkim gdzie jestes i spraw, aby serwer mienil sie kolorami!"}));
		    fire.setItemMeta(imfire);
		ItemStack potion = new ItemStack(Material.POTION);
			ItemMeta impotion = potion.getItemMeta();
			impotion.setDisplayName("§2§lSkakanie");
			impotion.setLore(Arrays.asList(new String[]{"§6Aktywuj efekt skakania!"}));
			potion.setItemMeta(impotion);
		ItemStack sun = new ItemStack(175, 1);
			ItemMeta imsun = sun.getItemMeta();
			imsun.setDisplayName("§e§lStatystyki");
			imsun.setLore(Arrays.asList(new String[]{"§6Kliknij aby zobaczyc swoje statystyki"}));
			sun.setItemMeta(imsun);

			
		PlayerInventory inv = p.getInventory();
		inv.setItem(0, compass);
		inv.setItem(1, book);
		inv.setItem(4, potion);
		inv.setItem(5, fire);
		inv.setItem(7, sun);
		inv.setItem(8, star);
	}
	public static void openVipEQ(Player p){
		ItemStack buty = new ItemStack(Material.DIAMOND_BOOTS, 1);
			buty.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			ItemMeta imbuty = buty.getItemMeta();
			imbuty.setDisplayName("§b§lMagiczne Buty");
			imbuty.setLore(Arrays.asList(new String[]{"§6§lKliknij, aby aktywowac magiczne buty!"}));
			buty.setItemMeta(imbuty);
		ItemStack disco = new ItemStack(Material.LEATHER_HELMET, 1);
			LeatherArmorMeta imdisco = (LeatherArmorMeta) disco.getItemMeta();
			imdisco.setDisplayName(ChatColor.BOLD + "§a§lD§5§li§c§ls§6§lc§7§lo §2§lZ§1§lb§4§lr§e§lo§3§lj§8§la");
			imdisco.setLore(Arrays.asList(new String[]{"§6§lKliknij, aby aktywowac DiscoZbroje!"}));
			imdisco.setColor(Color.fromBGR(255, 0, 0));
			disco.setItemMeta(imdisco);
		ItemStack egg = new ItemStack(Material.MONSTER_EGG);
			ItemMeta imegg = egg.getItemMeta();
			imegg.setDisplayName("§b§lZamiana w moba");
			imegg.setLore(Arrays.asList(new String[]{"§6§lKliknij, aby zamienic sie w moba!"}));
			egg.setItemMeta(imegg);
		
			Inventory inv = Bukkit.createInventory(p, 9, "§6§lVIP §2§lMENU");
			inv.setItem(2, buty);
			inv.setItem(4, disco);
			inv.setItem(6, egg);
			p.openInventory(inv);
	}
	public static void openStatsEQ(Player p) {
		@SuppressWarnings("deprecation")
		ItemStack glass = new ItemStack(160, 1, (short) 2);
			ItemMeta imglass = glass.getItemMeta();
			imglass.setDisplayName("");
			glass.setItemMeta(imglass);
		ItemStack book = new ItemStack(Material.BOOK, 1);
			book.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			ItemMeta imbook = book.getItemMeta();
			imbook.setDisplayName("§6Statystyki");
			imbook.setLore(Arrays.asList(new String[]{"§7Komendy do rankingu:", "§b/ranking §7- pokazuje ranking", "§b/ranking <nick> §7- pokazuje ranking gracza", 
					"§b/top §7- pokazuje topke graczy"}));
			book.setItemMeta(imbook);
		ItemStack red = new ItemStack(Material.WOOL, 1, (short) 14);
			ItemMeta imred =  red.getItemMeta();
			imred.setDisplayName("§cPrzegrane gry: §7" + Main.map_ranking.get(p.getName()).getLost());
			red.setItemMeta(imred);
		ItemStack green = new ItemStack(Material.WOOL, 1, (short) 5);
			ItemMeta imgreen = green.getItemMeta();
			imgreen.setDisplayName("§aWygrane gry: §7" + Main.map_ranking.get(p.getName()).getWins());
			green.setItemMeta(imgreen);
		ItemStack kill = new ItemStack(Material.STONE_SWORD);
			ItemMeta imkill = kill.getItemMeta();
			imkill.setDisplayName("§eZabojstwa: §7" + Main.map_ranking.get(p.getName()).getKills());
			kill.setItemMeta(imkill);
		ItemStack death = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			ItemMeta imdeath = death.getItemMeta();
			imdeath.setDisplayName("§cZgony: §7" + Main.map_ranking.get(p.getName()).getDeaths());
			death.setItemMeta(imdeath);
		@SuppressWarnings("deprecation")
		ItemStack coins = new ItemStack(175, 1);
			ItemMeta imcoins = coins.getItemMeta();
			imcoins.setDisplayName("§3GoPoints: §7" + Main.map_points.get(p.getName()));
			coins.setItemMeta(imcoins);
			
		Inventory inv = Bukkit.createInventory(p, 27, "§4§lStatystyki");
		inv.setItem(0, glass);		inv.setItem(8, glass);		inv.setItem(9, glass);
		inv.setItem(17, glass);		inv.setItem(18, glass);		inv.setItem(26, glass);

		inv.setItem(4, book);
		inv.setItem(12, red);
		inv.setItem(14, green);
		inv.setItem(20, kill);
		inv.setItem(22, coins);
		inv.setItem(24, death);
		
		
		p.openInventory(inv);
		
	}
}
