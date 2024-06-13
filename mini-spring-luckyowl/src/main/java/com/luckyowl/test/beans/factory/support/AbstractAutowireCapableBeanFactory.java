package com.luckyowl.test.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.PropertyValue;
import com.luckyowl.test.beans.factory.BeanFactoryAware;
import com.luckyowl.test.beans.factory.DisposableBean;
import com.luckyowl.test.beans.factory.InitializingBean;
import com.luckyowl.test.beans.factory.config.AutowireCapableBeanFactory;
import com.luckyowl.test.beans.factory.config.BeanDefinition;
import com.luckyowl.test.beans.factory.config.BeanPostProcessor;
import com.luckyowl.test.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

/**
 * @description 抽象自动注入bean工厂
 * <p>
 *     继承抽象bean工厂，实现了创建bean实例的功能
 *     <b>至此该继承分支完成了根据bean定义创建bean实例的功能，后续需要完成获取bean定义</b>
 * </p>
 *
 * @author LuckyOwl-CN
 * @date 2024/6/6
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition){
        //Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try{
            //bean = beanClass.newInstance();
            //1. 根据实例化策略创建bean实例
            bean = createBeanInstance(beanDefinition);
            //2. 为创建的bean实例填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            //3. 执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e){
            throw new BeansException("实例化bean失败", e);
        }
        //注册有销毁方法的bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 注册有销毁方法的bean，即bean继承自
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition){
        return getInstantiationStrategy().instantaite(beanDefinition);
    }

    public InstantiationStrategy getInstantiationStrategy(){
        return this.instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 为bean实例填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try {
            //Class beanClass = beanDefinition.getBeanClass();

            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                /*
                //通过属性field的set方法来设置属性值
                //1. 获取bean的class
                Class<?> type = beanClass.getDeclaredField(name).getType();
                //2. 通过属性名PropertyName构造出set方法名
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                //3. 通过bean的class与方法名获取到对应的方法对象method
                Method method = beanClass.getDeclaredMethod(methodName, type);
                //4. 调用set方法来给bean中的属性赋值value-即调用bean对象的method方法，传入value参数
                method.invoke(bean, value);
                */

                //如果属性值是BeanReference类型，则先构建该bean实例
                if(value instanceof BeanReference){
                    //A依赖B，先实例化B
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                //通过引入hutool工具类，使用反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception ex){
            throw new BeansException("为bean实例:["+ beanName +"]填充属性失败", ex);
        }
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        //设置beanFactory
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        //1. 执行BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try{
            invokeInitMethod(beanName, wrappedBean, beanDefinition);
        } catch (Throwable ex){
            throw new BeansException("调用bean["+ beanName +"]的初始化方法失败", ex);
        }

        //3. 执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if(current == null){
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessors()){
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if(current == null){
                return result;
            }
            result = current;
        }
        return result;
    }

    protected void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
        //1.若bean继承自InitializingBean则调用初始化方法
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        //2.获取初始化方法
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if(initMethod == null) {
                throw new BeansException("找不到bean[" + beanName + "]的初始化方法[" + initMethodName + "]");
            }
            //3.初始化
            initMethod.invoke(bean);
        }
    }
}
