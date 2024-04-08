package net.zffu.buildtickets.gui.impl.adminpanel;

import net.zffu.buildtickets.gui.AbstractGUI;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TicketsStatsPanelGUI extends AbstractGUI {
    public TicketsStatsPanelGUI() {
        super("Ticket Statistics");
    }

    @Override
    public void initItems() {

    }

    @Override
    public boolean setDefaultClickActions() {
        return false;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }
}
