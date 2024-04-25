package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.commands.sub.impl.BuildModeSubCommand;
import net.zffu.buildtickets.commands.sub.impl.CreateSubCommand;
import net.zffu.buildtickets.commands.sub.impl.GuiSubCommand;
import net.zffu.buildtickets.commands.sub.impl.PanelSubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BuildTicketsCommand implements CommandExecutor, TabCompleter {

    private SubCommand[] commands;

    public BuildTicketsCommand() {
        this.commands = new SubCommand[] {new GuiSubCommand(), new CreateSubCommand(), new PanelSubCommand(), new BuildModeSubCommand()};
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cYou cannot run this command as a console!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            sender.sendMessage("§6Running §eBuildTickets v" + BuildTicketsPlugin.getInstance().getDescription().getVersion());
            sender.sendMessage((Permissions.HELP_MESSAGE.hasPermission(player) ? "§6Use §e/buildtickets help §6to see the available commands!" : LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player)));
            return true;
        }

        if(args[0].equals("help")) {
            for(SubCommand sub : commands) {
                String perm = (sub.permission != null && player.hasPermission(sub.permission)) ? "§m" : "";
                sender.sendMessage("§6> §e/bt " + perm + sub.name);
            }
            return true;
        }

        for(SubCommand sub : commands) {
            if(args[0].equals(sub.name)) {
                if(sub.permission != null && !player.hasPermission(sub.permission)) {
                    player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
                    return false;
                }
                sub.execute(sender, args);
                return true;
            }
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String c, @NotNull String[] strings) {
        List<String> s = new ArrayList<>();
        for(SubCommand sub : commands) {
            if(sub.permission != null && commandSender.hasPermission(sub.permission)) s.add(sub.name);
        }
        return s;
    }
}
