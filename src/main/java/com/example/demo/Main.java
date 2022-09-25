package com.example.demo;

import com.example.demo.config.SpringDataJpaConfig;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);

        UserRepository repository = (UserRepository) ioc.getBean("userRepository");
        User u1 = new User();
        u1.setName("Sara");
        u1.setId(11L);
        repository.save(u1);

        System.out.println("users: " + repository.findById(11L));

    }
}
