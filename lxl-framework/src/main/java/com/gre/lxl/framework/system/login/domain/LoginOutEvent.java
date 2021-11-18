package com.gre.lxl.framework.system.login.domain;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author lxl
 * @date 2021/11/12 17:34
 */
public class LoginOutEvent extends ApplicationEvent {

    private final String event;

    public LoginOutEvent(Object source, String event) {
        super(source);
        this.event = event;
    }

    public LoginOutEvent(Object source, Clock clock, String event) {
        super(source, clock);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
