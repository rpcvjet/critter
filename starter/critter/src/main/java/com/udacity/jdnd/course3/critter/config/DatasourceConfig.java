package com.udacity.jdnd.course3.critter.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfig {
    // @Primary
    // @ConfigurationProperties(prefix = "spring.datasource")
    // public DataSource getDataSource() {
    // DataSourceBuilder dsb = DataSourceBuilder.create();
    // dsb.url("jdbc:mysql://localhost:3306/critter");
    // return dsb.build();
    // }
}
