package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.DisposableBean;
import com.luckyowl.test.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description 默认单例bean注册表——实现单例bean注册表接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存储所有单例bean的Map
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 存储拥有销毁方法的bean
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例bean
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> beanNames = disposableBeans.keySet();
        for(String beanName: beanNames){
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try{
                disposableBean.destroy();
            }catch(Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
