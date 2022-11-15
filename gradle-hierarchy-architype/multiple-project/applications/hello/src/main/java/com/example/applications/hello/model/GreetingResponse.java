package com.example.applications.hello.model;

import java.time.ZonedDateTime;

import lombok.Data;

/**
 * GreetingResponse
 * 
 * created by jonghyeon on 2022/11/14
 */
@Data
public class GreetingResponse {

    private String helloMessage;
    private ZonedDateTime returnTime;
}
