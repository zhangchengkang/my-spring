package com.ksf.beans.factory;

import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.factory.support.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 11:43
 */
public class DefaultListableBeanFactory extends AutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    @Override
    public void removeBeanDefinition(String beanName) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

}
