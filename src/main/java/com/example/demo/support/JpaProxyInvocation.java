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
    Class clazz;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleJpaSupport support = new SimpleJpaSupport(localContainerEntityManagerFactoryBean, entityManager,clazz);
        Object param = args[0];
        Object result;
        Method jpaMethod = support.getClass().getMethod(method.getName(), method.getParameterTypes());
        System.out.println(jpaMethod.getName());
        result = jpaMethod.invoke(support,args);
        return result;
    }
}
