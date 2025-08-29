package com.vedantgangal.stringduper.listeners;

import com.vedantgangal.stringduper.StringDuper;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CobwebListener implements Listener {

    private final StringDuper plugin;

    public CobwebListener(StringDuper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (event.getBlock().getType() != Material.COBWEB) return;

        if (!plugin.getConfig().getBoolean("enabled", true)) return;

        Player player = event.getPlayer();
        if (player == null) return;

        if (player.getGameMode() == GameMode.CREATIVE) return;

        boolean requireShears = plugin.getConfig().getBoolean("require-shears", false);
        boolean silkOnly = plugin.getConfig().getBoolean("silk-touch-only", false);
        int amount = Math.max(0, plugin.getConfig().getInt("drop-amount", 2));

        ItemStack tool = player.getInventory().getItem(EquipmentSlot.HAND);
        boolean hasShears = tool != null && tool.getType() == Material.SHEARS;

        boolean hasSilk = false;
        if (tool != null) {
            ItemMeta meta = tool.getItemMeta();
            if (meta != null && meta.hasEnchant(Enchantment.SILK_TOUCH)) {
                hasSilk = true;
            }
        }

        if (requireShears && !hasShears) return;
        if (silkOnly && !hasSilk) return;

        event.setDropItems(false);
        if (amount > 0) {
            event.getBlock().getWorld().dropItemNaturally(
                event.getBlock().getLocation(),
                new ItemStack(Material.STRING, amount)
            );
        }
    }
}
