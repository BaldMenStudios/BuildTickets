package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildPhysicsSubCommand extends SubCommand {
    public BuildPhysicsSubCommand() {
        super("physics", "Toggles the building physics", Permissions.BUILD_PHYSICS.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!BuildTicketsPlugin.getInstance().isDoBuildPhysics()) return false;

        Player player = (Player) sender;

        boolean b = !BuildTicketsPlugin.getInstance().isBuildPhysics();
        BuildTicketsPlugin.getInstance().setBuildPhysics(b);

        if(b) player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDPHYSICS_ON, player));
        else player.sendMessage(LocaleManager.getMessage(LocaleString.BUILDPHYSICS_OFF, player));

        return true;
    }
}
