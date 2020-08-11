package com.ksf.beans.factory;

import com.ksf.beans.factory.config.BeanPostProcessor;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 14:28
 */
public interface ConfigurableBeanFactory extends BeanFactory{

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
