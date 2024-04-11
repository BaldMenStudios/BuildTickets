package net.zffu.buildtickets.storage.impl.crimson;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.tickets.TicketPriority;
import net.zffu.crimson.CrimsonDatabase;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The CrimsonDB Storage Implementation for BuildTickets.
 */
public class CrimsonStorage implements IStorage {

    private CrimsonDatabase db;
    private CrimsonTable tickets;
    private CrimsonTable builders;

    @Override
    public void init() throws Exception {
        this.db = new CrimsonDatabase(new File(BuildTicketsPlugin.getInstance().getDataFolder(), "db"));
        this.db.loadTables();

        this.tickets = this.db.getOrCreateTable("tickets");
        this.builders = this.db.getOrCreateTable("builders");

        // Structure: ticketUUID, creator, reason, priority as index, completion, needsHelp as int (true = 1)
        this.tickets.useTemplateIfEmpty(ParameterType.STRING, ParameterType.STRING, ParameterType.STRING, ParameterType.INTEGER, ParameterType.INTEGER, ParameterType.INTEGER);

        // Structure: uuid, created, completed
        this.builders.useTemplateIfEmpty(ParameterType.STRING, ParameterType.INTEGER, ParameterType.INTEGER);

        for(Map.Entry<Object, Object[]> entry : this.tickets.getEntries().entrySet()) {
            BuildTicketsPlugin.getInstance().getBuilders().put(UUID.fromString(entry.getKey().toString()), new TicketBuilder(UUID.fromString(entry.getKey().toString()), (Integer)entry.getValue()[0], (Integer)entry.getValue()[1]));
        }

        for(Map.Entry<Object, Object[]> entry : this.builders.getEntries().entrySet()) {
            BuildTicketsPlugin.getInstance().getTickets().add(new BuildTicket(UUID.fromString(entry.getKey().toString()), UUID.fromString(entry.getValue()[0].toString()), entry.getValue()[1].toString(), TicketPriority.values()[(Integer)entry.getValue()[2]], (Integer)entry.getValue()[3],((Integer)entry.getValue()[4] == 1)));
        }
    }

    @Override
    public void shutdown() {
        try {

            for(Map.Entry<UUID, TicketBuilder> builderEntry : BuildTicketsPlugin.getInstance().getBuilders().entrySet()) {
                this.builders.addEntry(builderEntry.getKey().toString(), builderEntry.getValue().getTicketsCreated(), builderEntry.getValue().getTicketsCompleted());
            }

            for(BuildTicket ticket : BuildTicketsPlugin.getInstance().getTickets()) {
                this.tickets.addEntry(ticket.getTicketUUID().toString(), ticket.getCreatorUUID().toString(), ticket.getTicketReason(), ticket.getPriority().getIndex(), ticket.getTicketCompletionMode());
            }

            this.db.saveTables();
        } catch (Exception e) {
            BuildTicketsPlugin.getInstance().getLogger().warning("Could not save Crimson Database: " + e);
        }
    }

}
