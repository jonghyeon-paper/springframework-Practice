package com.example.applications.hello.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class GreetingResponse {

    private String helloMessage;
    private ZonedDateTime returnTime;
}
