package com.example.demo.core;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class JpaClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public JpaClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();

            String beanClassName = beanDefinition.getBeanClassName();
            // 设置构造函数的值
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

            // 扫到接口后， 把beanClass替换成实现类。
            beanDefinition.setBeanClass(JpaFactoryBean.class);
        }
        return beanDefinitionHolders;
    }
}
