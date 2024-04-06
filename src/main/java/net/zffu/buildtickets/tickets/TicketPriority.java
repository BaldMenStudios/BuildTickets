package net.zffu.buildtickets.tickets;

import lombok.Getter;

@Getter
public enum TicketPriority {

    HIGH("High Priority"),
    NORMAL("Normal Priority"),
    LOW("Low Priority"),
    NONE("No Priority");

    private String display;

    private TicketPriority(String display) {
        this.display = display;
    }

    public static TicketPriority getValue(String id) {
        try {
            return valueOf(id);
        } catch (Exception e) {
            return null;
        }
    }

}
