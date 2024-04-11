package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(!Permissions.BUILD_MODE_TOGGLE.hasPermission(player)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
            return false;
        }

        boolean b = BuildTicketsPlugin.getInstance().getBuildMode().remove(player.getUniqueId());

        if(!b) {
            BuildTicketsPlugin.getInstance().getBuildMode().add(player.getUniqueId());
            player.sendMessage(Messages.BUILD_MODE_ENABLED.getMessage());
        }
        else {
            player.sendMessage(Messages.BUILD_MODE_DISABLED.getMessage());
        }


        return true;
    }
}
