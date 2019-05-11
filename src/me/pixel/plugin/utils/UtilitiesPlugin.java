package me.pixel.plugin.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class UtilitiesPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		if (this.getConfig().getBoolean("EnableEnchantCost", false)) new EnchantListener(this);
		if (this.getConfig().getBoolean("EnableOreDropCut", false)) new BlockBreakListener(this);
		if (!this.getConfig().getBoolean("RabbitsEatsDandelion", false)) new AnimalBreedListener(this);
		if (this.getConfig().getBoolean("EffectBlackListEnabled", false)) new EffectListener(this);
		new MobSpawnListener(this);
		getLogger().info("plugin is enabled!");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("plugin is disabled!");
	}
}
