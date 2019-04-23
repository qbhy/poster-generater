package com.qbhy.poster.controllers;

import com.qbhy.poster.drawable.Poster;
import com.qbhy.poster.kernal.Smms.SmmsUploadResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosterController {
    @RequestMapping(method = RequestMethod.POST, path = "/poster")
    SmmsUploadResult drawAndUpload(@RequestBody Poster poster) throws Exception {
        return poster.drawAndUpload();
    }
}
