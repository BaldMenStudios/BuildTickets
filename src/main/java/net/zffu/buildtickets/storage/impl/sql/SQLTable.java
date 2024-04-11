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

    private SQLStorage storage;
    private final Statement statement;
    private String table;

    public SQLTable(SQLStorage database, String table) throws SQLException {
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

    public JsonObject getJson(UUID uuid, @Nullable JsonObject defaultVal) {
        String stringValue = getRaw(uuid).toString();
        if(stringValue != null) {
            try {
                return (JsonObject) new JsonParser().parse(stringValue);
            } catch (Exception e) {}
        }
        return defaultVal;
    }

    public Object getRaw(UUID uuid) {
        String select = "SELECT value from " + table + " WHERE key = ?";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(select)) {
            preparedStatement.setString(1, uuid.toString());
            ResultSet set = preparedStatement.executeQuery();
            if(set.next()) {
                return set.getObject("value");
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pushJSON(UUID uuid, JsonObject object) {
        pushOrUpdate(uuid, object.toString());
    }

    public void pushOrUpdate(UUID uuid, String value) {
        String insertOrUpdate = "INSERT OR REPLACE INTO " + table + " (uuid, value) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = this.storage.getConnection().prepareStatement(insertOrUpdate)) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, value);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
