package net.zffu.buildtickets.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * A Basic GUI.
 */
public abstract class AbstractGUI {

    private Inventory inventory;

    public AbstractGUI(String inventoryName) {
        this.inventory = Bukkit.createInventory(null, 54, inventoryName);
    }

    public abstract void initItems();

    public void open(Player player) {
        this.initItems();
        player.openInventory(this.inventory);
    }

}
