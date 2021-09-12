package edu.pkch.datajpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("mysql.datasource.config")
    public HikariConfig mysqlHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public HikariDataSource mysqlDataSource(@Qualifier("mysqlHikariConfig") HikariConfig mysqlHikariConfig) {
        return new HikariDataSource(mysqlHikariConfig);
    }

    @Bean
    @ConfigurationProperties("postgres.datasource.config")
    public HikariConfig postgresHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public HikariDataSource postgresDataSource(@Qualifier("postgresHikariConfig") HikariConfig postgresHikariConfig) {
        return new HikariDataSource(postgresHikariConfig);
    }
}
