package net.zffu.buildtickets.gui.impl;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.gui.AbstractGUI;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.HeadUtils;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TicketGUI extends AbstractGUI {

    private BuildTicket ticket;

    public TicketGUI(BuildTicket ticket) {
        super("Ticket Viewer");
        this.ticket = ticket;
    }

    @Override
    public void initItems() {
        gui.setItem(13, new GuiItem(ItemBuilder.create(Material.PAPER).display("§aTicket Information").lore("§7Ticket Reason: §f" + ticket.getTicketReason(), "§7Ticket Priority: §f" + this.ticket.getPriority(), "§7Ticket Creator: §f" + ticket.getCreator()).build()));

        gui.setItem(22, new GuiItem(ItemBuilder.create(Material.ORANGE_DYE).display("§aModify Ticket Priority").lore("§7Sets the priority of the ticket", "", "§7Current Priority: §f" + this.ticket.getPriority().getDisplay(), "", "§eClick to change the priority!").build()));
        gui.setItem(21, new GuiItem(ItemBuilder.create(Material.LIME_WOOL).display("§aJoin the ticket").lore("§7Adds you to the ticket builders.", "", "§eClick here to join the ticket!").build()));
        gui.setItem(23, new GuiItem(ItemBuilder.create(Material.LIME_DYE).display("§aRequest Help").lore("§7Request help from other builders to help you with this ticket.", "", "§eClick here to request help!").build()));

        gui.setItem(31, new GuiItem(ItemBuilder.create(Material.COMPARATOR).display("§aModify the ticket reason").lore("§7Update the ticket's reason.", "", "§eClick here to change the ticket reason!").build()));
        gui.setItem(30, new GuiItem(ItemBuilder.create(Material.DIAMOND).display("§aComplete Ticket").lore("§7Marks the ticket as completed.", "", "§cFaking completing a ticket will", "§cmostly result as a punishement", "§cfrom your staff team", "", "§eClick here to complete this ticket!").build()));
        gui.setItem(32, new GuiItem(ItemBuilder.create(Material.RED_DYE).display("§aLeave Ticket").lore("§7Leave the ticket to go to another one.", "", "§eClick here to leave this ticket").build()));
    }

    @Override
    public boolean setDefaultClickActions() {
        return false;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }
}