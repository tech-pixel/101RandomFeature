package me.pixel.plugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantListener implements Listener {
	public EnchantListener (UtilitiesPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
		
	@EventHandler
	public void onEnchantItemEvent (EnchantItemEvent event) {
		Player player = event.getEnchanter();
		int cost = event.getExpLevelCost();
		int butt = event.whichButton()+1;
		int playerlvl = player.getLevel();
		
		player.sendMessage("You enchanted for " + cost + " lvl cost wich is " + butt + " button and your level is " + playerlvl+" levels.");
		int newlvl = playerlvl-(cost-butt);
		event.setExpLevelCost(0);
		player.setLevel(newlvl);
		player.sendMessage("Your level set to "+ newlvl);
		return;
	}
}
