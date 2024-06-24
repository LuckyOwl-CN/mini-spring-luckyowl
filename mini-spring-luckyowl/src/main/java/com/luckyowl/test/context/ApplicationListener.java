package com.luckyowl.test.context;

import java.util.EventListener;

/**
 * @description 应用事件监听器
 *
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
