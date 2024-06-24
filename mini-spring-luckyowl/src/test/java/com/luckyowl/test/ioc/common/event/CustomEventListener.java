package com.luckyowl.test.ioc.common.event;

import com.luckyowl.test.context.ApplicationListener;

/**
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
