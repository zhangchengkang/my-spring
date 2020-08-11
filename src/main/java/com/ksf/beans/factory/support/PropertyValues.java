package com.ksf.beans.factory.support;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangchengkang
 * @Date: 2020/7/10 14:25
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        //TODO:这里可以对于重复propertyName进行判断，直接用list没法做到
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueList;
    }
}
