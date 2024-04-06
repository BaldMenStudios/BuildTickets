package net.zffu.buildtickets.tickets;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BuildTicket {

    // Uses player names instead of uuids just to avoid having to get the name trough mojang all the time;
    private String ticketReason;
    private TicketPriority priority;
    private final String creator;
    private String claimer;

    public BuildTicket(String reason, TicketPriority priority, String creator) {
        this.ticketReason = reason;
        this.priority = priority;
        this.creator = creator;
    }

}
