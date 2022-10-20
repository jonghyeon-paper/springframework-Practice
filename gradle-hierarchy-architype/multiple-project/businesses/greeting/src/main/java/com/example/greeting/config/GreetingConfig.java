package com.example.greeting.config;

import org.springframework.context.annotation.ComponentScan;

import com.example.greeting.BusinessGreetingMarker;

@ComponentScan(basePackageClasses = BusinessGreetingMarker.class)
public class GreetingConfig {

}
