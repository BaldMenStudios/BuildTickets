package net.zffu.buildtickets.gui.impl;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.gui.impl.ticketviewer.TicketViewerGUI;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketBrowserGUI extends PaginatedGUI {

    private Category category;

    public TicketBrowserGUI(int page) {
        super("Build Tickets (Page " + (page + 1) + ")", page, 35);
        this.page = page;
        this.category = Category.ALL;
    }

    public TicketBrowserGUI(int page, Category category) {
        super("Build Tickets (Page " + (page + 1) + ")", page, 35);
        this.page = page;
        this.category = category;
    }

    @Override
    public void initItems() {
        super.initItems();
        gui.setItem(48, new GuiItem(GO_BACK));
        gui.setItem(50, new GuiItem(GO_NEXT));

        setAction(48, (event -> {
            goBack(event.getWhoClicked());
        }));

        setAction(50, (event -> {
            goNext(event.getWhoClicked());
        }));
    }

    @Override
    public boolean setDefaultClickActions() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        event.setCancelled(true);

        if(event.getSlot() <= 35) {
            BuildTicket ticket = BuildTicketsPlugin.getInstance().getTickets().get(startingIndex + event.getSlot());
            if(event.getClick().isLeftClick()) {
                new TicketNotesGUI(ticket, 0).open(event.getWhoClicked(), this);
            }
            if(event.getClick().isRightClick()) {
                new TicketViewerGUI(ticket).open(event.getWhoClicked(), this);
            }

        }

    }

    @Override
    public List<ItemStack> getStacks() {
        List<ItemStack> stacks = new ArrayList<>();

        for(BuildTicket ticket : BuildTicketsPlugin.getInstance().getTickets()) {

            switch (category) {
                case ACTIVE:
                    if(ticket.getBuilders().isEmpty()) continue;
                    break;
                case INACTIVE:
                    if(!ticket.getBuilders().isEmpty()) continue;
                    break;
                case WAITING:
                    if(!ticket.isWaitingForCompletionConfirmation()) continue;
                    break;
            }

            Material material = Material.GREEN_DYE;

            if(ticket.getBuilders().isEmpty()) {
                material = Material.RED_DYE;
            }

            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a" + ticket.getTicketReason());
            meta.setLore(Arrays.asList("", "§7Creator: §f" + ticket.getCreator(), "§7Priority: §f" + ticket.getPriority().getDisplay(), "§7Claimed by: §f" + (ticket.getBuilders().isEmpty() ? "§cNone" : ticket.getFormattedBuilders()), "", "§eRight-Click to view the ticket!", "§eLeft-Click to add a note"));
            stack.setItemMeta(meta);

            stacks.add(stack);
        }
        return stacks;
    }

    public enum Category {
        ALL,
        ACTIVE,
        INACTIVE,
        WAITING
    }

}
