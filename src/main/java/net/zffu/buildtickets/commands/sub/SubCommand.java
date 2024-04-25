package net.zffu.buildtickets.commands.sub;

import org.bukkit.command.CommandSender;

/**
 * A Sub Command of BuildTickets.
 */
public abstract class SubCommand {

    public String name;
    public String description;
    public String permission;

    public SubCommand(String name, String description, String permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    public SubCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

}
