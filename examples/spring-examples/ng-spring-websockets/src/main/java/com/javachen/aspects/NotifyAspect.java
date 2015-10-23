package com.javachen.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Date;

@Aspect
public class NotifyAspect {

    private static final String WEBSOCKET_TOPIC = "/topic/notify";
    @Autowired
    private SimpMessagingTemplate template;

    @Pointcut("@annotation(com.javachen.aspects.NotifyClients)")
    public void notifyPointcut() {
    }

    @Pointcut("execution(* com.javachen.controller.**.*(..))")
    public void methodPointcut() {
    }

    @After("methodPointcut() && notifyPointcut()")
    public void notifyClients() throws Throwable {
        template.convertAndSend(WEBSOCKET_TOPIC, new Date());
    }

}
