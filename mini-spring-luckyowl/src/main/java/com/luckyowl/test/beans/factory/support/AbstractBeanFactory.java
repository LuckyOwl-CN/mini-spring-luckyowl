package com.luckyowl.test.beans.factory.support;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.BeanFactory;
import com.luckyowl.test.beans.factory.FactoryBean;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.config.BeanPostProcessor;
import com.luckyowl.test.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 抽象BeanFactory
 * <p>
 *     实现了-通过根据bean名称获取bean
 *     需要实现的方法：
 *     1.创建bean实例
 *     2.根据beanName获取bean定义
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 缓存FactoryBean实例调用getBean获取到的bean对象
     */
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    /**
     * 通过bean名称获取bean实例
     * <p>
     *     1.先尝试从单实例bean缓存中获取
     *     2.缓存中获取不到则新建
     * </p>
     * @param name
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name) throws BeansException {
        //1. 尝试从单例bean缓存中获取
        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null){
            return getObjectBeanInstance(sharedInstance, name);
        }

        //2. 单例缓存中没有对应的bean：获取bean定义->实例化bean->返回bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        return getObjectBeanInstance(bean, name);
    }

    /**
     * 若bean是FactoryBean，则从FactoryBean的getObject中创建bean
     * <p>
     *     1.获取bean
     *     2.判断bean是否实现FactoryBean接口
     *     -2.1 没实现：直接返回bean
     *     -2.2 实现：获取factoryBean，尝试从缓存中获取bean
     *      -2.2.1 缓存中获取不到：调用getObject
     *      -2.2.2 缓存中获取到：直接返回
     * </p>
     * @param beanInstance
     * @param beanName
     * @return
     */
    protected Object getObjectBeanInstance(Object beanInstance, String beanName){
        Object object = beanInstance;
        //1. 若bean实例实现了FactoryBean接口
        if(beanInstance instanceof FactoryBean){
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try{
                if(factoryBean.isSingleton()){
                    object = factoryBeanObjectCache.get(beanName);
                    if(object == null){
                        object = factoryBean.getObject();
                        factoryBeanObjectCache.put(beanName, object);
                    }
                }
                else {
                    //prototype作用域，新建bean
                    object = factoryBean.getObject();
                }
            } catch (Exception e){
                throw new BeansException("FactoryBean 创建bean [" + beanName + "] 失败", e);
            }
        }
        return object;
    }

    /**
     * 定义创建bean实例的方法
     * @param beanName
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 定义获取bean定义的方法
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //存在则覆盖
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
