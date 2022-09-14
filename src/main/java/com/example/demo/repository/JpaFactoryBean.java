package com.example.demo.repository;

import com.example.demo.support.JpaProxyInvocation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@Component
public class JpaFactoryBean implements FactoryBean<UserRepository> {
    @Autowired
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Autowired
    EntityManager entityManager;

    @Override
    public UserRepository getObject() throws Exception {
        JpaProxyInvocation handler = new JpaProxyInvocation(localContainerEntityManagerFactoryBean, entityManager);
        UserRepository userRepository = (UserRepository) Proxy.newProxyInstance(UserRepository.class.getClassLoader(),
                new Class[]{UserRepository.class},
                handler);
        return userRepository;
    }

    @Override
    public Class<?> getObjectType() {
        return UserRepository.class;
    }
}
