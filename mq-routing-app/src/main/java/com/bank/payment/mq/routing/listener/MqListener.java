package com.bank.payment.mq.routing.listener;


import com.bank.payment.mq.routing.service.MessageService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqListener {
    private final MessageService messageService;

    public MqListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @JmsListener(destination = "DEV.QUEUE.1")
    public void onMessage(String message) {
        messageService.saveMessage(message);
    }
}