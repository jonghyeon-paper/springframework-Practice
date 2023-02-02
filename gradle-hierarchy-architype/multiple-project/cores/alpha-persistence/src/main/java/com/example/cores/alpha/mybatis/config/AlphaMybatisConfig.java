package com.example.cores.alpha.mybatis.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cores.alpha.jpa.config.AlphaJpaConfig;
import com.example.cores.alpha.mybatis.mapper.MybatisMapperLocationMarker;

import lombok.RequiredArgsConstructor;

/**
 * AlphaMybatisConfig
 * <br>Transactions are handled in {@link AlphaJpaConfig#transactionManager(org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean)}.
 *
 * created by jonghyeon on 2022/11/01
 */
@Configuration
@MapperScan(basePackageClasses = { MybatisMapperLocationMarker.class })
@RequiredArgsConstructor
public class AlphaMybatisConfig {

    private final ApplicationContext applicationContext;

    /**
     * sqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceSample") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties());
        //sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:alpha/mybatis/config/configuration.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:alpha/mybatis/mapper/*.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("com.example.cores.hello.mybatis.model");
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * mybatisProperties
     *
     * @return
     */
    private Properties mybatisProperties() {
        Properties properties = new Properties();
        properties.setProperty("mapUnderscoreToCamelCase", "true");
        return properties;
    }

    /**
     * sqlSessionTemplate
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
