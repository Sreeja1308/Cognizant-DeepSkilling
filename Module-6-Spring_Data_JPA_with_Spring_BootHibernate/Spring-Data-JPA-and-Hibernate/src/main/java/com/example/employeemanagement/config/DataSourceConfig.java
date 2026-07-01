package com.example.employeemanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    // For multiple data sources (optional)
    // @Bean
    // @ConfigurationProperties(prefix = "secondary.datasource")
    // public DataSource secondaryDataSource() {
    //     return DataSourceBuilder.create().build();
    // }
}