package net.zffu.buildtickets.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class PaginatedGUI extends AbstractGUI {

    private int page;
    private int elementsPerPage;
    private int startingIndex;
    public PaginatedGUI(String inventoryName, int page, int elementsPerPage) {
        super(inventoryName);
        this.page = page;
        this.elementsPerPage = elementsPerPage;
        this.startingIndex = elementsPerPage * page;
    }

    public abstract List<ItemStack> getStacks();
}
