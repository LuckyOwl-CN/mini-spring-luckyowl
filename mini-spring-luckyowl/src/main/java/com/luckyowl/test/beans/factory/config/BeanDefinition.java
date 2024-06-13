package com.luckyowl.test.beans.factory.config;

import com.luckyowl.test.beans.PropertyValues;

/**
 * @description  BeanDefinition实例用于保存<b>bean信息</b>
 * <p>
 * 包括：
 * 1. class类型、构造参数、属性值
 * 2. 是否为单例
 * 3. bean的scope
 * 等。
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class BeanDefinition {

    public static String SCOPE_SINGLETON = "singleton";

    public static String SCOPE_PROTOTYPE = "prototype";

    private Class beanClass;

    private PropertyValues propertyValues;

    //初始化方法名
    private String initMethodName;

    //销毁方法名
    private String destroyMethodName;

    //bean作用域
    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

    public BeanDefinition(Class beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null? propertyValues : new PropertyValues();
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton(){
        return this.singleton;
    }

    public boolean isPrototype(){
        return this.prototype;
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

    public String getInitMethodName(){
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName){
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName(){
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName){
        this.destroyMethodName = destroyMethodName;
    }
}
