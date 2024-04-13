package net.zffu.buildtickets.commands;

import jdk.vm.ci.meta.Local;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.impl.TicketBrowserGUI;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.tickets.TicketPriority;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicketCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandName, String[] args) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;


        if(args.length == 0) {
            if(!Permissions.OPEN_TICKET_GUI.hasPermission(player)) {
                player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
                return false;
            }
            new TicketBrowserGUI(0).open(player);
            return true;
        }

        String sub = args[0];

        if(sub.equals("create")) {
            if(args.length < 2) {
                player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
                return false;
            }

            if(!Permissions.CREATE_TICKET.hasPermission(player)) {
                player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
                return false;
            }

            String reason = args[1];

            BuildTicket buildTicket = new BuildTicket(reason, (args.length >= 3 ? TicketPriority.getValue(args[2]) : TicketPriority.NORMAL), player.getUniqueId());
            BuildTicketsPlugin.getInstance().getTickets().add(buildTicket);
            BuildTicketsPlugin.getInstance().getOrCreateBuilder(player.getUniqueId()).createTicket();
            player.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_CREATED, player));
        }
        else {
            player.sendMessage("§7------ §a§lBuild Tickets §r§7------");
            player.sendMessage("§a/ticket create <reason> [priority]: §r§fCreates a Ticket");
            player.sendMessage("§a/ticket help: §r§fShows this help message");
        }

        return true;
    }


}
