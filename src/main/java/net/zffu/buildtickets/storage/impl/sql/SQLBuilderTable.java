package net.zffu.buildtickets.storage.impl.sql;

import java.sql.SQLException;

public class SQLBuilderTable extends SQLTable {
    public SQLBuilderTable(SQLStorage database, String table) throws SQLException {
        super(database, table);
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
}
