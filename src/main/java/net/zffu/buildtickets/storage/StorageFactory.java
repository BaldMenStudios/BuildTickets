package net.zffu.buildtickets.storage;

import net.zffu.buildtickets.storage.impl.crimson.CrimsonStorage;
import net.zffu.buildtickets.storage.impl.sql.SQLiteStorage;

public class StorageFactory {

    public static IStorage createStorageFromType(StorageType type) {
        switch (type) {
            case SQLITE:
                return new SQLiteStorage();
            case CRIMSON:
                return new CrimsonStorage();
        }
        return null;
    }

}
