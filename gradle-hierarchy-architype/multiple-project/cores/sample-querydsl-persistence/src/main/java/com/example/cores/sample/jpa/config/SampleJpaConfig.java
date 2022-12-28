package com.example.cores.sample.jpa.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.cores.sample.CoresSamplePersistenceMarker;

/**
 * SampleJpaConfig
 *
 * created by jonghyeon on 2022/11/01
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = CoresSamplePersistenceMarker.class, entityManagerFactoryRef = "localContainerEntityManagerFactoryBean")
public class SampleJpaConfig {

    /**
     * localContainerEntityManagerFactoryBean
     * 
     * @param dataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier("dataSourceSample") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(new String[] { "com.example.cores.sample.jpa" });
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    /**
     * hibernateProperties
     * 
     * @return
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.ddl-auto", "validate");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        //org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        return properties;
    }

    /**
     * transactionManager
     * 
     * @param localContainerEntityManagerFactoryBean
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean.getObject());
        return transactionManager;
    }

    /**
     * exceptionTranslation
     * 
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
