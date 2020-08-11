package com.ksf.beans.factory.config;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 14:23
 */
public interface BeanDefinition {

    void setBeanClassName(String beanClassName);

    String getBeanClassName();

    void setLazyInit(boolean lazyInit);

    boolean isLazyInit();

    void setFactoryBeanName(String factoryBeanName);

    String getFactoryBeanName();

    String getParentName();

}
