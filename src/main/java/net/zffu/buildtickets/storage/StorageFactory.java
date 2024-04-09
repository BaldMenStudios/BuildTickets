package net.zffu.buildtickets.storage;

import net.zffu.buildtickets.BuildTicketsPlugin;
import net.zffu.buildtickets.storage.impl.sql.SQLStorage;
import net.zffu.buildtickets.storage.impl.sql.SQLiteStorage;

public class StorageFactory {

    public static IStorage createStorageFromType(StorageType type) {
        switch (type) {
            case SQLITE:
                return new SQLiteStorage();
        }
        return null;
    }

}
