package edu.pkch.datajpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import static edu.pkch.datajpa.config.PostgresEntityManagerConfig.*;

@Configuration
@EnableJpaRepositories(
        basePackages = POSTGRES_ENTITY_BASE_PACKAGES,
        entityManagerFactoryRef = POSTGRES_ENTITY_MANAGER_FACTORY_NAME ,
        transactionManagerRef = POSTGRES_TRANSACTION_MANAGER_NAME
)
public class PostgresEntityManagerConfig {
    public static final String POSTGRES_ENTITY_BASE_PACKAGES = "edu.pkch.datajpa.postgresql";
    public static final String POSTGRES_ENTITY_MANAGER_FACTORY_NAME = "postgresEntityManagerFactory";
    public static final String POSTGRES_TRANSACTION_MANAGER_NAME = "postgresTransactionManager";
    public static final String POSTGRES_PERSISTENCE_UNIT_NAME = "legacy";

    @Bean(POSTGRES_ENTITY_MANAGER_FACTORY_NAME)
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                               @Qualifier("postgresDataSource") HikariDataSource postgresDataSource) {
        return entityManagerFactoryBuilder
                .dataSource(postgresDataSource)
                .packages(POSTGRES_ENTITY_BASE_PACKAGES)
                .persistenceUnit(POSTGRES_PERSISTENCE_UNIT_NAME)
                .build();
    }

    @Bean(POSTGRES_TRANSACTION_MANAGER_NAME)
    public JpaTransactionManager postgresTransactionManager(
            @Qualifier(POSTGRES_ENTITY_MANAGER_FACTORY_NAME) LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory
    ) {
        return new JpaTransactionManager(postgresEntityManagerFactory.getObject());
    }
}
