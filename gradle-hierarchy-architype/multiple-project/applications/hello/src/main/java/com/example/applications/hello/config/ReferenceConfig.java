package com.example.applications.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.businesses.greeting.BusinessGreetingScanner;
import com.example.cores.hello.CoresHelloPersistenceScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/01
 */
@Configuration
@Import(value = {
        BusinessGreetingScanner.class,
        CoresHelloPersistenceScanner.class
})
public class ReferenceConfig {

}
