package com.example.applications.beta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.beta.CoresBetaPersistenceScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Import(value = {
        CoresBetaPersistenceScanner.class
})
@Configuration
public class ReferenceConfig {

}
