package com.example.modules.datasource.memory.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * DataSourceMemoryConfig
 * 
 * created by jonghyeon on 2022/11/01
 */
@Configuration
public class DataSourceMemoryConfig {

    /**
     * dataSourceMemory
     * 
     * @return
     */
    @Bean("dataSourceMemory")
    public DataSource dataSourceMemory() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScripts("classpath:hsqldb/create-db.sql", "classpath:hsqldb/insert-db.sql")
                .build();
        return database;
    }
}
