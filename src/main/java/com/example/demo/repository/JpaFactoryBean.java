package com.example.demo.repository;

import com.example.demo.support.JpaProxyInvocation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class JpaFactoryBean implements FactoryBean<UserRepository> {
    @Override
    public UserRepository getObject() throws Exception {
        JpaProxyInvocation handler = new JpaProxyInvocation();
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
