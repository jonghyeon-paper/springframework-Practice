package com.example.applications.gamma.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * ServerConfig
 * 
 * @author _sCream
 */
@Configuration
public class ServerConfig {

    /**
     * webServerFactoryCustomizer
     * 
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/gamma");
    }

    /**
     * localServer
     * 
     * @return
     */
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

    /**
     * betaServer
     * 
     * @return
     */
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

    /**
     * productionServer
     * 
     * @return
     */
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
