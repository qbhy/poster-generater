package com.qbhy.poster.kernal;

import com.qbhy.poster.contracts.Data;
import com.qbhy.poster.contracts.Result;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapData extends JsonAble implements Data {

    private Map<String, Result> map = new HashMap<>();

    @Override
    public boolean save(String key, Result result) {
        map.put(key, result);
        return true;
    }

    @Override
    public Result find(String key) {
        return map.get(key);
    }

    @Override
    public boolean delete(String key) {
        if (has(key)) {
            map.remove(key);
            return true;
        }

        return false;
    }

    @Override
    public Map<String, Result> list() {
        return map;
    }

    @Override
    public boolean has(String key) {
        return map.get(key) != null;
    }
}
