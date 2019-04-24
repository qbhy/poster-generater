package com.qbhy.poster.controllers;

import com.qbhy.poster.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private Config config;

    @RequestMapping("/")
    String home(){
        return "hello, poster";
    }
}
