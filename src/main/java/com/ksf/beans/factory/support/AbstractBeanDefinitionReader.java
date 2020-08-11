package com.ksf.beans.factory.support;

import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 16:00
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;


    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
