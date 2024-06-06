package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

/**
 * @description 使用CGLIB动态生成子类实现实例化策略
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantaite(BeanDefinition beanDefinition) throws BeansException {
        //TODO 实现CGLIB动态生成
        throw new UnsupportedOperationException("暂不支持CGLIB动态生成");
    }
}
