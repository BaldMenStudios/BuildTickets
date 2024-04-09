package net.zffu.buildtickets.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static JsonArray toJsonArray(List<?> list) {
        JsonArray array = new JsonArray();
        for(Object value : list) {
            array.add(value.toString());
        }
        return array;
    }

    public static JsonObject toMap(Map<?, ?> map) {
        JsonObject object = new JsonObject();
        for(Map.Entry<?, ?> entry : map.entrySet()) {
            object.addProperty(entry.getKey().toString(), entry.getValue().toString());
        }
        return object;
    }

}
