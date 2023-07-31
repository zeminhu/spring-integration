package com.hzsolution.integration.queue;

import org.springframework.stereotype.Component;

@Component
public class RabbitQListner {
    public void processMessage(Object messageObject) {
        String message = new String((byte[]) messageObject);
        System.out.println("message");
    }
}
