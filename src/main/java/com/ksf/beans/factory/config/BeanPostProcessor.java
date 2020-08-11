package com.ksf.beans.factory.config;

import com.ksf.beans.factory.BeanFactory;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 14:30
 */
public interface BeanPostProcessor {

    //为在Bean的初始化前提供回调入口
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    //为在Bean的初始化之后提供回调入口
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
