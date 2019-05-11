package me.pixel.plugin.utils;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {
	UtilitiesPlugin inst;
	public BlockBreakListener (UtilitiesPlugin plugin) {
		inst = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("incomplete-switch")
	@EventHandler
	public void onBlockBreak (BlockBreakEvent event) {
		if ((event.getBlock().getType() == Material.REDSTONE_ORE || event.getBlock().getType() == Material.LAPIS_ORE) && event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
			int drop_count = 1;
			Random random = new Random();
			if (tool.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
				inst.getLogger().info("Ench level is "+tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
				if (33 >= random.nextInt(100)) {
					switch (tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) {
					case 1:
						drop_count+= random.nextInt(1);
						break;
					case 2:
						drop_count+= random.nextInt(2);
						break;
					case 3:
						drop_count+= random.nextInt(3);
						break;
					}
				}
				
			}
			switch ( event.getBlock().getType()) {
			case REDSTONE_ORE:
				tool = new ItemStack(Material.REDSTONE);
				break;
			case LAPIS_ORE:
				tool = new ItemStack(Material.LAPIS_LAZULI);
				break;
			}
			tool.setAmount(drop_count);
			event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), tool);
			for (int i = 0, r = random.nextInt(5); i < r; i++) {
				event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.EXPERIENCE_ORB);
			}
			event.getBlock().setType(Material.AIR);
			event.setCancelled(true);
		} else return;
	}

}
