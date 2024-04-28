package net.zffu.buildtickets.commands.sub;

import org.bukkit.command.CommandSender;

/**
 * A Sub Command of BuildTickets.
 */
public abstract class SubCommand {

    public String name;
    public String[] options;
    public String description;
    public String permission;

    public SubCommand(String name, String description, String permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }
    public SubCommand(String name, String description, String permission, String[] options) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.options = options;
    }


    public SubCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getUsage() {
        return "/bt " + name;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

}
