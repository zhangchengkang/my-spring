package com.ksf.beans.factory.support;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 14:26
 */
public class PropertyValue {

    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
