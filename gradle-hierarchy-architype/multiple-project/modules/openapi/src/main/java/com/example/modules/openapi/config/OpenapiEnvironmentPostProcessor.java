package com.example.modules.openapi.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * OpenapiEnvironmentPostProcessor:
 * <ul>
 * <li>see: classpath:META-INF/spring.factories</li>
 * <li>see: classpath:openapi.yml</li>
 * </ul>
 * 
 * created by jonghyeon on 2023/04/04
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class OpenapiEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String RESOURCE_PATH = "classpath:openapi-{activeProfile}.yml";

    /* (non-Javadoc)
     * @see org.springframework.boot.env.EnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment, org.springframework.boot.SpringApplication)
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // profile level : prod > stage > dev > local
        String profile = null;
        if (Arrays.asList(environment.getActiveProfiles()).contains("local")) {
            profile = "local";
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            profile = "dev";
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("stage")) {
            profile = "stage";
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            profile = "prod";
        }

        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver().getResources(RESOURCE_PATH.replace("{activeProfile}", profile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(resources);

        environment.getPropertySources().addLast(new PropertiesPropertySource("openapi.yml", yaml.getObject()));
    }
}
