package com.luckyowl.test.beans.factory.config;

import com.luckyowl.test.beans.PropertyValues;

/**
 * @description  BeanDefinition实例用于保存<b>bean信息</b>
 * <p>
 * 包括：
 * 1. class类型、构造参数、属性值
 * 2. 是否为单例
 * 等。
 * </p>
 *
 * @author yuanjiahao03
 * @date 2024/6/6
 **/
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null? propertyValues : new PropertyValues();
    }

    public Class getBeanClass(){
        return beanClass;
    }

    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues(){
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues){
        this.propertyValues = propertyValues;
    }
}
