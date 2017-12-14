package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    private final DemoService demoService;

    @Autowired
    public DemoController( final DemoService demoService) {

        this.demoService = demoService;
    }

    @GetMapping("/fetchResponse")
    public String fetchResponse() {
        return demoService.fetchResponse()
                .map(DemoResponse::getName)
                .orElse("Sorry, I couldn't fetch the weather for you :(");
    }
}
