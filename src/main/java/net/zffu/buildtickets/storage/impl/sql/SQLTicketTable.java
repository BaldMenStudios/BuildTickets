package net.zffu.buildtickets.storage.impl.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SQLTicketTable extends SQLTable {
    public SQLTicketTable(SQLStorage database) throws SQLException {
        super(database, "tickets");
    }

    @Override
    public void init() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + table + " (uuid TEXT PRIMARY KEY, reason TEXT, priority INT, creator STRING, builders STRING, notes STRING,completion INT,help INT)";
        try {
            this.statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pushOrUpdateTicket(UUID uuid, String reason, int priority, String creator, String builders, String notes, int completionStatus, int help) {
        String insertOrUpdate = "INSERT OR REPLACE INTO " + table + " (uuid, reason, priority, creator, builders, notes, completion, help) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(insertOrUpdate)) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, reason);
            preparedStatement.setString(3, "" + priority);
            preparedStatement.setString(4, creator);
            preparedStatement.setString(5, builders);
            preparedStatement.setString(6, notes);
            preparedStatement.setString(7, "" + completionStatus);
            preparedStatement.setString(8, "" + help);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
