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
            Result result = poster.drawAndUpload();
            if (result.isSuccessful()) {
                System.out.println(poster.key());
                data.save(poster.key(), result);
            }
            return result;
        } catch (Exception e) {
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
