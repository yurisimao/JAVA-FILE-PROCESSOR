package com.br.yurisimao.primarydb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.br.yurisimao.primarydb.repository"})
@EntityScan(basePackages = "com.br.yurisimao.primarydb.entity")
public class PrimaryDatabaseConfig {

    @Value("${primary.datasource.url}")
    private String url;

    @Value("${primary.datasource.username}")
    private String username;

    @Value("${primary.datasource.password}")
    private String password;

    @Value("${primary.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder, @Qualifier("dataSource") final DataSource dataSource) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return builder.dataSource(dataSource).packages("com.br.yurisimao.primarydb.entity")
                .properties(properties).build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
