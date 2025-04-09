package com.bank.payment.mq.routing.service;

import com.bank.payment.mq.routing.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    Message saveMessage(String content);
    Page<Message> getAllMessages(Pageable pageable);
    Message getMessageById(Long id);
}
