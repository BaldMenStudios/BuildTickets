package net.zffu.buildtickets.messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {

    // GENERAL
    public static String PREFIX;
    public static String NO_PERMISSION;
    public static String INVALID_USAGE;

    // Build Mode
    public static String ENABLED_BUILD_MODE;
    public static String DISABLED_BUILD_MODE;

    // Notes
    public static String NOTE_ADD;
    public static String NOTE_EDIT;

    // Ticket
    public static String NO_NEED_HELP;
    public static String TICKET_JOINED;
    public static String ALREADY_JOINED;
    public static String TICKET_LEFT;
    public static String TICKET_ALREADY_PRIORITY;
    public static String TICKET_CHANGED_PRIORITY;
    public static String TICKET_HELP_ON;
    public static String TICKET_HELP_OFF;
    public static String TICKET_NOT_BUILDER;
    public static String TICKET_CREATE;

    // Pages
    public static String ALREADY_FIRST_PAGE;
    public static String ALREADY_LAST_PAGE;

    public static String ENTER_PROMPT;


    public Messages(FileConfiguration configuration) {
        PREFIX = configuration.getString("prefix");

        NO_PERMISSION = PREFIX + configuration.getString("no-permission");
        INVALID_USAGE = PREFIX + configuration.getString( "invalid-usage");

        ENABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-enabled");
        DISABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-disabled");

        NOTE_ADD = PREFIX + configuration.getString("ticket-note-add");
        NOTE_EDIT = PREFIX + configuration.getString("ticket-note-edit");

        NO_NEED_HELP = PREFIX + configuration.getString("ticket-no-need-help");
        TICKET_JOINED = PREFIX + configuration.getString("ticket-joined");
        ALREADY_JOINED = PREFIX + configuration.getString("ticket-already-joined");
        TICKET_LEFT = PREFIX + configuration.getString("ticket-left");
        TICKET_NOT_BUILDER = PREFIX + configuration.getString("ticket-not-builder");
        TICKET_ALREADY_PRIORITY = PREFIX + configuration.getString("ticket-already-priority");
        TICKET_CHANGED_PRIORITY = PREFIX + configuration.getString("ticket-changed-priority");
        TICKET_HELP_ON = PREFIX + configuration.getString("ticket-request-help-on");
        TICKET_HELP_OFF = PREFIX + configuration.getString("ticket-request-help-off");
        TICKET_CREATE = PREFIX + configuration.getString("ticket-created");

        ALREADY_FIRST_PAGE = PREFIX + configuration.getString("page-already-first-page");
        ALREADY_LAST_PAGE = PREFIX + configuration.getString("page-already-last-page");

        ENTER_PROMPT = PREFIX + configuration.getString("enter-prompt");
    }

}
