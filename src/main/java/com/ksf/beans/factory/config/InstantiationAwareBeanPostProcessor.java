package com.ksf.beans.factory.config;

import com.ksf.beans.factory.support.PropertyValues;

import java.beans.PropertyDescriptor;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 15:34
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    //实例化(Instantiation): 要生成对象, 对象还未生成.
    //初始化(Initialization): 对象已经生成, 赋值操作.

    /**
     * 自身方法，是最先执行的方法，它在目标对象实例化之前调用，该方法的返回值类型是Object，我们可以返回任何类型的值
     * 由于这个时候目标对象还未实例化，所以这个返回值可以用来代替原本该生成的目标对象的实例(比如代理对象)。
     * 如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，其它方法不再调用
     * @param beanClass
     * @param beanName
     * @return
     * @throws Exception
     */
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws Exception {
        return null;
    }

    /**
     * 在目标对象实例化之后调用，这个时候对象已经被实例化，但是该实例的属性还未被设置，都是null。
     * 如果该方法返回false,并且不需要check，那么postProcessPropertyValues就会被忽略不执行
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws Exception {
        return true;
    }

    default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws Exception {
        return null;
    }

    /**
     * 对属性值进行修改，可以在该方法内对属性值进行修改
     * 如果postProcessAfterInstantiation方法返回false，该方法可能不会被调用。
     *
     * @param pvs
     * @param pds
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    default PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws Exception {
        return pvs;
    }
}
