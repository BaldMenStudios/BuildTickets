package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
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
            player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
            return false;
        }

        boolean b = BuildTicketsPlugin.getInstance().getBuildMode().remove(player.getUniqueId());

        if(!b) {
            BuildTicketsPlugin.getInstance().getBuildMode().add(player.getUniqueId());
            player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDMODE_ON, player));
        }
        else {
            player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDMODE_OFF, player));
        }


        return true;
    }
}
