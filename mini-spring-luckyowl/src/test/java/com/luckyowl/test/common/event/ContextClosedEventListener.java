package com.luckyowl.test.common.event;

import com.luckyowl.test.context.ApplicationListener;
import com.luckyowl.test.context.event.ContextClosedEvent;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
