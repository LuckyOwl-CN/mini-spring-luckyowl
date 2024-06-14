package com.luckyowl.test.beans.factory;

import com.luckyowl.test.beans.BeansException;

/**
 * @description 工厂bean
 * <p>
 *     一种特殊的bean，当向容器获取该bean时
 *     容器返回的不是本身，而是调用getObject方法返回的对象
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
