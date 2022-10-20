package com.example.hello.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.greeting.service.GreetingService;
import com.example.hello.service.model.HelloResponse;

@Service
public class HelloService {

    @Autowired
    private GreetingService greetingService;

    public HelloResponse getHello() {
        HelloResponse responseData = new HelloResponse();
        responseData.setHelloMessage(greetingService.getGreetingMessage("hello", "hello"));
        responseData.setReturnTime(ZonedDateTime.now());
        return responseData;
    }
}
