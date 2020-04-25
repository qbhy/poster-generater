package com.qbhy.poster.controllers;

import com.qbhy.poster.kernal.BlankResult;
import com.qbhy.poster.kernal.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import com.qbhy.poster.contracts.Result;

import java.util.Map;

@RestController
public class IndexController {

    @RequestMapping("/")
    String home() {
        return "hello, poster";
    }

    /**
     * 网络图片预加载
     *
     * @param request json 请求实例
     * @return
     * @throws Throwable
     */
    @PostMapping("image/load")
    Result loadImage(@RequestBody Map<String, Object> request) throws Throwable {

        System.out.println(request);
        Object update = request.get("update");
        ResourceUtils.getImageFromUrl((String) request.get("url"), update != null ? (Boolean) update : false);

        return new BlankResult(Result.SUCCESSFUL, "successful!");
    }
}
