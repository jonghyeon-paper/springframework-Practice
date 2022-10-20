package com.example.hello.service.model;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HelloResponse {

    String helloMessage;
    ZonedDateTime returnTime;
}
