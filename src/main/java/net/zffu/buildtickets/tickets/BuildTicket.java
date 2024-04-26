package net.zffu.buildtickets.tickets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.gui.ItemConvertible;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.utils.JsonUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Getter
@Setter
public class BuildTicket implements ItemConvertible {

    private UUID ticketUUID;
    private String ticketReason;
    private TicketPriority priority;
    private final UUID creator;
    private Collection<UUID> builders = new ArrayList<>();
    private List<UUID> noteCreators = new ArrayList<>();
    private Map<UUID, String> notes = new HashMap<>();
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

    public void updateNoteCreators() {
        this.noteCreators.clear();
        this.noteCreators.addAll(this.notes.keySet());
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

    @Override
    public ItemStack toItemStack() {
        Material material = Material.GREEN_DYE;

        if(this.isCompleted()) {
            material = Material.DIAMOND;
        }

        if(this.getBuilders().isEmpty()) {
            material = Material.RED_DYE;
        }

        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§a" + this.getTicketReason());
        meta.setLore(Arrays.asList("", "§7Creator: §f" + this.getCreator(), "§7Priority: §f" + this.getPriority().getDisplay(), "§7Claimed by: §f" + (this.getBuilders().isEmpty() ? "§cNone" : this.getFormattedBuilders()), "", "§eRight-Click to view the ticket!", "§eLeft-Click to add a note"));
        stack.setItemMeta(meta);
        return stack;
    }

    public void joinTicket(HumanEntity entity) {
        if(!Permissions.JOIN_TICKET.hasPermission(entity, this)) {
            entity.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, entity));
            return;
        }
        if(this.builders.contains(entity.getUniqueId())) {
            entity.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_JOIN_ALREADY, entity));
            return;
        }
        if(!this.isNeedsHelp() && !this.builders.isEmpty()) {
            entity.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_JOIN_HELP_DISABLED, entity));
            return;
        }
        this.builders.add(entity.getUniqueId());
        entity.sendMessage(LocaleManager.getMessage(LocaleString.TICKET_JOIN, entity));
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public void setNeedsHelp(boolean needsHelp) {
        this.needsHelp = needsHelp;
    }

}
