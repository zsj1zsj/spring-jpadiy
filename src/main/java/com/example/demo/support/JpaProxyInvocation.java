package com.example.demo.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JpaProxyInvocation implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleJpaSupport support = new SimpleJpaSupport();
        Object param = args[0];
        Object result = support.findById(param);
        return result;
    }
}
