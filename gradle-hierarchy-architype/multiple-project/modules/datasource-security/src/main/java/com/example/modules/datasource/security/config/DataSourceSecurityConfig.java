package com.example.modules.datasource.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * DataSourceSecurityConfig
 * 
 * @author _sCream
 */
@Configuration
public class DataSourceSecurityConfig {

    /**
     * dataSourceSecurity
     * 
     * @return
     */
    @Bean("dataSourceSample")
    public DataSource dataSourceSecurity() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScripts("classpath:hsqldb/ddl-create.sql", "classpath:hsqldb/dml-insert.sql")
                .build();
        return database;
    }
}
