package com.ksf.beans.factory.support;

import com.ksf.beans.factory.config.SingletonBeanRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/10 15:27
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /*** 存放的是单例 bean 的映射  bean name --> bean instance */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    /*** 存放的是创建单例 bean 的 factory bean name --> ObjectFactory */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

    /*** 存放的是早期的 bean bean name --> bean instance */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    /*** 已注册的 Bean 的名字的集合 */
    private final Set<String> registeredSingletons = new LinkedHashSet<>(256);

    /*** 正在创建中的单例 Bean 的名字的集合 */
    private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));


    private boolean singletonsCurrentlyInDestruction = false;


    @Override
    public void registerSingleton(String beanName, Object singletonObject) throws Exception {
        synchronized (singletonObjects) {
            Object oldObject = singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new Exception("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }

            addSingleton(beanName, singletonObject);
        }

    }

    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    private Object getSingleton(String beanName, boolean allowEarlyReference) {
        //三级获取bean 解决循环依赖   singletonObjects --> earlySingletonObjects --> singletonFactories
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (singletonObjects) {
                singletonObject = earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        earlySingletonObjects.put(beanName, singletonObject);
                        singletonFactories.remove(beanName);
                    }
                }
            }
        }

        return singletonObject;
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return singletonsCurrentlyInCreation.contains(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }
}
