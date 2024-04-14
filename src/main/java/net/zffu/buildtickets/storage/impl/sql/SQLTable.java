package net.zffu.buildtickets.storage.impl.sql;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.UUID;

public class SQLTable {

    protected SQLStorage storage;
    protected final Statement statement;
    protected String table;

    public SQLTable(SQLStorage database, String table) throws SQLException {
        this.storage = database;
        this.table = table;
        this.statement = database.getStatement();
        this.init();
    }

    public void init() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + table + " (uuid TEXT PRIMARY KEY, value TEXT)";
        try {
            this.statement.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getRaw(UUID uuid, String field) {
        String select = "SELECT " + field + " from " + table + " WHERE key = ?";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(select)) {
            preparedStatement.setString(1, uuid.toString());
            ResultSet set = preparedStatement.executeQuery();
            if(set.next()) {
                return set.getObject(field);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pushOrUpdateField(UUID uuid, String field, Object value) {
        String insertOrUpdate = "INSERT OR REPLACE INTO " + table + " (uuid, " + field + ") VALUES (?, ?)";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(insertOrUpdate)) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, value.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEntries() {
        try {
            return statement.executeQuery("SELECT * from " + table);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
