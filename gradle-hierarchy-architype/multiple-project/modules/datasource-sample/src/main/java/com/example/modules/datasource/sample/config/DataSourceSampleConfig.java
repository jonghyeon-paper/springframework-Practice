package com.example.modules.datasource.sample.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * DataSourceSampleConfig
 * 
 * created by jonghyeon on 2022/11/01
 */
@Configuration
public class DataSourceSampleConfig {

    /**
     * dataSourceMemory
     * 
     * @return
     */
    @Bean("dataSourceSample")
    public DataSource dataSourceSample() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScripts("classpath:hsqldb/ddl-create.sql", "classpath:hsqldb/dml-insert.sql")
                .build();
        return database;
    }
}
