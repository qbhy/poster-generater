package com.qbhy.poster.kernal.smms;

import com.qbhy.poster.contracts.Result;
import com.qbhy.poster.kernal.JsonAble;
import lombok.Data;

import java.util.Map;

@Data
public class SmmsUploadResult extends JsonAble implements Result {
    private String code = Result.SUCCESSFUL;
    private String msg = null;
    private Map<String, String> data;

    public boolean isSuccessful() {
        return code.equals(Result.SUCCESSFUL);
    }

    /**
     * 获取 data 里面的字段
     *
     * @param name
     *
     * @return String
     */
    public String get(String name) {
        return data.get(name);
    }
}
