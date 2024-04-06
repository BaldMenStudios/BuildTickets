package net.zffu.buildtickets.gui.impl;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Item;
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
    public void initItems() {
        super.initItems();
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§aCreate a note");
        meta.setLore(Arrays.asList("§7Create or edit your note of this", "§7ticket.", "", "§eClick to send / edit your note!"));
        itemStack.setItemMeta(meta);
        gui.setItem(53, new GuiItem(itemStack));

        setAction(53, (event -> {
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().sendMessage("§aPlease enter your note in the chat.");
            BuildTicketsPlugin.getInstance().getChatHandlers().put(event.getWhoClicked().getUniqueId(), (chat) -> {
                chat.setCancelled(true);
                ticket.sendNote(chat.getPlayer(), chat.getMessage());
                this.open(chat.getPlayer());
            });
        }));

    }

    @Override
    public List<ItemStack> getStacks() {
        List<ItemStack> stacks = new ArrayList<>();

        for(UUID posterUUID : ticket.getNoteCreators()) {
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
