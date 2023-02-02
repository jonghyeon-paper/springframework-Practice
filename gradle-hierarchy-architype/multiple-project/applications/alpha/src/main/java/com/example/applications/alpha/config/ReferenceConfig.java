package com.example.applications.alpha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.alpha.CoresAlphaPersistenceScanner;

/**
 * ReferenceConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Import(value = {
        CoresAlphaPersistenceScanner.class
})
@Configuration
public class ReferenceConfig {

}
