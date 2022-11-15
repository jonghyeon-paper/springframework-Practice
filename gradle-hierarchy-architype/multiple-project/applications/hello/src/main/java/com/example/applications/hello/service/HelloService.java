package com.example.applications.hello.service;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.example.applications.hello.model.GreetingResponse;
import com.example.businesses.greeting.service.GreetingService;

import lombok.RequiredArgsConstructor;

/**
 * HelloService
 * 
 * created by jonghyeon on 2022/11/01
 */
@Service
@RequiredArgsConstructor
public class HelloService {

    private final GreetingService greetingService;

    /**
     * getHelloMessage
     * 
     * @return
     */
    public GreetingResponse getHelloMessage() {
        GreetingResponse responseData = new GreetingResponse();
        responseData.setHelloMessage(greetingService.getGreetingMessage("hello world", "hello"));
        responseData.setReturnTime(ZonedDateTime.now());
        return responseData;
    }
}
