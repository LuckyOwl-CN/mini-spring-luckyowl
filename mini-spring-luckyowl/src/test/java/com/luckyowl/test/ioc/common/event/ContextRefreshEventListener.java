package com.luckyowl.test.ioc.common.event;

import com.luckyowl.test.context.ApplicationListener;
import com.luckyowl.test.context.event.ContextRefreshedEvent;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
