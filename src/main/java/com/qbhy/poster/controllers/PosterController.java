package com.qbhy.poster.controllers;

import com.qbhy.poster.contracts.Data;
import com.qbhy.poster.contracts.Result;
import com.qbhy.poster.drawable.Poster;
import com.qbhy.poster.kernal.BlankResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosterController {

    @Autowired
    private Data data;

    /**
     * 画图并上传
     *
     * @param poster
     *
     * @return Result
     */
    @RequestMapping(method = RequestMethod.POST, path = "/poster")
    Result drawAndUpload(@RequestBody Poster poster) {
        try {
            // 从 数据中心获取结果，如果有，直接返回
            Result result = data.find(poster.key());
            if (result != null) {
                return result;
            }

            // 如果没有，则画图并上传后存储到数据中心
            result = poster.drawAndUpload();
            if (result.isSuccessful()) {
                data.save(poster.key(), result);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new BlankResult("error", e.getMessage());
        }
    }

    /**
     * 查询结果
     *
     * @param key
     *
     * @return Result
     */
    @RequestMapping(method = RequestMethod.GET, path = "/poster/{key}")
    Result find(@PathVariable String key) {
        Result result = data.find(key);
        if (result != null) {
            return result;
        }
        return new BlankResult("error", "result not found!");
    }

    /**
     * 删除结果
     *
     * @param key
     *
     * @return Result
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/poster/{key}")
    Result delete(@PathVariable String key) {
        if (data.delete(key)) {
            return new BlankResult(Result.SUCCESSFUL, null);
        }
        return new BlankResult("error", "result not found!");
    }


}
