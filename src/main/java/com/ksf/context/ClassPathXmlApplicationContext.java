package com.ksf.context;

import com.ksf.beans.factory.config.BeanDefinition;
import com.ksf.beans.factory.AbstractBeanFactory;
import com.ksf.beans.factory.AutowireCapableBeanFactory;
import com.ksf.beans.io.ResourceLoader;
import com.ksf.beans.factory.support.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 16:57
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    private String configLocation;


    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
