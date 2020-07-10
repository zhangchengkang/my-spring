package com.ksf.beans.reader;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 15:57
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
