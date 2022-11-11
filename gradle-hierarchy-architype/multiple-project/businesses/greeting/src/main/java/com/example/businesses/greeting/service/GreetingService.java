package com.example.businesses.greeting.service;

import org.springframework.stereotype.Service;

/**
 * GreetingService
 * 
 * created by jonghyeon on 2022/11/01
 */
@Service
public class GreetingService {

    /**
     * getGreetingMessage
     * 
     * @param greetingMessage
     * @param appName
     * @return
     */
    public String getGreetingMessage(String greetingMessage, String appName) {
        return new StringBuilder().append("'").append(greetingMessage).append("!'").append("\n")
                .append("this message return at greeting service.").append("\n")
                .append("this message called by hello app.")
                .toString();
    }
}
