package net.zffu.buildtickets.tickets;

import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
    private final UUID creator;
    private ArrayList<UUID> builders = new ArrayList<>();
    private ArrayList<UUID> noteCreators = new ArrayList<>();
    private HashMap<UUID, String> notes = new HashMap<>();
    private boolean needsHelp;

    public BuildTicket(String reason, TicketPriority priority, UUID creator) {
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

    public String getCreator() {
        return Bukkit.getOfflinePlayer(this.creator).getPlayer().getName();
    }

    public String getFormattedBuilders() {
        String s = "";
        boolean b = false;
        for(UUID uuid : builders) {
            if(b) s += ", ";
            s += Bukkit.getOfflinePlayer(uuid).getPlayer().getName();
            b = true;
        }
        return s;
    }

}
