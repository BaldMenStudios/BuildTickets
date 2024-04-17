package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildTicketsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou cannot run this command as a console!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            sender.sendMessage("§6Running §eBuildTickets v" + BuildTicketsPlugin.getInstance().getDescription().getVersion());
            sender.sendMessage((Permissions.HELP_MESSAGE.hasPermission(player) ? "§6Use §e/buildtickets help §6to see the available commands!" : "§6You do not have permission to use this command!"));
        }
        return true;
    }
}
