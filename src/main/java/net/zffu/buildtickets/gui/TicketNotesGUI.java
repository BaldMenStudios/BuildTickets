package net.zffu.buildtickets.gui;

import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class TicketNotesGUI {

    private BuildTicket ticket;
    private int page = 0;
    private Inventory inventory;

    public TicketNotesGUI(BuildTicket ticket) {
        this.ticket = ticket;
    }

    public TicketNotesGUI(BuildTicket ticket, int page) {
        this.ticket = ticket;
        this.page = page;
    }

    public void initItems() {
        int startingIndex = page * 35;
        if(ticket.getNoteCreators().size() <= startingIndex) return;

        for(int i = startingIndex; i < startingIndex + 35; i++) {
            if(ticket.getNoteCreators().size() <= i) return;
            UUID posterUUID = ticket.getNoteCreators().get(i);
            OfflinePlayer poster = Bukkit.getOfflinePlayer(posterUUID);
            String note = ticket.getNotes().get(posterUUID);

            ItemStack stack = HeadUtils.getHeadStack(posterUUID);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a" + poster.getPlayer().getName() + "'s Note");
            meta.setLore(Arrays.asList(note));

            stack.setItemMeta(meta);

            inventory.setItem(i, stack);
        }
    }

    public void open(Player player) {
        this.initItems();
        player.openInventory(this.inventory);
    }


}
