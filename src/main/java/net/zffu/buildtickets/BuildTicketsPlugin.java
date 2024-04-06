package net.zffu.buildtickets;

import org.bukkit.plugin.java.JavaPlugin;

public final class BuildTicketsPlugin extends JavaPlugin {


    private static BuildTicketsPlugin INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BuildTicketsPlugin getInstance() {
        return INSTANCE;
    }

}
