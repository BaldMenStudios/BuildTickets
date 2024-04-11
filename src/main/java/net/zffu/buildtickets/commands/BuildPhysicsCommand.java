package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BuildPhysicsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if(!Permissions.BUILD_PHYSICS.hasPermission(player)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
            return false;
        }

        boolean b = !BuildTicketsPlugin.getInstance().isBuildPhysics();
        BuildTicketsPlugin.getInstance().setBuildPhysics(b);

        if(b) player.sendMessage(Messages.BUILD_PHYSICS_ENABLED.getMessage());
        else player.sendMessage(Messages.BUILD_PHYSICS_DISABLED.getMessage());

        return true;
    }

}
