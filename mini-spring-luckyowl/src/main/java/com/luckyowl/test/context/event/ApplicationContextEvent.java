package com.luckyowl.test.context.event;

import com.luckyowl.test.context.ApplicationContext;
import com.luckyowl.test.context.ApplicationEvent;

/**
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
