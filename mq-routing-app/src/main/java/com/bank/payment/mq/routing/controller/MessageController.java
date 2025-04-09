package com.bank.payment.mq.routing.controller;

import com.bank.payment.mq.routing.model.Message;
import com.bank.payment.mq.routing.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin("*")
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Message> getMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        return service.getAllMessages(pageable);
    }


    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return service.getMessageById(id);
    }
}