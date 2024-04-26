package net.zffu.buildtickets.gui.impl.adminpanel;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.gui.PaginatedGUI;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatsPanelGUI extends PaginatedGUI<TicketBuilder> {
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
    public void handleMenu(InventoryClickEvent event) {}

    @Override
    public void initItems() {
        super.initItems();
        gui.setItem(49, new GuiItem(BACK));

        setAction(49, (event -> {
            new AdminPanelGUI().open(event.getWhoClicked());
        }));
    }

    @Override
    public List<TicketBuilder> getElements() {
        return BuildTicketsPlugin.getInstance().getBuilders().values().stream().collect(Collectors.toList());
    }

}
