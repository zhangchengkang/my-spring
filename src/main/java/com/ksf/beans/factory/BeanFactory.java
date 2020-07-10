package com.ksf.beans.factory;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 14:20
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
