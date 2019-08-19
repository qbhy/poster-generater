package com.qbhy.poster.controllers;

import com.qbhy.poster.config.PosterConfig;
import com.qbhy.poster.config.UpYunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private UpYunConfig upYunConfig;

    @Autowired
    private PosterConfig posterConfig;

    @RequestMapping("/")
    String home() {
        return "hello, poster";
    }
}
