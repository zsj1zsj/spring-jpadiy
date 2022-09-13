package com.example.demo;

import com.example.demo.config.SpringDataJpaConfig;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);

        UserRepository repository = (UserRepository) ioc.getBean("jpaFactoryBean");
        System.out.println("users: " + repository.findById(1L));

    }
}
