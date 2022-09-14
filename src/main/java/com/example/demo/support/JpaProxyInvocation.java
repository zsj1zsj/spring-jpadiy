package com.example.demo.support;

import lombok.AllArgsConstructor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class JpaProxyInvocation implements InvocationHandler {
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
    EntityManager entityManager;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleJpaSupport support = new SimpleJpaSupport(localContainerEntityManagerFactoryBean, entityManager);
        Object param = args[0];
        Object result;
        if(method.getName()=="findById") {
            result = support.findById(param);
        }else{
            result = support.save(param);
        }
        return result;
    }
}
