package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.impl.adminpanel.AdminPanelGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TicketPanelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if(!Permissions.OPEN_TICKET_PANEL.hasPermission(player)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
            return false;
        }

        new AdminPanelGUI().open(player);
        return true;
    }
}
