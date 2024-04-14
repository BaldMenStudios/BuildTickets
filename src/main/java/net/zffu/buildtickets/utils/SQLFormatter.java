package net.zffu.buildtickets.utils;

import java.util.*;

public class SQLFormatter {

    public static String parseCollection(Collection<SQLSerializable> collection) {
        String s = "";
        boolean b = false;
        for(SQLSerializable serializable : collection) {
            if(b) s += ",";
            s += serializable.toSQLString();
            b = true;
        }
        return s;
    }

    public static String parseMap(Map<Object, SQLSerializable> map) {
        String s = "";
        boolean b = false;
        for(Map.Entry<Object, SQLSerializable> entry : map.entrySet()) {
            if(b) s += ",";
            s += entry.getKey().toString();
            s += ";";
            s += entry.getValue().toSQLString();
            b = true;
        }
        return s;
    }

    public static <T extends SQLSerializable> Collection<T> parseToCollection(String raw) {
        List<T> t = new ArrayList<>();
        for(String s : raw.split(",")) {
            t.add(T.fromSQLString(s));
        }
        return t;
    }

    public static <V extends SQLSerializable> Map<String, V> parseMap(String str) {
        Map<String, V> map = new HashMap<>();
        for(String entry : str.split(",")) {
            String[] s = entry.split(";");
            map.put(s[0], V.fromSQLString(s[1]));
        }
        return map;
    }

    public static String parseCollectionObj(Collection<? extends Object> collection) {
        String s = "";
        boolean b = false;
        for(Object serializable : collection) {
            if(b) s += ",";
            s += serializable.toString();
            b = true;
        }
        return s;
    }

    public static String parseMapObj(Map<? extends Object, ? extends Object> map) {
        String s = "";
        boolean b = false;
        for(Map.Entry<? extends Object, ? extends Object> entry : map.entrySet()) {
            if(b) s += ",";
            s += entry.getKey().toString();
            s += ";";
            s += entry.getValue().toString();
            b = true;
        }
        return s;
    }

    public static Collection<UUID> parseToCollectionUUID(String raw) {
        List<UUID> t = new ArrayList<>();
        if(raw.isEmpty()) return t;
        for(String s : raw.split(",")) {
            t.add(UUID.fromString(s));
        }
        return t;
    }

    public static Map<UUID, String> parseMapUUID(String str) {
        Map<UUID, String> map = new HashMap<>();
        if(str.isEmpty()) return map;
        for(String entry : str.split(",")) {
            String[] s = entry.split(";");
            map.put(UUID.fromString(s[0]), s[1]);
        }
        return map;
    }

}
