package net.zffu.buildtickets.api;

import net.zffu.buildtickets.BuildTicketsPlugin;

/**
 * Class used to access the BuildTickets API.
 */
public class BuildTicketsAPI {

    private static BuildTicketsAPI INSTANCE;
    private BuildTicketsPlugin plugin;

    /**
     * Constructs a new Build Tickets API Instance.
     * @param plugin
     */
    public BuildTicketsAPI(BuildTicketsPlugin plugin) {
        if(INSTANCE != null || plugin == null) throw new IllegalStateException("Could not create a BuildTickets API Instance.");
        INSTANCE = this;
        this.plugin = plugin;
    }

    /**
     * Gets the active Build Tickets API Instance.
     * @return
     */
    public static BuildTicketsAPI getInstance() {
        return INSTANCE;
    }

}
