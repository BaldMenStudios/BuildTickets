package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.tickets.TicketPriority;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateSubCommand extends SubCommand {
    public CreateSubCommand() {
        super("create", "Creates a Ticket.", Permissions.CREATE_TICKET.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length < 2) {
            sender.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }
        String reason = args[1];

        BuildTicket buildTicket = new BuildTicket(reason, (args.length >= 3 ? TicketPriority.getValue(args[2]) : TicketPriority.NORMAL), player.getUniqueId());
        BuildTicketsPlugin.getInstance().getTickets().add(buildTicket);
        BuildTicketsPlugin.getInstance().getOrCreateBuilder(player.getUniqueId()).createTicket();
        player.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_CREATED, player));
        return true;
    }
}
