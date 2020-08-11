package com.ksf.beans.factory.config;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/10 15:26
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject) throws Exception;

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);
}
