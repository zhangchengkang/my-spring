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

    /**
     * 1、刷新上下文
     * 2、初始化BeanFactory，获取bean工厂，这里会解析XML文件
     * 3、对BeanFactory的各种功能进行填充，如注解
     * 4、激活各种BeanFactory处理器
     * 5、注册拦截Bean创建的Bean处理器
     * 6、初始化上下文中的资源文件
     * 7、初始化上下文事件广播器
     * 8、给子类扩展初始化其他Bean
     * 9、在所有bean中查找listener bean并注册
     * 10、实例化所有非惰性加载的bean
     * 11、结束：广播事件，这里面对url做了映射。
     * <p>
     * 我们简化处理重要的步骤：解析XML，注册bean，实例化bean，保存URL映射关系
     *
     * @throws Exception
     */
    public void refresh() throws Exception {
        
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
