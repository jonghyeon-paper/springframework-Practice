package com.example.applications.gamma.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * SpringConfig
 * 
 * @author _sCream
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
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:*.yml");

        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;

    }
}
