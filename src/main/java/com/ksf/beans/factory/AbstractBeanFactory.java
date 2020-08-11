package com.ksf.beans.factory;

import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.factory.config.BeanPostProcessor;
import com.ksf.beans.factory.support.DefaultSingletonBeanRegistry;
import com.ksf.beans.factory.support.RootBeanDefinition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 14:21
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry  implements ConfigurableBeanFactory {

    private final Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    private volatile boolean hasInstantiationAwareBeanPostProcessors;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    protected boolean hasInstantiationAwareBeanPostProcessors() {
        return this.hasInstantiationAwareBeanPostProcessors;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public Object getBean(String name) throws Exception {
        return doGetBean(name);
    }

    protected Object doGetBean(String name) throws Exception {
        String beanName = transformedBeanName(name);
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance == null) {
            return createBean(beanName,  getMergedLocalBeanDefinition(beanName));
        }

        return sharedInstance;
    }

    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) throws Exception {
        // 检查缓存中是否存在“已合并的 BeanDefinition”，若有直接返回即可
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        if (mbd != null && !mbd.stale) {
            return mbd;
        }

        return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
    }

    protected RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd) throws Exception {
        RootBeanDefinition mbd = null;
        synchronized (this.mergedBeanDefinitions) {
            if (bd.getParentName() == null) {
                if (bd.getParentName() == null) {
                    // bd.getParentName() == null，表明无父配置，这时直接将当前的 BeanDefinition 升级为 RootBeanDefinition
                    if (bd instanceof RootBeanDefinition) {
                        mbd = ((RootBeanDefinition) bd).cloneBeanDefinition();
                    } else {
                        mbd = new RootBeanDefinition(bd);
                    }
                } else {
                    //TODO  如果有父配置  则需要合并
                }
            }
            return mbd;
        }
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws Exception;

    protected abstract String transformedBeanName(String name);

    protected abstract Object createBean(String beanName, RootBeanDefinition mbd) throws Exception;
}
