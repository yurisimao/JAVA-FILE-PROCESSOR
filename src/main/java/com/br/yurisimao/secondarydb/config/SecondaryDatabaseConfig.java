package com.br.yurisimao.secondarydb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "secondaryFactory",
        transactionManagerRef = "secondaryTransaction",
        basePackages = {"com.br.yurisimao.secondarydb.repository"})
@EntityScan(basePackages = "com.br.yurisimao.secondarydb.entity")
public class SecondaryDatabaseConfig {

    @Value("${secondary.datasource.url}")
    private String url;

    @Value("${secondary.datasource.username}")
    private String username;

    @Value("${secondary.datasource.password}")
    private String password;

    @Value("${secondary.datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "secondaryDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean(name = "secondaryFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder, @Qualifier("secondaryDataSource") final DataSource dataSource) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return builder.dataSource(dataSource).packages("com.br.yurisimao.secondarydb.entity")
                .properties(properties).build();
    }

    @Bean(name = "secondaryTransaction")
    public PlatformTransactionManager transactionManager(@Qualifier("secondaryFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
