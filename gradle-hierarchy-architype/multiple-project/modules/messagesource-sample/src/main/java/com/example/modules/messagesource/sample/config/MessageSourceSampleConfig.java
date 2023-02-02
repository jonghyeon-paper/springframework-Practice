package com.example.modules.messagesource.sample.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * DataSourceSampleConfig
 * 
 * created by jonghyeon on 2023/02/02
 */
@Configuration
public class MessageSourceSampleConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/sample");
        messageSource.setDefaultEncoding("UTF-8");
        //messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }
}
