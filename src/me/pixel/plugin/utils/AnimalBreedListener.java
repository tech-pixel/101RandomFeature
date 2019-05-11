package me.pixel.plugin.utils;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class AnimalBreedListener implements Listener{
	
	public AnimalBreedListener (UtilitiesPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBreedEvent (EntityBreedEvent event) {
		if (event.getMother().getType() == EntityType.RABBIT && event.getBredWith().getType() == Material.DANDELION) {
			event.setCancelled(true);
			return;
		}
	}
}
