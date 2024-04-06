package net.zffu.buildtickets;

import lombok.Getter;
import net.zffu.buildtickets.commands.BuildModeCommand;
import net.zffu.buildtickets.commands.TicketCommand;
import net.zffu.buildtickets.listeners.BuildModeListeners;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

@Getter
public final class BuildTicketsPlugin extends JavaPlugin {
    private static BuildTicketsPlugin INSTANCE;

    private ArrayList<UUID> buildMode = new ArrayList<>();

    private ArrayList<BuildTicket> tickets = new ArrayList<>();


    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();

        this.getCommand("ticket").setExecutor(new TicketCommand(this.getConfig().getString("tickets.command-permission")));

        this.getLogger().info("Loading Features...");

        if(getConfig().getBoolean("build-mode.enabled")) {
            this.getCommand("buildmode").setExecutor(new BuildModeCommand(this.getConfig().getString("build-mode.permission")));
            this.getServer().getPluginManager().registerEvents(new BuildModeListeners(), this);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BuildTicketsPlugin getInstance() {
        return INSTANCE;
    }

}
