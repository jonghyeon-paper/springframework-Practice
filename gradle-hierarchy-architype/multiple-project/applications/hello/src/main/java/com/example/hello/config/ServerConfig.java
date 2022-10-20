package com.example.hello.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ServerConfig {

    @Profile("local")
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> localServer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(9011);
            }
        };
    }

    @Profile("beta")
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> betaServer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(9021);
            }
        };
    }

    @Profile("prod")
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> productionServer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(9031);
            }
        };
    }
}
