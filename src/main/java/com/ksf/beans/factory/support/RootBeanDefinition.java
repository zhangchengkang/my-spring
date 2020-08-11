package com.ksf.beans.factory.support;

import com.ksf.beans.factory.config.BeanDefinition;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 16:07
 */
public class RootBeanDefinition extends AbstractBeanDefinition {

    public volatile Boolean beforeInstantiationResolved;

    public volatile boolean stale;

    private boolean synthetic = false;

    public RootBeanDefinition() {
        super();
    }

    public RootBeanDefinition(BeanDefinition original) {
        super(original);
    }

    @Override
    public String getParentName() {
        return null;
    }

    @Override
    public RootBeanDefinition cloneBeanDefinition() {
        return new RootBeanDefinition();
    }

    public boolean isSynthetic() {
        return this.synthetic;
    }

}
