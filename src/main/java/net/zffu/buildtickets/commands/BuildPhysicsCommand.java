package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildPhysicsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(!Permissions.BUILD_PHYSICS.hasPermission(player)) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
            return false;
        }

        boolean b = !BuildTicketsPlugin.getInstance().isBuildPhysics();
        BuildTicketsPlugin.getInstance().setBuildPhysics(b);

        if(b) player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDPHYSICS_ON, player));
        else player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDPHYSICS_OFF, player));

        return true;
    }

}
