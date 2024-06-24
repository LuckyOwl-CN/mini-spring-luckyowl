package com.luckyowl.test.ioc;

import com.luckyowl.test.context.support.ClassPathXmlApplicationContext;
import com.luckyowl.test.ioc.common.event.CustomEvent;
import org.junit.Test;

/**
 * @author yuanjiahao03
 * @date 2024/6/14
 **/
public class EventAndEventListenerTest {

    @Test
    public void testEventListener() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        context.publishEvent(new CustomEvent(context));
        context.registerShutdownHook();
    }
}
