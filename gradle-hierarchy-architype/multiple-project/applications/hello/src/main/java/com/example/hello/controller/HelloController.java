package com.example.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.config.SomethingConfig;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        SomethingConfig somethingConfig = new SomethingConfig();
        somethingConfig.initConfig();
        return "Greetings from Spring Boot!";
    }
}
