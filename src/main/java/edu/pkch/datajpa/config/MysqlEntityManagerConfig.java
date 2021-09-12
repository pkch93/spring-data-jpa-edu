package edu.pkch.datajpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import static edu.pkch.datajpa.config.MysqlEntityManagerConfig.*;

@Configuration
@EnableJpaRepositories(
        basePackages = MYSQL_ENTITY_BASE_PACKAGES,
        entityManagerFactoryRef = MYSQL_ENTITY_MANAGER_FACTORY_NAME,
        transactionManagerRef = MYSQL_TRANSACTION_MANAGER_NAME
)
public class MysqlEntityManagerConfig {
    public static final String MYSQL_ENTITY_BASE_PACKAGES = "edu.pkch.datajpa.mysql";
    public static final String MYSQL_ENTITY_MANAGER_FACTORY_NAME = "mysqlEntityManagerFactory";
    public static final String MYSQL_TRANSACTION_MANAGER_NAME = "mysqlTransactionManager";
    public static final String MYSQL_PERSISTENCE_UNIT_NAME = "default";

    @Bean(MYSQL_ENTITY_MANAGER_FACTORY_NAME)
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                            HikariDataSource mysqlDataSource) {
        return entityManagerFactoryBuilder
                .dataSource(mysqlDataSource)
                .packages(MYSQL_ENTITY_BASE_PACKAGES)
                .persistenceUnit(MYSQL_PERSISTENCE_UNIT_NAME)
                .build();
    }

    @Bean(MYSQL_TRANSACTION_MANAGER_NAME)
    @Primary
    public JpaTransactionManager mysqlTransactionManager(LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
        return new JpaTransactionManager(mysqlEntityManagerFactory.getObject());
    }
}
