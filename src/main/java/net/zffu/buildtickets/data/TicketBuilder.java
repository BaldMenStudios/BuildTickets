package net.zffu.buildtickets.data;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents a builder player.
 */
@Getter
public class TicketBuilder {

    private UUID uuid;

    // Statistics
    private int ticketsCreated;
    private int ticketsCompleted;

    // Timestamps
    private long lastCompletedTicket;
    private long lastCreatedTicket;

    public TicketBuilder(UUID uuid) {
        this.uuid = uuid;
    }

    public void completeTicket() {
        this.ticketsCompleted++;
        this.lastCompletedTicket = System.currentTimeMillis();
    }

    public void createTicket() {
        this.ticketsCreated++;
        this.lastCreatedTicket = System.currentTimeMillis();
    }

}
