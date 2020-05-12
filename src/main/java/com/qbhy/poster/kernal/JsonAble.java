package com.qbhy.poster.kernal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbhy.poster.contracts.Jsonable;

import java.io.IOException;

public abstract class JsonAble implements Jsonable {

    @Override
    public String toString() {
        String str = this.toJson();
        return str != null ? str : super.toString();
    }

    @Override
    public String toJson() {
        try {
            return Jsonable.encode(this);
        } catch (JsonProcessingException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
