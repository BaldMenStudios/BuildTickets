package net.zffu.buildtickets.gui.impl;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.gui.impl.ticketviewer.TicketViewerGUI;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.tickets.TicketPriority;
import net.zffu.buildtickets.utils.Bundle;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketBrowserGUI extends PaginatedGUI<BuildTicket> {

    private Category category;

    public TicketBrowserGUI(int page) {
        super("Build Tickets (Page " + (page + 1) + ")", page, 35);
        this.page = page;
        this.category = Category.ALL;
        this.initChoices();
    }

    public TicketBrowserGUI(int page, Category category) {
        super("Build Tickets (Page " + (page + 1) + ")", page, 35);
        this.page = page;
        this.category = category;
        this.initChoices();
    }

    public void initChoices() {
        this.sortingOptions = new Bundle[] {new Bundle("Newest Created", (Comparator<BuildTicket>) (o1, o2) -> o2.getCreated().compareTo(o1.getCreated())), new Bundle("Last Created", Comparator.comparing(BuildTicket::getCreated))};
    }

    @Override
    public void initItems() {
        super.initItems();
        gui.setItem(48, new GuiItem(GO_BACK));
        gui.setItem(50, new GuiItem(GO_NEXT));

        gui.setItem(53, new GuiItem(ItemBuilder.create(Material.PAPER).display("§aCreate Ticket").lore("§7Create a building ticket here.", "", "§eClick here to create a ticket!").build()));

        setAction(48, (event -> {
            goBack(event.getWhoClicked());
        }));

        setAction(50, (event -> {
            goNext(event.getWhoClicked());
        }));


    }

    @Override
    public List<BuildTicket> getElements() {
        switch (category) {
            case ALL:
                return BuildTicketsPlugin.getInstance().getTickets();
            case ACTIVE:
                return BuildTicketsPlugin.getInstance().getTickets().stream().filter(ticket -> !ticket.getBuilders().isEmpty()).collect(Collectors.toList());
            case INACTIVE:
                return BuildTicketsPlugin.getInstance().getTickets().stream().filter(ticket -> ticket.getBuilders().isEmpty()).collect(Collectors.toList());
            case WAITING:
                return BuildTicketsPlugin.getInstance().getTickets().stream().filter(ticket -> ticket.isWaitingForCompletionConfirmation()).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean setDefaultClickActions() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        event.setCancelled(true);

        if(event.getSlot() <= 35) {
            BuildTicket ticket = getElements().get(startingIndex + event.getSlot());
            if(event.getClick().isLeftClick()) {
                new TicketNotesGUI(ticket, 0).open(event.getWhoClicked(), this);
            }
            if(event.getClick().isRightClick()) {
                new TicketViewerGUI(ticket).open(event.getWhoClicked(), this);
            }
        }

        if(event.getSlot() == 53) {
            BuildTicketsPlugin.getInstance().doChatHandler(event.getWhoClicked(), (e -> {
                BuildTicket buildTicket = new BuildTicket(event.getEventName(), TicketPriority.NORMAL, event.getWhoClicked().getUniqueId());
                BuildTicketsPlugin.getInstance().registerTicket(buildTicket);
                BuildTicketsPlugin.getInstance().getOrCreateBuilder(event.getWhoClicked().getUniqueId()).createTicket();
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_CREATED, event.getWhoClicked()));
            }));
        }
    }

    public enum Category {
        ALL,
        ACTIVE,
        INACTIVE,
        WAITING
    }

}
