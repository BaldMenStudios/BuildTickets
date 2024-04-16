package net.zffu.buildtickets.api;

/**
 * Class used to access the BuildTickets API.
 */
public class BuildTicketsAPI {

    private static BuildTicketsAPI INSTANCE;

    /**
     * Constructs a new Build Tickets API Instance.
     */
    public BuildTicketsAPI() {
        if(INSTANCE != null) throw new IllegalStateException("Could not create a BuildTickets API Instance.");
        INSTANCE = this;
    }

    /**
     * Gets the active Build Tickets API Instance.
     * @return
     */
    public static BuildTicketsAPI getInstance() {
        return INSTANCE;
    }

}
