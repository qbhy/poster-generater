package com.qbhy.poster.contracts;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public interface JsonableInterface {
    String toJson();

    public static final ObjectMapper mapper = new ObjectMapper();

    static Object decode(String json, Class valueType) throws IOException {
        return mapper.readValue(json, valueType);
    }

    static String encode(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }
}
