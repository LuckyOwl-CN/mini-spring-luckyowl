package com.luckyowl.test.context;

import java.util.EventObject;

/**
 * @description 应用事件
 *
 * @author LuckyOwl-CN
 * @date 2024/6/14
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
