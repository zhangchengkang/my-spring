package com.ksf.context;

import com.ksf.beans.factory.AbstractBeanFactory;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 16:41
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {

    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
