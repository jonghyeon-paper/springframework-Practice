package com.example.applications.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.sample.CoresSamplePersistenceScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Import(value = {
        CoresSamplePersistenceScanner.class
})
@Configuration
public class ReferenceConfig {

}
