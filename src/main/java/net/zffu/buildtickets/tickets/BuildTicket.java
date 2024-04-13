package net.zffu.buildtickets.tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.utils.JsonUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class BuildTicket {

    private UUID ticketUUID;
    private String ticketReason;
    private TicketPriority priority;
    private final UUID creator;
    private ArrayList<UUID> builders = new ArrayList<>();
    private ArrayList<UUID> noteCreators = new ArrayList<>();
    private HashMap<UUID, String> notes = new HashMap<>();
    private boolean needsHelp;

    private boolean isWaitingForCompletionConfirmation;
    private boolean completed;

    public BuildTicket(String reason, TicketPriority priority, UUID creator) {
        this.ticketUUID = UUID.randomUUID();
        this.ticketReason = reason;
        this.priority = priority;
        this.creator = creator;
    }

    public BuildTicket(UUID ticketUUID, String reason, TicketPriority priority, UUID creator) {
        this.ticketUUID = ticketUUID;
        this.ticketReason = reason;
        this.priority = priority;
        this.creator = creator;
    }

    public BuildTicket(UUID ticketUUID, UUID creator, String reason, TicketPriority priority, int completionMode, boolean needsHelp) {
        this.ticketUUID = ticketUUID;
        this.creator = creator;
        this.ticketReason = reason;
        this.priority = priority;
        this.isWaitingForCompletionConfirmation = (completionMode == 0);
        this.completed = (completionMode == 1);
        this.needsHelp = needsHelp;
    }

    /**
     * Sends a note to the ticket.
     * @param player
     * @param note
     */
    public void sendNote(Player player, String note) {
        boolean b = notes.containsKey(player.getUniqueId());
        notes.put(player.getUniqueId(), note);

        if(b) player.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_NOTE_EDIT, player));
        else {
            player.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_NOTE_ADDED, player));
            noteCreators.add(player.getUniqueId());
        }
    }

    public String getCreator() {
        return Bukkit.getOfflinePlayer(this.creator).getPlayer().getName();
    }

    public UUID getCreatorUUID() {
        return creator;
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

    public int getTicketCompletionMode() {
        return (completed ? 1 : (isWaitingForCompletionConfirmation ? 0 : -1));
    }

    public JsonObject toJSON() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("creator", creator.toString());
        jsonObject.addProperty("reason", ticketReason);
        jsonObject.addProperty("priority", priority.getIndex());
        jsonObject.addProperty("completion", (completed ? 1 : (isWaitingForCompletionConfirmation ? 0 : -1)));
        jsonObject.add("builders", JsonUtils.toJsonArray(this.builders));
        jsonObject.add("notes", JsonUtils.toMap(this.notes));
        jsonObject.addProperty("help", needsHelp);
        return jsonObject;
    }

}
