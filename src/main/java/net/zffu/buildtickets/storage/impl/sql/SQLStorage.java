package net.zffu.buildtickets.storage.impl.sql;

import lombok.Getter;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.tickets.TicketPriority;
import net.zffu.buildtickets.utils.SQLFormatter;

import java.sql.*;
import java.util.*;

/**
 * Implementation of the SQL Storage.
 */
//todo: add loading
@Getter
public class SQLStorage implements IStorage {

    protected Statement statement;
    public Connection connection;
    protected SQLBuilderTable builders;
    protected SQLTicketTable tickets;

    public SQLStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void init() throws Exception {
        if(this.connection != null) {
            this.statement = this.connection.createStatement();
        }

        this.builders = new SQLBuilderTable(this);
        this.tickets = new SQLTicketTable(this);

        ResultSet buildersSet = this.builders.getEntries();

        while (buildersSet.next()) {
            UUID uuid = UUID.fromString(buildersSet.getString("uuid"));
            int created = buildersSet.getInt("created");
            int completed = buildersSet.getInt("completed");
            BuildTicketsPlugin.getInstance().getBuilders().put(uuid, new TicketBuilder(uuid, created, completed));
        }

        ResultSet ticketsSet = this.tickets.getEntries();

        while (ticketsSet.next()) {
            UUID uuid = UUID.fromString(ticketsSet.getString("uuid"));
            String reason = ticketsSet.getString("reason");
            TicketPriority priority = TicketPriority.values()[ticketsSet.getInt("priority")];
            UUID creator = UUID.fromString(ticketsSet.getString("creator"));
            Collection<UUID> builders = SQLFormatter.parseToCollectionUUID(ticketsSet.getString("builders"));
            Map<UUID, String> notes = SQLFormatter.parseMapUUID(ticketsSet.getString("notes"));
            int compMode = ticketsSet.getInt("completion");

            BuildTicket ticket = new BuildTicket(uuid, reason, priority, creator);
            ticket.setBuilders(builders);
            ticket.setNotes(notes);
            ticket.updateNoteCreators();
            if(compMode == 0) ticket.setWaitingForCompletionConfirmation(true);
            if(compMode == 1) ticket.setCompleted(true);

            ticket.setNeedsHelp((ticketsSet.getInt("help") == 1));

            BuildTicketsPlugin.getInstance().getTickets().add(ticket);
        }
    }

    @Override
    public void shutdown() {

        for(BuildTicket ticket : BuildTicketsPlugin.getInstance().getTickets()) {
            this.tickets.pushOrUpdateTicket(ticket.getTicketUUID(), ticket.getTicketReason(), ticket.getPriority().getIndex(), ticket.getCreatorUUID().toString(), SQLFormatter.parseCollectionObj(ticket.getBuilders()), SQLFormatter.parseMapObj(ticket.getNotes()), ticket.getTicketCompletionMode(), (ticket.isNeedsHelp() ? 1 : 0));
        }

        for(Map.Entry<UUID, TicketBuilder> builder : BuildTicketsPlugin.getInstance().getBuilders().entrySet()) {
            this.builders.pushOrUpdateBuilder(builder.getKey(), builder.getValue().getTicketsCreated(), builder.getValue().getTicketsCompleted());
        }

        try {
            this.connection.close();
            this.connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
