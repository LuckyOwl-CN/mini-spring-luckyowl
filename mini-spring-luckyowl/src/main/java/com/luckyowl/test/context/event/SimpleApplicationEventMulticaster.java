package com.luckyowl.test.context.event;

import com.luckyowl.test.beans.BeansException;
import com.luckyowl.test.beans.factory.BeanFactory;
import com.luckyowl.test.context.ApplicationEvent;
import com.luckyowl.test.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            //判断监听器是否对事件感兴趣
            if(supportsEvent(applicationListener, event)){
                //调用监听器的onApplicationEvent方法，将事件传递给监听器处理
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    /**
     * 监听器applicationListener是否对事件event感兴趣
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event){
        //1. 获取监听器的泛型接口类型
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        //2. 获取该泛型接口的实际类型参数
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        //3. 获取实际类型的类名
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            //4. 根据类名获取类对象
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("不存在的事件类名："+ className);
        }
        //5. 判断event类是否能赋值给监听器泛型类——即event是否为监听器泛型类及其子类或子接口
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
