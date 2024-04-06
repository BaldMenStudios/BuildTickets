package net.zffu.buildtickets.gui.impl;

import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TicketNotesGUI extends PaginatedGUI {
    private BuildTicket ticket;

    public TicketNotesGUI(BuildTicket ticket, int page) {
        super("Notes (Page " + page + ")", page, 35);
        this.ticket = ticket;
        this.page = page;
    }

    @Override
    public List<ItemStack> getStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        if(ticket.getNoteCreators().size() <= startingIndex) return stacks;

        for(int i = startingIndex; i < startingIndex + 35; i++) {
            if(ticket.getNoteCreators().size() <= i) return stacks;
            UUID posterUUID = ticket.getNoteCreators().get(i);
            OfflinePlayer poster = Bukkit.getOfflinePlayer(posterUUID);
            String note = ticket.getNotes().get(posterUUID);

            ItemStack stack = HeadUtils.getHeadStack(posterUUID);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a" + poster.getPlayer().getName() + "'s Note");
            meta.setLore(Arrays.asList(note));

            stack.setItemMeta(meta);
            stacks.add(stack);
        }
        return stacks;
    }

    @Override
    public boolean setDefaultClickActions() {
        return false;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {}
}
