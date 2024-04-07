package net.zffu.buildtickets.gui.impl.ticketviewer;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.gui.AbstractGUI;
import net.zffu.buildtickets.gui.impl.BuildTicketsGUI;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.zffu.buildtickets.gui.PaginatedGUI.BACK;

public class TicketViewerGUI extends AbstractGUI {

    private BuildTicket ticket;

    public TicketViewerGUI(BuildTicket ticket) {
        super("Ticket Viewer");
        this.ticket = ticket;
    }

    @Override
    public void initItems() {
        gui.setItem(13, new GuiItem(ItemBuilder.create(Material.PAPER).display("§aTicket Information").lore("", "§7Creator: §f" + ticket.getCreator(), "§7Priority: §f" + ticket.getPriority().getDisplay(), "§7Claimed by: §f" + (ticket.getBuilders().isEmpty() ? "§cNone" : ticket.getFormattedBuilders())).build()));

        gui.setItem(22, new GuiItem(ItemBuilder.create(Material.ORANGE_DYE).display("§aModify Ticket Priority").lore("§7Sets the priority of the ticket", "", "§7Current Priority: §f" + this.ticket.getPriority().getDisplay(), "", "§eClick to change the priority!").build()));
        gui.setItem(21, new GuiItem(ItemBuilder.create(Material.LIME_WOOL).display("§aJoin the ticket").lore("§7Adds you to the ticket builders.", "", "§eClick here to join the ticket!").build()));
        gui.setItem(23, new GuiItem(ItemBuilder.create(Material.LIME_DYE).display("§aRequest Help").lore("§7Request help from other builders to help you with this ticket.", "", "§eClick here to request help!").build()));

        gui.setItem(31, new GuiItem(ItemBuilder.create(Material.COMPARATOR).display("§aModify the ticket reason").lore("§7Update the ticket's reason.", "", "§eClick here to change the ticket reason!").build()));
        gui.setItem(30, new GuiItem(ItemBuilder.create(Material.DIAMOND).display("§aComplete Ticket").lore("§7Marks the ticket as completed.", "", "§cFaking completing a ticket will", "§cmostly result as a punishement", "§cfrom your staff team", "", "§eClick here to complete this ticket!").build()));
        gui.setItem(32, new GuiItem(ItemBuilder.create(Material.RED_DYE).display("§aLeave Ticket").lore("§7Leave the ticket to go to another one.", "", "§eClick here to leave this ticket").build()));


        gui.setItem(49, new GuiItem(BACK));

        setAction(49, (event -> {
            new BuildTicketsGUI(0).open(event.getWhoClicked());
        }));

        setAction(21, (event -> {
            if(ticket.getBuilders().contains(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage(Messages.TICKET_ALREADY_JOINED.getMessage());
                return;
            }
            if(!ticket.isNeedsHelp() && !ticket.getBuilders().isEmpty()) {
                event.getWhoClicked().sendMessage(Messages.TICKET_NO_NEED_HELP.getMessage());
                return;
            }
            ticket.getBuilders().add(event.getWhoClicked().getUniqueId());
            event.getWhoClicked().sendMessage(Messages.TICKET_JOINED.getMessage());
        }));

        setAction(22, (event -> {
            new TicketPriorityGUI(ticket).open(event.getWhoClicked());
        }));

        setAction(23, (event -> {
            boolean b = !ticket.isNeedsHelp();
            ticket.setNeedsHelp(b);

            if(b) event.getWhoClicked().sendMessage(Messages.TICKET_REQUEST_HELP_ON.getMessage());
            else event.getWhoClicked().sendMessage(Messages.TICKET_REQUEST_HELP_OFF.getMessage());
        }));

        setAction(31, (event -> {
            BuildTicketsPlugin.getInstance().doChatHandler(event.getWhoClicked(), (chat) -> {
                chat.setCancelled(true);
                ticket.setTicketReason(chat.getMessage());
                this.open(chat.getPlayer());
            });
        }));

        setAction(32, (event -> {
            if(!ticket.getBuilders().contains(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage(Messages.TICKET_NOTE_EDIT.getMessage());
                return;
            }

            ticket.getBuilders().remove(event.getWhoClicked().getUniqueId());
            event.getWhoClicked().sendMessage(Messages.TICKET_LEFT.getMessage());
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
