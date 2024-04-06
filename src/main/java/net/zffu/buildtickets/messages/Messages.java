package net.zffu.buildtickets.messages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {

    public static String PREFIX;
    public static String NO_PERMISSION;
    public static String INVALID_USAGE;

    public static String ENABLED_BUILD_MODE;
    public static String DISABLED_BUILD_MODE;

    public static String NOTE_ADD;
    public static String NOTE_EDIT;
    public static String ALREADY_CLAIMED;
    public static String CLAIMED;

    public static String ALREADY_FIRST_PAGE;
    public static String ALREADY_LAST_PAGE;


    public Messages(FileConfiguration configuration) {
        PREFIX = configuration.getString("prefix");

        NO_PERMISSION = PREFIX + configuration.getString("no-permission");
        INVALID_USAGE = PREFIX + configuration.getString( "invalid-usage");

        ENABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-enabled");
        DISABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-disabled");

        NOTE_ADD = PREFIX + configuration.getString("ticket-note-add");
        NOTE_EDIT = PREFIX + configuration.getString("ticket-note-edit");
        ALREADY_CLAIMED = PREFIX + configuration.getString("ticket-already-claimed");
        CLAIMED = PREFIX + configuration.getString("ticket-claim");

        ALREADY_FIRST_PAGE = PREFIX + configuration.getString("page-already-first-page");
        ALREADY_LAST_PAGE = PREFIX + configuration.getString("page-already-last-page");
    }

}
