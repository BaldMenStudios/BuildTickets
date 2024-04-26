package net.zffu.buildtickets.gui.impl.adminpanel;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.gui.AbstractGUI;
import net.zffu.buildtickets.gui.impl.TicketBrowserGUI;
import net.zffu.buildtickets.utils.ItemBuilder;
import net.zffu.buildtickets.wrappers.WrappedMaterials;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.zffu.buildtickets.gui.PaginatedGUI.BACK;

public class TicketsStatsPanelGUI extends AbstractGUI {
    public TicketsStatsPanelGUI() {
        super("Ticket Statistics");
    }

    @Override
    public void initItems() {
        gui.setItem(20, new GuiItem(ItemBuilder.create(WrappedMaterials.DYE_LIME).display("§aActive Tickets").lore("§7Tickets that have at least a builder", "§7working on those.", "", "§eClick here to view category!").build()));
        gui.setItem(22, new GuiItem(ItemBuilder.create(WrappedMaterials.DYE_YELLOW).display("§aTickets waiting for confirmation").lore("§7Tickets that need confirmation that they", "§7are completed.", "", "§eClick to view category!").build()));
        gui.setItem(24, new GuiItem(ItemBuilder.create(WrappedMaterials.DYE_RED).display("§aInactive Tickets").lore("§7Tickets that doesn't have any builders", "§7working on those.", "", "§eClick here to view category!").build()));

        setAction(20, (event -> {
            new TicketBrowserGUI(0, TicketBrowserGUI.Category.ACTIVE).open(event.getWhoClicked(), this);
        }));

        setAction(22, (event -> {
            new TicketBrowserGUI(0, TicketBrowserGUI.Category.WAITING).open(event.getWhoClicked(), this);
        }));

        setAction(24, (event -> {
            new TicketBrowserGUI(0, TicketBrowserGUI.Category.INACTIVE).open(event.getWhoClicked(), this);
        }));

    }

    @Override
    public boolean setDefaultClickActions() {
        return false;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }
}
