package net.zffu.buildtickets.gui.impl.adminpanel;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.utils.HeadUtils;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerStatsPanelGUI extends PaginatedGUI {
    public PlayerStatsPanelGUI(int page) {
        super("Player Stats Panel", page, 44);
        this.startingSlotIndex = 10;
        this.elementsPerLine = 7;
    }

    @Override
    public boolean setDefaultClickActions() {
        return false;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public List<ItemStack> getStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        for(TicketBuilder builder : BuildTicketsPlugin.getInstance().getBuilders().values()) {
            ItemStack stack = HeadUtils.getHeadStack(builder.getUuid());
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§a" + Bukkit.getOfflinePlayer(builder.getUuid()).getPlayer().getName() + "'s Statistics");
            meta.setLore(Arrays.asList(
                    "§7Tickets Created: §f" + builder.getTicketsCreated(),
                    "§7Tickets Completed: §f" + builder.getTicketsCompleted()
            ));
            stack.setItemMeta(meta);
            stacks.add(stack);
        }
        return stacks;
    }
}
