package com.example.applications.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.hello.model.GreetingResponse;
import com.example.applications.hello.service.HelloService;

import lombok.RequiredArgsConstructor;

/**
 * HelloController
 * 
 * created by jonghyeon on 2022/11/01
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    /**
     * getHelloMessage
     * 
     * @return
     */
    @GetMapping("/hello")
    public GreetingResponse getHelloMessage() {
        return helloService.getHelloMessage();
    }
}
