package com.luckyowl.test.context;

/**
 * @description 事件发布器接口
 *
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public interface ApplicationEventPubisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
