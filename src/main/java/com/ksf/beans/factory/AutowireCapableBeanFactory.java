package com.ksf.beans.factory;

import com.ksf.BeanReference;
import com.ksf.beans.factory.config.BeanPostProcessor;
import com.ksf.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.factory.support.PropertyValue;
import com.ksf.beans.factory.support.PropertyValues;
import com.ksf.beans.factory.support.RootBeanDefinition;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 15:00
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private final ConcurrentMap<Class<?>, PropertyDescriptor[]> filteredPropertyDescriptorsCache = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return null;
    }

    @Override
    protected String transformedBeanName(String name) {
        return null;
    }

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd) throws Exception {
        Object bean = resolveBeforeInstantiation(beanName, mbd);
        if (bean != null) {
            return bean;
        }

        return doCreateBean(beanName, mbd);
    }

    private Object doCreateBean(String beanName, RootBeanDefinition mbd) throws Exception {
        Class<?> clazz = Class.forName(mbd.getBeanClassName());
        Object instance = clazz.newInstance();
        populateBean(instance, mbd, beanName, clazz);
        return initializeBean(instance, beanName, mbd);
    }

    private void populateBean(Object instance, RootBeanDefinition mbd, String beanName, Class<?> clazz) throws Exception {
        boolean continueWithPropertyPopulation = true;
        if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
            for (BeanPostProcessor bp : getBeanPostProcessors()) {
                if (bp instanceof InstantiationAwareBeanPostProcessor) {
                    InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                    if (!ibp.postProcessAfterInstantiation(instance, beanName)) {
                        continueWithPropertyPopulation = false;
                        break;
                    }
                }
            }
        }

        if (!continueWithPropertyPopulation) {
            return;
        }

        PropertyValues propertyValues = mbd.getPropertyValues();
        for (BeanPostProcessor bp : getBeanPostProcessors()) {
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                PropertyValues pvsToUse = ibp.postProcessProperties(propertyValues, instance, beanName);
                if (pvsToUse == null) {
                    pvsToUse = ibp.postProcessPropertyValues(propertyValues, null, instance, beanName);
                    if (pvsToUse == null) {
                        return;
                    }
                }
                propertyValues = pvsToUse;
            }
        }

        if (propertyValues != null) {
            applyPropertyValues(instance, propertyValues);
        }
    }

    private void applyPropertyValues(Object instance, PropertyValues propertyValues) throws Exception {
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Field declaredField = instance.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(instance, value);
        }
    }

    private Object initializeBean(Object instance, String beanName, RootBeanDefinition mbd) throws Exception {
        if (mbd == null || !mbd.isSynthetic()) {
            instance = applyBeanPostProcessorsBeforeInitialization(instance, beanName);
        }

        if (mbd == null || !mbd.isSynthetic()) {
            instance = applyBeanPostProcessorsAfterInitialization(instance, beanName);
        }

        return instance;
    }


    protected Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd) throws Exception {
        Object bean = null;
        //判断是否已执行,防止重复执行
        if (!Boolean.FALSE.equals(mbd.beforeInstantiationResolved)) {
            //class.forName  加载连接未初始化
            Class<?> targetType = Class.forName(mbd.getBeanClassName());
            bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
            if (bean != null) {
                bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
            }
            mbd.beforeInstantiationResolved = (bean != null);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) throws Exception {
        for (BeanPostProcessor bp : getBeanPostProcessors()) {
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                Object result = ibp.postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws Exception {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws Exception {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
