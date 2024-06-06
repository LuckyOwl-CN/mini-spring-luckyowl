package com.luckyowl.test.beans.factory.config;

/**
 * @description 用于记录一个bean对于另一个bean的引用
 * <p>
 *     实例化A填充属性时，如果用到了BeanReference，则先构建B
 *     <b>后续解决循环依赖问题</b>
 *     保存beanName用于获取bean定义
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
//TODO 待解决循环依赖问题
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
