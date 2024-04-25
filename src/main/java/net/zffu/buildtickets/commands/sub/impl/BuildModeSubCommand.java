package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildModeSubCommand extends SubCommand {
    public BuildModeSubCommand() {
        super("buildmode", "Toggles the build mode.", Permissions.BUILD_MODE_TOGGLE.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!BuildTicketsPlugin.getInstance().isBuildModeEnabled()) return false;

        Player player = (Player) sender;
        boolean b = BuildTicketsPlugin.getInstance().getBuildMode().remove(player.getUniqueId());

        if(!b) {
            BuildTicketsPlugin.getInstance().getBuildMode().add(player.getUniqueId());
            player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDMODE_ON, player));
        }
        else player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDMODE_OFF, player));

        return true;
    }
}
