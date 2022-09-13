package com.example.demo;

import com.example.demo.config.SpringDataJpaConfig;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);

        UserRepository repository = (UserRepository) ioc.getBean("userRepository");
        System.out.println("users: "+repository.findAll());
    }
}
