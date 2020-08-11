package com.ksf.context;

import com.ksf.beans.factory.AbstractBeanFactory;
import com.ksf.beans.factory.AutowireCapableBeanFactory;

import java.io.InputStream;

/**
 * @Author: zhangchengkang
 * @Date: 2020/8/6 9:32
 */
public class JsonApplicationContext extends AbstractApplicationContext {

    private String fileName;

    public JsonApplicationContext(String fileName) throws Exception {
        this(fileName, new AutowireCapableBeanFactory());
    }


    public JsonApplicationContext(String fileName, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.fileName = fileName;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

    }
}
