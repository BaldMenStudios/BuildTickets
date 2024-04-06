package net.zffu.buildtickets.gui.impl;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildTicketsGUI extends PaginatedGUI {
    private int page;


    public BuildTicketsGUI(int page) {
        super("Build Tickets (Page " + (page + 1) + ")", page, 35);
        this.page = page;
    }

    @Override
    public boolean setDefaultClickActions() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        event.setCancelled(true);

        if(event.getSlot() <= 35) {
            if(event.getClick().isLeftClick()) {
                new TicketNotesGUI(BuildTicketsPlugin.getInstance().getTickets().get(startingIndex + event.getSlot()), 0).open(event.getWhoClicked());
            }

        }

    }

    @Override
    public List<ItemStack> getStacks() {
        List<ItemStack> stacks = new ArrayList<>();

        for(BuildTicket ticket : BuildTicketsPlugin.getInstance().getTickets()) {
            Material material = Material.GREEN_DYE;

            if(ticket.getClaimer() == null) {
                material = Material.RED_DYE;
            }

            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a" + ticket.getTicketReason());
            meta.setLore(Arrays.asList("", "§7Creator: §f" + ticket.getCreator(), "§7Priority: §f" + ticket.getPriority().getDisplay(), "§7Claimed by: " + (ticket.getClaimer() == null ? "§cNone" : ticket.getClaimer()), "", "§eRight-Click to claim the ticket!", "§eLeft-Click to add a note"));
            stack.setItemMeta(meta);

            stacks.add(stack);
        }
        return stacks;
    }
}
