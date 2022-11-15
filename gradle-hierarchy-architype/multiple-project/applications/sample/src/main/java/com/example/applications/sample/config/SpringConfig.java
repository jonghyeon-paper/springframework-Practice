package com.example.applications.sample.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * SpringConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Configuration
public class SpringConfig {

    /**
     * properties
     * 
     * @return
     * @throws IOException 
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer properties() throws IOException {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:*.yml");
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;

    }
}
