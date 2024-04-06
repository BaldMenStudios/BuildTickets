package net.zffu.buildtickets.gui;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BuildTicketsGUI {

    private Inventory inventory;
    private int page;


    public BuildTicketsGUI() {
        this.inventory = Bukkit.createInventory(null, 54, "Build Tickets (Page 1)");
    }

    public BuildTicketsGUI(int page) {
        this.inventory = Bukkit.createInventory(null, 54, "Build Tickets (Page " + (page + 1) + ")");
        this.page = page;
    }

    public void initItems() {
        if(!BuildTicketsPlugin.getInstance().getTickets().isEmpty()) {
            int startingIndex = page * 35;

            for(int i = startingIndex; i < startingIndex + 35; i++) {
                if(BuildTicketsPlugin.getInstance().getTickets().size() < startingIndex) return;
                BuildTicket ticket = BuildTicketsPlugin.getInstance().getTickets().get(i);
                if(ticket == null) return;

                Material material = Material.GREEN_DYE;

                if(ticket.getClaimer() == null) {
                    material = Material.RED_DYE;
                }

                ItemStack stack = new ItemStack(material);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName("§a" + ticket.getTicketReason());
                meta.setLore(Arrays.asList("", "§7Creator: §f" + ticket.getCreator(), "§7Priority: §f" + ticket.getPriority().getDisplay(), "Claimed by: " + (ticket.getClaimer() == null ? "§cNone" : ticket.getClaimer()), "", "§eClick to claim the ticket!"));
                stack.setItemMeta(meta);

                inventory.setItem(i - startingIndex, stack);
            }
        }
    }

    public void open(Player player) {
        this.initItems();
        player.openInventory(this.inventory);
    }

}
