package net.zffu.buildtickets.storage.impl.sql;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.storage.StorageType;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnectionFactory {

    public static Connection fabricateConnection(String connectionURL, StorageType type) {

        if(type == StorageType.SQLITE) {
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
                return DriverManager.getConnection("jdbc:sqlite:" + folder);
            } catch (Exception e) {
                BuildTicketsPlugin.getInstance().getLogger().warning("Could not load SQLite!");
            }
        }

        return null;
    }

}
