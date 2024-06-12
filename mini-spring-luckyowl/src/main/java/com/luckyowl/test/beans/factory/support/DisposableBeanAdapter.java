package com.luckyowl.test.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.DisposableBean;
import com.luckyowl.test.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/12
 **/
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1. 如果bean继承DisposableBean则调用destroy方法
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        //2. 避免自定义销毁方法与继承DisposableBean导致bean被销毁两次
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))){
            //执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if(destroyMethod == null){
                throw new BeansException("找不到bean["+ beanName +"]的销毁方法[" + destroyMethodName + "]");
            }
            destroyMethod.invoke(bean);
        }
    }
}
