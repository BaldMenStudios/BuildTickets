package net.zffu.buildtickets;

import lombok.Getter;
import net.zffu.buildtickets.commands.BuildModeCommand;
import net.zffu.buildtickets.commands.TicketCommand;
import net.zffu.buildtickets.listeners.BuildModeListeners;
import net.zffu.buildtickets.listeners.ChatListener;
import net.zffu.buildtickets.messages.Messages;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.Action;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
public final class BuildTicketsPlugin extends JavaPlugin {
    private static BuildTicketsPlugin INSTANCE;

    private ArrayList<UUID> buildMode = new ArrayList<>();
    private HashMap<UUID, Action<AsyncPlayerChatEvent>> chatHandlers = new HashMap<>();
    private ArrayList<BuildTicket> tickets = new ArrayList<>();


    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();
        new Messages(this.getConfig());

        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
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

    public void doChatHandler(HumanEntity entity, Action<AsyncPlayerChatEvent> action) {
        entity.closeInventory();
        entity.sendMessage(Messages.ENTER_PROMPT);
        this.chatHandlers.put(entity.getUniqueId(), action);
    }

    public static BuildTicketsPlugin getInstance() {
        return INSTANCE;
    }

}
