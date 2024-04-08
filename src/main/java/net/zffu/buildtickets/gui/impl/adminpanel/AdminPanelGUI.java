package net.zffu.buildtickets.gui.impl.adminpanel;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.AbstractGUI;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.zffu.buildtickets.gui.PaginatedGUI.BACK;

public class AdminPanelGUI extends AbstractGUI {
    public AdminPanelGUI() {
        super("Administrator Ticket Panel");
    }

    @Override
    public void initItems() {
        gui.setItem(20, new GuiItem(ItemBuilder.create(Material.LIME_WOOL).display("§aBuilder Statistics").lore("§7See the stats of builders", "", "§eClick here to open the panel!").build()));
        gui.setItem(22, new GuiItem(ItemBuilder.create(Material.YELLOW_WOOL).display("§aTickets Statistics").lore("§7See the stats of the tickets", "", "§eClick here to open the panel!").build()));
        gui.setItem(24, new GuiItem(ItemBuilder.create(Material.RED_WOOL).display("§aPermission Management").lore("§7Give or remove permissions to your builders", "", "§eClick here to open the panel!").build()));

        gui.setItem(29, new GuiItem(ItemBuilder.create(Material.BOOK).display("§aPanel Explanation").lore("§7This panel is used to see stats of a builder", "§7or the overall stats of the builders", "§7Those stats include: Completed Ticket, Created Tickets", "§7and more").build()));
        gui.setItem(31, new GuiItem(ItemBuilder.create(Material.BOOK).display("§aPanel Explanation").lore("§7This panel is used to see the tickets", "§7that are active, waiting for confirmation", "§7or that require help.").build()));
        gui.setItem(33, new GuiItem(ItemBuilder.create(Material.BOOK).display("§aPanel Explanation").lore("§7This panel is used to give or remove ticket permissions to a builder.").build()));

        setAction(20, (event -> {
            if(!(Permissions.PANEL_PLAYER_STATS.hasPermission(event.getWhoClicked()))) {
                event.getWhoClicked().sendMessage(Messages.NO_PERMISSION.getMessage());
                return;
            }
            new PlayerStatsPanelGUI(0).open(event.getWhoClicked());
        }));

        gui.setItem(49, new GuiItem(BACK));

        setAction(49, (event -> {
            event.getWhoClicked().closeInventory();
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
