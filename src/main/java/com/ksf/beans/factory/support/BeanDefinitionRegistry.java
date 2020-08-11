package com.ksf.beans.factory.support;

import com.ksf.beans.factory.config.BeanDefinition;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 11:45
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception;

    void removeBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

    int getBeanDefinitionCount();
}
