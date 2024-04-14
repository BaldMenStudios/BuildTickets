package net.zffu.buildtickets.storage.impl.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SQLBuilderTable extends SQLTable {
    public SQLBuilderTable(SQLStorage database) throws SQLException {
        super(database, "builders");
    }

    @Override
    public void init() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + table + " (uuid TEXT PRIMARY KEY, created INT, completed INT)";
        try {
            this.statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pushOrUpdateBuilder(UUID uuid, int created, int completed) {
        String insertOrUpdate = "INSERT OR REPLACE INTO " + table + " (uuid, created, completed) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(insertOrUpdate)) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, created + "");
            preparedStatement.setString(3, completed + "");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
