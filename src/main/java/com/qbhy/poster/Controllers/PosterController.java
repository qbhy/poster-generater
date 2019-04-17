package com.qbhy.poster.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbhy.poster.PosterConfig.Poster;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosterController {

    @RequestMapping(method = RequestMethod.POST, path = "/poster")
    String create(@RequestBody Poster poster) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        

        System.out.println(mapper.writeValueAsString(poster));
        return "hello, poster generator";
    }
}
