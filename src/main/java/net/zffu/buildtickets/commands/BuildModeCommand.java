package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildModeCommand implements CommandExecutor {

    private String togglePermission;

    public BuildModeCommand(String togglePermission) {
        this.togglePermission = togglePermission;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(!player.hasPermission(togglePermission)) {
            player.sendMessage(Messages.NO_PERMISSION);
            return false;
        }

        boolean b = !BuildTicketsPlugin.getInstance().getBuildMode().remove(player.getUniqueId());
        if(!b) BuildTicketsPlugin.getInstance().getBuildMode().add(player.getUniqueId());

        player.sendMessage((b) ? Messages.ENABLED_BUILD_MODE : Messages.DISABLED_BUILD_MODE);

        return true;
    }
}
