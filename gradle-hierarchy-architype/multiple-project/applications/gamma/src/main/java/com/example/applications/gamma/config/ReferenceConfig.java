package com.example.applications.gamma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.cores.gamma.CoresGammaPersistenceScanner;

/**
 * ReferenceConfig
 * 
 * @author _sCream
 */
@Import(value = {
        CoresGammaPersistenceScanner.class
})
@Configuration
public class ReferenceConfig {

}
