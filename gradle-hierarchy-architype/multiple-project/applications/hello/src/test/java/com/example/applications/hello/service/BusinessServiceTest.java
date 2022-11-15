package com.example.applications.hello.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * BusinessServiceTest
 * 
 * created by jonghyeon on 2022/11/14
 */
@SpringBootTest
public class BusinessServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void serviceTest() {
        System.out.println(helloService.getHelloMessage());
    }
}
