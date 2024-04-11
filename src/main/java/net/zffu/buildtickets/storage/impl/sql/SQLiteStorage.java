package net.zffu.buildtickets.storage.impl.sql;

import net.zffu.buildtickets.BuildTicketsPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;

public class SQLiteStorage extends SQLStorage {
    public SQLiteStorage() {
        super("");
    }

    @Override
    public void init() throws Exception {
        File folder = new File(BuildTicketsPlugin.getInstance().getDataFolder(), "buildtickets.db");
        if(!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException e) {
                BuildTicketsPlugin.getInstance().getLogger().warning("Could not write SQLite File!");
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + folder);

            this.statement = connection.createStatement();
            this.builders = new SQLTable(this, "builders");
            this.tickets = new SQLTable(this, "tickets");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
