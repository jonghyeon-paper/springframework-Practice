package com.example.applications.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.hello.CoresHelloPersistenceScanner;
import com.example.greeting.config.GreetingConfig;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/01
 */
@Configuration
@Import(value = {
        GreetingConfig.class,
        CoresHelloPersistenceScanner.class
})
public class ReferenceConfig {

}
