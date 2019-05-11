package me.pixel.plugin.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobSpawnListener implements Listener {
	
	UtilitiesPlugin inst;
	public MobSpawnListener (UtilitiesPlugin plugin) {
		inst = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onMobSpawn (CreatureSpawnEvent event) {
		if (event.getSpawnReason() == SpawnReason.NATURAL) {
			if (inst.getConfig().contains("MobSpawn."+event.getEntityType().name())) {
				String biome = new String(event.getLocation().getBlock().getBiome().name());
				if (inst.getConfig().getStringList("MobSpawn."+event.getEntityType().name()).contains(biome)) {
					return;
				}
				else {
					event.setCancelled(true);
				}
				
			}
		}
	}
}
