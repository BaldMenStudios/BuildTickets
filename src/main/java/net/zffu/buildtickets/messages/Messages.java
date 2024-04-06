package net.zffu.buildtickets.messages;

import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {

    public static String PREFIX;
    public static String NO_PERMISSION;
    public static String INVALID_USAGE;

    public static String ENABLED_BUILD_MODE;
    public static String DISABLED_BUILD_MODE;



    public Messages(YamlConfiguration configuration) {
        PREFIX = configuration.getString("prefix");

        NO_PERMISSION = PREFIX + configuration.getString("no-permission");
        INVALID_USAGE = PREFIX + configuration.getString( "invalid-usage");

        ENABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-enabled");
        DISABLED_BUILD_MODE = PREFIX + configuration.getString("buildmode-disabled");
    }

}
