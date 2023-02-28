package com.example.applications.alpha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * WebConfig
 * 
 * created by jonghyeon on 2022/11/14
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // if you need MVC config settings, add them here

    /**
     * customObjectMapper:
     * <ul>
     * <li>refer to {@link org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration}.
     * </ul>
     * @param builder
     * @return
     */
    @Bean
    public ObjectMapper customObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.createXmlMapper(false)
                .modulesToInstall(new JavaTimeModule())
                .build();
    }
}
