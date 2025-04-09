package com.bank.payment.mq.routing.service.impl;

import com.bank.payment.mq.routing.model.Message;
import com.bank.payment.mq.routing.repository.MessageRepository;
import com.bank.payment.mq.routing.service.MessageService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Message saveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        return repository.save(message);
    }

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Message getMessageById(Long id) {
        return repository.findById(id).orElse(null);
    }
}