package com.qbhy.poster.contracts;

import java.util.Map;

public interface Data {
    public boolean save(String key, Result result);

    public Result find(String key);

    public boolean delete(String key);

    public boolean has(String key);

    public Map<String, Result> list();

}
