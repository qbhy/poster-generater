package com.qbhy.poster.contracts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public interface Jsonable {
    String toJson() throws IOException;

    public static final ObjectMapper mapper = new ObjectMapper();

    static Object decode(String json, Class valueType) throws IOException {
        return mapper.readValue(json, valueType);
    }

    static MapResult decode(String json) throws IOException {
        return mapper.readValue(json, MapResult.class);
    }

    static String encode(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    class MapResult extends HashMap<String, Object> {
    }
}
