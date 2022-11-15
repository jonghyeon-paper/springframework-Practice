package com.example.applications.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.hello.CoresHelloPersistenceScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Configuration
@Import(value = {
        CoresHelloPersistenceScanner.class
})
public class ReferenceConfig {

}
