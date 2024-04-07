package net.zffu.buildtickets.data;

import java.util.UUID;

/**
 * Represents a builder player.
 */
public class TicketBuilder implements IDataElement {

    private UUID uuid;

    // Statistics
    private int ticketsCreated;
    private int ticketsCompleted;

    // Timestamps
    private long lastCompletedTicket;
    private long lastCreatedTicket;

    public void completeTicket() {
        this.ticketsCompleted++;
        this.lastCompletedTicket = System.currentTimeMillis();
    }

    public void createTicket() {
        this.ticketsCreated++;
        this.lastCreatedTicket = System.currentTimeMillis();
    }

}
