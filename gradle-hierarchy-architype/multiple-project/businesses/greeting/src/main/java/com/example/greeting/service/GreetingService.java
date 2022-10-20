package com.example.greeting.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreetingMessage(String greetingMessage, String appName) {
        return new StringBuilder().append("'").append(greetingMessage).append("!'").append("\n")
                .append("this message return at greeting service.").append("\n")
                .append("this message called by hello app.")
                .toString();
    }
}
