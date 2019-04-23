package com.qbhy.poster.kernal.Smms;

import com.qbhy.poster.kernal.JsonAble;

import java.util.Map;

public class SmmsUploadResult extends JsonAble {
    private String code;
    private String msg;
    private Map<String, String> data;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public boolean isSuccessful() {
        return this.getCode().equals("success");
    }

    /**
     * 获取 data 里面的字段
     * @param name
     * @return String
     */
    public String get(String name) {
        return data.get(name);
    }
}
