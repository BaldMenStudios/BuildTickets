package net.zffu.buildtickets.config;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

@Getter
public enum Messages {

    // GENERAL
    PREFIX("prefix", "§8[§aBuildTickets§r§8] §r"),
    NO_PERMISSION("no-permission", "§cYou do not have the required permission to execute this action!"),
    INVALID_USAGE("invalid-usage", "§cMissing arguments! Please check the command help page!"),
    PAGE_ALREADY_FIRST_PAGE("page-already-first-page", "§cYou are already on the first page!"),
    PAGE_ALREADY_LAST_PAGE("page-already-last-page", "§cYou are already on the last page!"),
    ENTER_PROMPT("enter-prompt", "§aPlease enter the prompt in the chat:"),

    // GENERAL TICKET
    TICKET_NOTE_ADD("ticket-note-add", "§aYou added a note for the ticket!"),
    TICKET_NOTE_EDIT("ticket-note-edit", "§aYou edited your previous note on the ticket!"),
    TICKET_CREATED("ticket-created", "§aYou created a ticket!"),
    TICKET_NO_NEED_HELP("ticket-no-need-help", "§cThis ticket doesn't need help from other players, to contribute to this ticket, please ask the creator of the ticket to ask for help."),
    TICKET_JOINED("ticket-joined", "§aYou joined the ticket."),
    TICKET_ALREADY_JOINED("ticket-already-joined", "§aYou already joined this ticket."),
    TICKET_NOT_BUILDER("ticket-not-builder", "§cYou are not a builder of this ticket."),
    TICKET_LEFT("ticket-left", "§cYou left the ticket."),
    TICKET_PRIORITY("ticket-already-priority", "§aThe ticket already has this priority!"),
    TICKET_PRIORITY_CHANGE("ticket-changed-priority", "§aYou changed the ticket priority!"),
    TICKET_REQUEST_HELP_ON("ticket-request-help-on", "§aYou are now requesting help from other builders!"),
    TICKET_REQUEST_HELP_OFF("ticket-request-help-off", "§aYou are no longer requesting help from other builders!"),
    TICKET_WAITING_COMPLETION("ticket-waiting-completion", "§aYou marked the ticket as completed! You must wait for an admin to confirm it as completed!"),
    TICKET_COMPLETED("ticket-completed", "§aYou marked this ticket as completed!"),

    // BUILD MODE
    BUILD_MODE_ENABLED("buildmode-enabled", "§aYou enabled build mode!"),
    BUILD_MODE_DISABLED("buildmode-disabled", "§aYou disabled build mode!"),

    BUILD_PHYSICS_ENABLED("buildphyics-enabled", "§aYou enabled block physics!"),
    BUILD_PHYSICS_DISABLED("buildphysics-disabled", "§aYou disabled block physics!");

    private String message;
    private String configKey;

    private Messages(String configKey, String defaultMessage) {
        this.configKey = configKey;
        this.message = defaultMessage;
    }

    public static void loadFromConfig(FileConfiguration configuration) {
        for(Messages messages : Messages.values()) {
            if(configuration.contains(messages.configKey)) {
                if(messages == PREFIX) {
                    messages.message = configuration.getString(messages.configKey);
                }
                else {
                    messages.message = PREFIX.message + configuration.getString(messages.configKey);
                }
            }
        }
    }

}
