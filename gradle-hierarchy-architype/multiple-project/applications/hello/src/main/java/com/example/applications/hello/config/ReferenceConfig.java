package com.example.applications.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.businesses.greeting.BusinessGreetingScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/01
 */
@Import(value = {
        BusinessGreetingScanner.class
})
@Configuration
public class ReferenceConfig {

}
