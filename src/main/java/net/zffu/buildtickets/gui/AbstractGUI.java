package net.zffu.buildtickets.gui;

import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.zffu.buildtickets.gui.PaginatedGUI.BACK;

/**
 * A Basic GUI.
 */
public abstract class AbstractGUI {

    protected Gui gui;
    protected AbstractGUI oldGUI;
    protected int backItemSlot = 49;

    public AbstractGUI(String inventoryName) {
        this.gui = Gui.gui().title(Component.text(inventoryName)).rows(6).create();
    }

    public AbstractGUI(String inventoryName, int rows) {
        this.gui = Gui.gui().title(Component.text(inventoryName)).rows(rows).create();
    }


    public abstract void initItems();

    public abstract boolean setDefaultClickActions();

    public abstract void handleMenu(InventoryClickEvent event);

    public void open(HumanEntity player) {
        this.initItems();

        if(this.oldGUI != null) {
            gui.setItem(backItemSlot, new GuiItem(BACK));
            setAction(backItemSlot, (event -> {
                this.oldGUI.open(event.getWhoClicked());
            }));
        }

        this.gui.open(player);

        if(setDefaultClickActions()) {
            this.gui.setDefaultClickAction(this::handleMenu);
        }
        else {
            this.gui.setDefaultClickAction(event -> event.setCancelled(true));
        }
    }

    public void open(HumanEntity player, AbstractGUI gui) {
        oldGUI = gui;
        this.open(player);
    }

    public void setAction(int slot, GuiAction<InventoryClickEvent> action) {
        this.gui.addSlotAction(slot, action);
    }

}
