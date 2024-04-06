package net.zffu.buildtickets.gui;

import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class PaginatedGUI extends AbstractGUI {

    protected int page;
    private int elementsPerPage;
    protected int startingIndex;
    public PaginatedGUI(String inventoryName, int page, int elementsPerPage) {
        super(inventoryName);
        this.page = page;
        this.elementsPerPage = elementsPerPage;
        this.startingIndex = elementsPerPage * page;
    }

    @Override
    public void initItems() {
        for(int i = startingIndex; i < startingIndex + elementsPerPage; i++) {
            this.gui.setItem(i - startingIndex, new GuiItem(getStacks().get(i)));
        }
    }

    public abstract List<ItemStack> getStacks();
}
