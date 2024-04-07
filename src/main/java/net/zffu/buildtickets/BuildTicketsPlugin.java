package net.zffu.buildtickets;

import lombok.Getter;
import net.zffu.buildtickets.commands.BuildModeCommand;
import net.zffu.buildtickets.commands.TicketCommand;
import net.zffu.buildtickets.listeners.BuildModeListeners;
import net.zffu.buildtickets.listeners.ChatListener;
import net.zffu.buildtickets.config.Messages;
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
    private HashMap<UUID, Action<AsyncPlayerChatEvent>> chatHandlers = new HashMap<>();
    private ArrayList<BuildTicket> tickets = new ArrayList<>();


    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();

        if(!this.getConfig().contains("version") || this.getConfig().getInt("version") != CONFIG_VERSION) {
            this.getLogger().warning("Config is outdated! Resetting configuration...");
            saveConfig();
        }

        Messages.loadFromConfig(getConfig());

        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getCommand("ticket").setExecutor(new TicketCommand(getPermissions("create-ticket")[0], getPermissions("open-ticket-gui")[0]));

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
        entity.sendMessage(Messages.ENTER_PROMPT.getMessage());
        this.chatHandlers.put(entity.getUniqueId(), action);
    }

    /**
     * Gets the permissions from the config.
     * The array is:
     * - First Element: Permission on self tickets (tickets owned by the player) or default
     * - Second Element: Permission on other tickets (tickets owned by another player) or self permission if none is set
     * @param permissionId the key in the config with "-permission" remove
     * @return the array of permissions.
     */
    public String[] getPermissions(String permissionId) {
        String permission = "";
        String otherPermission = "";
        if(!getConfig().contains(permissionId + "-permission")) {
            Bukkit.getLogger().warning("The permission " + permissionId + " could not be loaded correctly, please check if the config is valid");
            return null;
        }
        permission = getConfig().getString(permissionId + "-permission");
        otherPermission = permission;
        if(getConfig().contains(permissionId + "-other-permission")) {
            otherPermission = getConfig().getString(permissionId + "-other-permission");
        }
        return new String[] {permission, otherPermission};
    }

    public static BuildTicketsPlugin getInstance() {
        return INSTANCE;
    }

}
