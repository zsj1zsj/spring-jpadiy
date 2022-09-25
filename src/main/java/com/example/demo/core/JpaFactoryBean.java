package com.example.demo.core;

import com.example.demo.support.JpaProxyInvocation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

//@Component
public class JpaFactoryBean implements FactoryBean {
    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Autowired
    private EntityManager entityManager;

    private Class<?> repositoryInterface;

    public JpaFactoryBean(Class<?> repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public Object getObject() throws Exception {
        // 拿到父接口
        ParameterizedType parameterizedType = (ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        // 拿到接口的范型
        Type type = parameterizedType.getActualTypeArguments()[0];
        Class clazz = Class.forName(type.getTypeName());

        JpaProxyInvocation handler = new JpaProxyInvocation(localContainerEntityManagerFactoryBean, entityManager, clazz);

        return Proxy.newProxyInstance(repositoryInterface.getClassLoader(),
                new Class[]{repositoryInterface},
                handler);
    }

    @Override
    public Class<?> getObjectType() {
        return repositoryInterface;
    }
}
