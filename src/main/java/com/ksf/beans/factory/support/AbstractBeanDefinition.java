package com.ksf.beans.factory.support;

import com.ksf.beans.factory.config.BeanDefinition;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 16:01
 */
public abstract class AbstractBeanDefinition implements BeanDefinition {

    private volatile String beanClass;

    private boolean lazyInit = false;

    private String factoryBeanName;

    private PropertyValues propertyValues;

    protected AbstractBeanDefinition(){};

    protected AbstractBeanDefinition(BeanDefinition original) {
        setBeanClassName(original.getBeanClassName());
        setLazyInit(original.isLazyInit());
        setFactoryBeanName(original.getFactoryBeanName());
    }

    public abstract AbstractBeanDefinition cloneBeanDefinition();

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return beanClass;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @Override
    public boolean isLazyInit() {
        return this.lazyInit;
    }

    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    @Override
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
