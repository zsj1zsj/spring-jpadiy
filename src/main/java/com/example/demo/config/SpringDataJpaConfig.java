package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableJpaRepositories("com.example.demo.repository")
@ComponentScan(basePackages = {"com.example.demo.core"})
@EnableTransactionManagement
public class SpringDataJpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setUrl(
                "jdbc:sqlite://Users/lynn/Desktop/lynndb.db");

        return dataSource;
    }

    @Bean
    public EntityManager entityManager() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = entityManagerFactory();
        return localContainerEntityManagerFactoryBean.createNativeEntityManager(null);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.demo.entities");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(additionalProperties());
        return factory;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);

        return txManager;
    }

}
