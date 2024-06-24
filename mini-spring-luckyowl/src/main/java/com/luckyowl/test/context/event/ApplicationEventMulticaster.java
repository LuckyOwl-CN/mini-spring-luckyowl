package com.luckyowl.test.context.event;

import com.luckyowl.test.context.ApplicationEvent;
import com.luckyowl.test.context.ApplicationListener;

/**
 * @description 注册监听器和发布事件的接口
 *
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
