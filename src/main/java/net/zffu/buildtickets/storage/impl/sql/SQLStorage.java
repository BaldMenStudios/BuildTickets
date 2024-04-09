package net.zffu.buildtickets.storage.impl.sql;

import lombok.Getter;
import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.data.TicketBuilder;
import net.zffu.buildtickets.storage.IStorage;
import net.zffu.buildtickets.tickets.BuildTicket;
import org.h2.util.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Implementation of the SQL Storage.
 */
@Getter
public class SQLStorage implements IStorage {

    protected Statement statement;
    protected Connection connection;
    protected SQLTable builders;
    protected SQLTable tickets;
    protected String connectionURL;

    @Override
    public BuildTicketsPlugin getPlugin() {
        return BuildTicketsPlugin.getInstance();
    }

    @Override
    public String getStorageName() {
        return "MySQL";
    }

    public SQLStorage(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    @Override
    public void init() throws Exception {

        this.connection = DriverManager.getConnection(this.connectionURL);

        if(this.connection != null) {
            this.statement = this.connection.createStatement();
        }

        this.builders = new SQLTable(this, "builders");
        this.tickets = new SQLTable(this, "tickets");

    }

    @Override
    public void shutdown() {
        try {
            this.connection.close();
            this.connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TicketBuilder loadBuilder(UUID uuid) {
        return null;
    }

    @Override
    public Map<UUID, TicketBuilder> loadBuilders(Set<UUID> uuids) {
        return null;
    }

    @Override
    public void saveBuilder(TicketBuilder builder) {

    }

    @Override
    public Set<UUID> getUniqueBuilders() {
        return null;
    }

    @Override
    public Set<BuildTicket> getTickets() {
        return null;
    }

    @Override
    public void saveTicket(BuildTicket ticket) {
        JSONObject
    }
}
