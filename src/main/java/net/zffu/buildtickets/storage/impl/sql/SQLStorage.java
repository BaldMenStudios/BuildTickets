package net.zffu.buildtickets.storage.impl.sql;

import lombok.Getter;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;
import net.zffu.buildtickets.utils.SQLFormatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Implementation of the SQL Storage.
 */
//todo: add loading
@Getter
public class SQLStorage implements IStorage {

    protected Statement statement;
    protected Connection connection;
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
