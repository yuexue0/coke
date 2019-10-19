package com.xuelin.coke.event;

import com.xuelin.coke.event.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String msg) {
        applicationEventPublisher.publishEvent(new DemoEvent(this, msg));
    }
}
