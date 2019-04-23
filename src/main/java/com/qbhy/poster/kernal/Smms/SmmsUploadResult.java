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
        return this.code.equals("success");
    }
}
