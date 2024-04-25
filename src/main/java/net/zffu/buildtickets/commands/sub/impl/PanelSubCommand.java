package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.impl.adminpanel.AdminPanelGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PanelSubCommand extends SubCommand {
    public PanelSubCommand() {
        super("panel", "Opens the ticket panel.", Permissions.OPEN_TICKET_PANEL.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        new AdminPanelGUI().open((Player)sender);
        return false;
    }
}
