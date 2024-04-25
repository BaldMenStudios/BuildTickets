package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.impl.TicketBrowserGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiSubCommand extends SubCommand {
    public GuiSubCommand() {
        super("gui", "", Permissions.OPEN_TICKET_GUI.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        new TicketBrowserGUI(0).open(player);

        return true;
    }
}
