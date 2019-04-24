package com.qbhy.poster.controllers;

import com.qbhy.poster.contracts.UploadResult;
import com.qbhy.poster.drawable.Poster;
import com.qbhy.poster.kernal.BlankUploadResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosterController {

    @RequestMapping(method = RequestMethod.POST, path = "/poster")
    UploadResult drawAndUpload(@RequestBody Poster poster) {
        try {
            return poster.drawAndUpload();
        } catch (Exception e) {
            return new BlankUploadResult("error", e.getMessage());
        }
    }

}
