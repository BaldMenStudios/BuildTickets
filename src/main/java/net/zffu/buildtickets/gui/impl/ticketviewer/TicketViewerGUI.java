package net.zffu.buildtickets.gui.impl.ticketviewer;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.AbstractGUI;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.ItemBuilder;
import net.zffu.buildtickets.wrappers.WrappedMaterials;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class TicketViewerGUI extends AbstractGUI {

    private BuildTicket ticket;

    public TicketViewerGUI(BuildTicket ticket) {
        super("Ticket Viewer");
        this.ticket = ticket;
    }

    @Override
    public void initItems() {
        gui.setItem(13, new GuiItem(ItemBuilder.create(Material.PAPER).display("§aTicket Information").lore("", "§7Creator: §f" + ticket.getCreator(), "§7Priority: §f" + ticket.getPriority().getDisplay(), "§7Claimed by: §f" + (ticket.getBuilders().isEmpty() ? "§cNone" : ticket.getFormattedBuilders())).build()));

        gui.setItem(22, new GuiItem(ItemBuilder.create(WrappedMaterials.DYE_ORANGE).display("§aModify Ticket Priority").lore("§7Sets the priority of the ticket", "", "§7Current Priority: §f" + this.ticket.getPriority().getDisplay(), "", "§eClick to change the priority!").build()));
        gui.setItem(21, new GuiItem(ItemBuilder.create(WrappedMaterials.LIME_WOOL).display("§aJoin the ticket").lore("§7Adds you to the ticket builders.", "", "§eClick here to join the ticket!").build()));
        gui.setItem(23, new GuiItem(ItemBuilder.create(WrappedMaterials.LIME_WOOL).display("§aRequest Help").lore("§7Request help from other builders to help you with this ticket.", "", "§eClick here to request help!").build()));

        gui.setItem(31, new GuiItem(ItemBuilder.create(WrappedMaterials.COMPARATOR).display("§aModify the ticket reason").lore("§7Update the ticket's reason.", "", "§eClick here to change the ticket reason!").build()));
        gui.setItem(30, new GuiItem(ItemBuilder.create(Material.DIAMOND).display("§aComplete Ticket").lore("§7Marks the ticket as completed.", "", "§cFaking completing a ticket will", "§cmostly result as a punishement", "§cfrom your staff team", "", "§eClick here to complete this ticket!").build()));
        gui.setItem(32, new GuiItem(ItemBuilder.create(WrappedMaterials.DYE_RED).display("§aLeave Ticket").lore("§7Leave the ticket to go to another one.", "", "§eClick here to leave this ticket").build()));

        setAction(21, (event -> {
            ticket.joinTicket(event.getWhoClicked());
        }));

        setAction(22, (event -> {
            if(!Permissions.CHANGE_TICKET_PRIORITY.hasPermission(event.getWhoClicked(), ticket)) {
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, event.getWhoClicked()));
                return;
            }
            new TicketPriorityGUI(ticket).open(event.getWhoClicked(), this);
        }));

        setAction(23, (event -> {
            if(!Permissions.REQUEST_HELP.hasPermission(event.getWhoClicked(), ticket)) {
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, event.getWhoClicked()));
                return;
            }
            boolean b = !ticket.isNeedsHelp();
            ticket.setNeedsHelp(b);

            if(b) event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_HELP_ON, event.getWhoClicked()));
            else event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_HELP_OFF, event.getWhoClicked()));
        }));

        setAction(31, (event -> {
            if(!Permissions.TICKET_CHANGE_REASON.hasPermission(event.getWhoClicked(), ticket)) {
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, event.getWhoClicked()));
                return;
            }
            BuildTicketsPlugin.getInstance().doChatHandler(event.getWhoClicked(), (chat) -> {
                chat.setCancelled(true);
                ticket.setTicketReason(chat.getMessage());
                this.open(chat.getPlayer());
            });
        }));

        setAction(32, (event -> {
            if(!ticket.getBuilders().contains(event.getWhoClicked().getUniqueId())) {
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_BUILDER_NOT, event.getWhoClicked()));
                return;
            }

            ticket.getBuilders().remove(event.getWhoClicked().getUniqueId());
            event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_QUIT, event.getWhoClicked()));
        }));


        setAction(30, (event -> {
            if(!(Permissions.TICKET_COMPLETE.hasPermission(event.getWhoClicked()))) {
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, event.getWhoClicked()));
                return;
            }
            if(!ticket.isWaitingForCompletionConfirmation()) {
                ticket.setWaitingForCompletionConfirmation(true);
                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_COMPLETION_WAITING, event.getWhoClicked()));
            }
            else {
                if(!Permissions.TICKET_COMPLETE_CONFIRM.hasPermission(event.getWhoClicked())) {
                    event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, event.getWhoClicked()));
                    return;
                }
                ticket.setCompleted(true);
                ticket.setWaitingForCompletionConfirmation(false);

                for(UUID builder : ticket.getBuilders()) {
                    BuildTicketsPlugin.getInstance().getOrCreateBuilder(builder).completeTicket();
                }

                event.getWhoClicked().sendMessage(LocaleManager.getMessage(LocaleString.TICKET_COMPLETION_CONFIRMED, event.getWhoClicked()));
            }
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
