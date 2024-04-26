package net.zffu.buildtickets.gui;

import org.bukkit.inventory.ItemStack;

/**
 * A class that can be visualized as an ItemStack.
 */
public interface ItemConvertible {

    /**
     * Converts the class into an ItemStack.
     * @return
     */
    ItemStack toItemStack();

}
