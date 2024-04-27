package net.zffu.buildtickets.wrapper;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

/**
 * Wrapper made for adding compatibility for older minecraft versions.
 */
public interface ICompatibilityWrapper {

    /**
     * Gets a skull that has the owning player.
     * @param player
     * @return
     */
    ItemStack getSkullWithOwningPlayer(OfflinePlayer player);

    /**
     * Gets the dyed variant of an item.
     * @param dye
     * @param mode
     * @return
     */
    ItemStack getDyed(CompatibilityDye dye, CompatibilityDye.Mode mode);
}
