package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.service.HelloService;
import com.example.hello.service.model.HelloResponse;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    public HelloResponse index() {
        //return "Greetings from Spring Boot!";
        return helloService.getHello();
    }
}
