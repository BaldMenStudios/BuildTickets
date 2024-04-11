package net.zffu.buildtickets.storage;

public enum StorageType {

    SQLITE,
    CRIMSON;

    public static StorageType get(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}
