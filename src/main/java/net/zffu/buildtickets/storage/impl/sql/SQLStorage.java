package net.zffu.buildtickets.storage.impl.sql;

import lombok.Getter;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;

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
    protected SQLTable builders;
    protected SQLTable tickets;

    public SQLStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void init() throws Exception {
        if(this.connection != null) {
            this.statement = this.connection.createStatement();
        }

        this.builders = new SQLTable(this, "builders");
        this.tickets = new SQLTable(this, "tickets");
    }

    @Override
    public void shutdown() {

        for(BuildTicket ticket : BuildTicketsPlugin.getInstance().getTickets()) {
            this.tickets.pushJSON(ticket.getTicketUUID(), ticket.toJSON());
        }

        for(Map.Entry<UUID, TicketBuilder> builder : BuildTicketsPlugin.getInstance().getBuilders().entrySet()) {
            this.builders.pushJSON(builder.getKey(), builder.getValue().toJSON());
        }

        try {
            this.connection.close();
            this.connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
