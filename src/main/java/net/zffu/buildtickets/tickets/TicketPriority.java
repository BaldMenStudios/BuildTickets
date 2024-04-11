package net.zffu.buildtickets.tickets;

import lombok.Getter;

@Getter
public enum TicketPriority {

    NONE("No Priority",0),
    LOW("Low Priority",1),
    NORMAL("Normal Priority",2),
    MEDIUM("Medium Priority",3),
    HIGH("High Priority", 4);

    private String display;
    private int index;

    private TicketPriority(String display, int index) {
        this.display = display;
        this.index = index;
    }

    public static TicketPriority getValue(String id) {
        try {
            return valueOf(id);
        } catch (Exception e) {
            return NORMAL;
        }
    }

}
