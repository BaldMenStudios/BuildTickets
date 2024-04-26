package net.zffu.buildtickets.data;

import com.google.gson.JsonObject;
import lombok.Getter;
import net.zffu.buildtickets.gui.ItemConvertible;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

/**
 * Represents a builder player.
 */
@Getter
public class TicketBuilder implements ItemConvertible {

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

    public TicketBuilder(UUID uuid, int created, int completed) {
        this.uuid = uuid;
        this.ticketsCreated = created;
        this.ticketsCompleted = completed;
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

    @Override
    public ItemStack toItemStack() {
        ItemStack stack = HeadUtils.getHeadStack(this.getUuid());
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§a" + Bukkit.getOfflinePlayer(this.getUuid()).getPlayer().getName() + "'s Statistics");
        meta.setLore(Arrays.asList(
                "§7Tickets Created: §f" + this.getTicketsCreated(),
                "§7Tickets Completed: §f" + this.getTicketsCompleted()
        ));
        stack.setItemMeta(meta);
        return stack;
    }
}
