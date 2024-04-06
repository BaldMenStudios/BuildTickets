package net.zffu.buildtickets.gui;

import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A Basic GUI.
 */
public abstract class AbstractGUI {

    protected Gui gui;

    public AbstractGUI(String inventoryName) {
        this.gui = Gui.gui().title(Component.text(inventoryName)).rows(9).create();
    }

    public abstract void initItems();

    public abstract boolean setDefaultClickActions();

    public abstract void handleMenu(InventoryClickEvent event);

    public void open(Player player) {
        this.initItems();
        this.gui.open(player);

        if(setDefaultClickActions()) {
            this.gui.setDefaultClickAction(this::handleMenu);
        }
        else {
            this.gui.setDefaultClickAction(event -> event.setCancelled(true));
        }
    }

    public void setAction(int slot, GuiAction<InventoryClickEvent> action) {
        this.gui.addSlotAction(slot, action);
    }

}