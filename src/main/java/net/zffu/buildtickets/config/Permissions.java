package net.zffu.buildtickets.config;

import lombok.Getter;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;

@Getter
public enum Permissions {

    OPEN_TICKET_GUI("open-ticket-gui", "buildtickets.tickets.gui"),
    CREATE_TICKET("create-ticket", "buildtickets.tickets.create"),
    CHANGE_TICKET_PRIORITY("change-ticket-priority", "buildtickets.tickets", "change_priority"),
    JOIN_TICKET("join-ticket", "buildtickets.tickets", "join"),
    JOIN_TICKET_WITHOUT_HELP("join-without-help", "buildtickets.tickets", "join_without_help"),
    REQUEST_HELP("request_help", "buildtickets.tickets", "request_help"),
    TICKET_COMPLETE("ticket-mark-as-complete", "buildtickets.tickets", "complete.mark"),
    TICKET_COMPLETE_CONFIRM("ticket-confirm-as-complete", "buildtickets.tickets", "complete.confirm"),
    TICKET_CHANGE_REASON("ticket-change-reason", "buildtickets.tickets", "change_reason"),
    LEAVE_TICKET("ticket-leave", "buildtickets.tickets", "leave"),
    TICKET_VIEWER("open-ticket-viewer", "buildtickets.viewer", "open"),

    OPEN_TICKET_PANEL("open-ticket-panel", "buildtickets.panel.open"),
    PANEL_PLAYER_STATS("player-stats-panel-permission", "buildtickets.panel.player_stats"),
    PANEL_ACTIVE_TICKETS("active-tickets-panel", "buildtickets.panel.active_tickets"),

    BUILD_MODE_TOGGLE("build-mode-toggle", "buildmode.toggle"),
    BUILD_PHYSICS("build-physics-toggle", "buildphysics.toggle");

    private String permission;
    private String otherPermission;
    private String configKey;

    private Permissions(String configId, String defaultPermission) {
        this.configKey = configId;
        this.permission = defaultPermission;
        this.otherPermission = defaultPermission;
    }

    private Permissions(String configId, String permissionNode, String permissionSubNode) {
        this.configKey = configId;
        this.permission = permissionNode + "." + permissionSubNode;
        this.otherPermission = permissionNode + ".other." + permissionSubNode;
    }

    public static void loadFromConfig(FileConfiguration configuration) {
        for(Permissions permissions : Permissions.values()) {
            if(configuration.contains(permissions.configKey + "-permission")) {
                permissions.permission = configuration.getString(permissions.configKey + "-permission");
            }
            if(configuration.contains(permissions.configKey + "-other-permission")) {
                permissions.otherPermission = configuration.getString(permissions.configKey + "-other-permission");
            }
        }
    }

    public boolean hasPermission(HumanEntity entity) {
        return (permission.isEmpty() || entity.hasPermission(permission));
    }

    public boolean hasPermission(HumanEntity entity, BuildTicket ticket) {
        String perm = (ticket.getCreatorUUID().equals(entity.getUniqueId()) ? permission : otherPermission);
        return (perm.isEmpty() || entity.hasPermission(perm) || (BuildTicketsPlugin.getInstance().isSmartTicketPermissions() && ticket.getBuilders().contains(entity.getUniqueId())));
    }

}
