package net.zffu.buildtickets.storage;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.storage.impl.crimson.CrimsonStorage;
import net.zffu.buildtickets.storage.impl.sql.SQLConnectionFactory;
import net.zffu.buildtickets.storage.impl.sql.SQLStorage;

public class StorageFactory {

    public static IStorage createStorageFromType(StorageType type) {
        switch (type) {
            case SQLITE:
                return new SQLStorage(SQLConnectionFactory.fabricateConnection(BuildTicketsPlugin.getInstance().getConfig().getString("database-url", ""), type));
            case CRIMSON:
                return new CrimsonStorage();
        }
        return null;
    }

}
