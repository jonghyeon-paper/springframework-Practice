package com.example.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.greeting.config.GreetingConfig;

@Configuration
@Import(value = GreetingConfig.class)
public class ReferenceConfig {

}
