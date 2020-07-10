package com.ksf;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 15:16
 */
public class BeanReference {
    private String name;

    private Object bean;

    public BeanReference() {
    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
