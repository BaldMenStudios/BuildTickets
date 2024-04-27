package net.zffu.buildtickets;

import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.commands.*;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.listeners.BuildModeListeners;
import net.zffu.buildtickets.listeners.BuildPhysicsListeners;
import net.zffu.buildtickets.listeners.ChatListener;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.storage.StorageFactory;
import net.zffu.buildtickets.storage.StorageType;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.Action;
import org.bstats.bukkit.Metrics;
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
    private final int CONFIG_VERSION = 2;

    private ArrayList<UUID> buildMode = new ArrayList<>();
    @Setter
    private boolean buildPhysics;

    @Setter
    private boolean buildModeEnabled;
    private boolean headGiverEnabled;
    private boolean doBuildPhysics;

    private HashMap<UUID, Action<AsyncPlayerChatEvent>> chatHandlers = new HashMap<>();
    private ArrayList<BuildTicket> tickets = new ArrayList<>();
    private boolean smartTicketPermissions;

    private HashMap<UUID, TicketBuilder> builders = new HashMap<>();

    private LocaleManager localeManager;

    private IStorage storage;

    private Metrics metrics;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.metrics = new Metrics(this, 21715);

        this.saveDefaultConfig();

        this.loadStorage();

        this.smartTicketPermissions = getConfig().getBoolean("tickets.smart-ticket-permissions");

        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getCommand("buildtickets").setExecutor(new BuildTicketsCommand());

        this.getLogger().info("Loading Locales...");
        this.localeManager = new LocaleManager(this);
        this.localeManager.loadLocales();

        this.getLogger().info("Loading Features...");

        if(buildModeEnabled = getConfig().getBoolean("build-mode.enabled", false)) {
            this.getServer().getPluginManager().registerEvents(new BuildModeListeners(), this);
        }

        if(doBuildPhysics = getConfig().getBoolean("build-physics.enabled", false)) {
            this.getServer().getPluginManager().registerEvents(new BuildPhysicsListeners(), this);
        }

        this.headGiverEnabled = getConfig().getBoolean("head-giver.enabled", false);
    }

    public void loadStorage() {
        String storageTypeId = this.getConfig().getString("storage-mode");
        if(storageTypeId.equals("none")) return;
        StorageType storageType = StorageType.get(this.getConfig().getString("storage-mode"));
        this.getLogger().info("Loading Data Storage...");

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
        entity.sendMessage(LocaleManager.getMessage(LocaleString.PROMPT_ENTER, entity));
        this.chatHandlers.put(entity.getUniqueId(), action);
    }

    /**
     * Registers the ticket into the ticket list and triggers the webhook if needed.
     * @param ticket
     */
    public void registerTicket(BuildTicket ticket) {
        this.tickets.add(ticket);
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
