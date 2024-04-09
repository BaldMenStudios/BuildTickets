package net.zffu.buildtickets.data;

import com.google.gson.JsonObject;
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

    public JsonObject toJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("created", this.ticketsCreated);
        jsonObject.addProperty("completed", this.ticketsCompleted);
        return jsonObject;
    }

    public static TicketBuilder fromJSON(UUID uuid, JsonObject object) {
        TicketBuilder builder =  new TicketBuilder(uuid);
        builder.ticketsCreated = object.get("created").getAsInt();
        builder.ticketsCompleted = object.get("completed").getAsInt();
        return builder;
    }

}
