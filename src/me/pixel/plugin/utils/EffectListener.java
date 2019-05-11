package me.pixel.plugin.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent.Action;


public class EffectListener implements Listener {
	
	UtilitiesPlugin inst;
	public EffectListener (UtilitiesPlugin plugin) {
		inst = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEffectApply (EntityPotionEffectEvent event) {
		if (event.getEntityType() == EntityType.PLAYER && event.getAction() == Action.ADDED) {
			if (inst.getConfig().getStringList("EffectBlackList").contains(event.getNewEffect().getType().getName())) {
				event.setCancelled(true);
			}
		}
	}
}