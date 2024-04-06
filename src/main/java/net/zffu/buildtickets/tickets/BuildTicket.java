package net.zffu.buildtickets.tickets;

import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.messages.Messages;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<UUID> noteCreators = new ArrayList<>();
    private HashMap<UUID, String> notes = new HashMap<>();

    public BuildTicket(String reason, TicketPriority priority, String creator) {
        this.ticketReason = reason;
        this.priority = priority;
        this.creator = creator;
    }

    /**
     * Sends a note to the ticket.
     * @param player
     * @param note
     */
    public void sendNote(Player player, String note) {
        boolean b = notes.containsKey(player.getUniqueId());
        notes.put(player.getUniqueId(), note);

        if(b) player.sendMessage(Messages.NOTE_EDIT);
        else {
            player.sendMessage(Messages.NOTE_ADD);
            noteCreators.add(player.getUniqueId());
        }
    }

}
