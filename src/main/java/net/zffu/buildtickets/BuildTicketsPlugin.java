package net.zffu.buildtickets;

import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.commands.BuildModeCommand;
import net.zffu.buildtickets.commands.BuildPhysicsCommand;
import net.zffu.buildtickets.commands.TicketCommand;
import net.zffu.buildtickets.commands.TicketPanelCommand;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.listeners.BuildModeListeners;
import net.zffu.buildtickets.listeners.ChatListener;
import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.storage.StorageFactory;
import net.zffu.buildtickets.storage.StorageType;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
public final class BuildTicketsPlugin extends JavaPlugin {
    private static BuildTicketsPlugin INSTANCE;

    // Used to make sure that the config is valid.
    private final int CONFIG_VERSION = 1;

    private ArrayList<UUID> buildMode = new ArrayList<>();
    @Setter
    private boolean buildPhysics;

    private HashMap<UUID, Action<AsyncPlayerChatEvent>> chatHandlers = new HashMap<>();
    private ArrayList<BuildTicket> tickets = new ArrayList<>();
    private boolean smartTicketPermissions;

    private HashMap<UUID, TicketBuilder> builders = new HashMap<>();

    private IStorage storage;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();

        if(!this.getConfig().contains("version") || this.getConfig().getInt("version") != CONFIG_VERSION) {
            this.getLogger().warning("Config is outdated! Resetting configuration...");
            saveConfig();
        }

        this.loadStorage();

        this.smartTicketPermissions = getConfig().getBoolean("tickets.smart-ticket-permissions");

        Messages.loadFromConfig(getConfig());

        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getCommand("ticket").setExecutor(new TicketCommand());
        this.getCommand("ticketpanel").setExecutor(new TicketPanelCommand());

        this.getLogger().info("Loading Features...");

        if(getConfig().getBoolean("build-mode.enabled", false)) {
            this.getCommand("buildmode").setExecutor(new BuildModeCommand());
            this.getServer().getPluginManager().registerEvents(new BuildModeListeners(), this);
        }

        if(getConfig().getBoolean("build-physics.enabled", false)) {
            this.getCommand("buildphysics").setExecutor(new BuildPhysicsCommand());
        }

    }

    public void loadStorage() {
        String storageTypeId = this.getConfig().getString("storage-mode");
        if(storageTypeId.equals("none")) return;
        StorageType storageType = StorageType.get(this.getConfig().getString("storage-mode"));
        this.getLogger().info("Loading Data Storage...");

        this.getLogger().warning("Database Storage is in development! Errors crashing the plugin when loading the data could occur. If you wish to disable this please enter none in the storage-mode config value!");

        if(storageType == null) {
            this.getLogger().warning("Data Storage type " + this.getConfig().getString("storage-mode") + " is invalid!");
            return;
        }

        this.getLogger().info("Loading " + storageType.name() + " Storage...");
        this.storage = StorageFactory.createStorageFromType(storageType);

        try {
            this.storage.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            this.storage.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doChatHandler(HumanEntity entity, Action<AsyncPlayerChatEvent> action) {
        entity.closeInventory();
        entity.sendMessage(Messages.ENTER_PROMPT.getMessage());
        this.chatHandlers.put(entity.getUniqueId(), action);
    }

    /**
     * Gets or create the builder.
     * @param uuid
     * @return
     */
    public TicketBuilder getOrCreateBuilder(UUID uuid) {
        TicketBuilder builder = this.builders.computeIfAbsent(uuid, TicketBuilder::new);
        return builder;
    }

    public static BuildTicketsPlugin getInstance() {
        return INSTANCE;
    }

}
