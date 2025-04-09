package com.bank.payment.mq.routing.service;

import com.bank.payment.mq.routing.model.Message;
import com.bank.payment.mq.routing.repository.MessageRepository;
import com.bank.payment.mq.routing.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    void testSaveMessage() {
        // Given
        String content = "Test message content";

        // When
        Message savedMessage = messageService.saveMessage(content);

        // Then
        assertNotNull(savedMessage.getId(), "Message ID should be generated");
        assertEquals(content, savedMessage.getContent(), "Content should match");
        assertNotNull(savedMessage.getCreatedAt(), "CreatedAt should be set");
    }

    @Test
    void testGetAllMessages() {
        // Given
        Message message1 = messageService.saveMessage("Message 1");
        Message message2 = messageService.saveMessage("Message 2");

        // When
        Pageable pageable = PageRequest.of(0, 1);
        Page<Message> messagePage = messageService.getAllMessages(pageable);

        // Then
        assertEquals(2, messagePage.getTotalElements(), "Total elements should be 2");
        assertEquals(1, messagePage.getNumberOfElements(), "Page should contain 1 element");
        assertEquals(0, messagePage.getNumber(), "Should be page 0");
        assertEquals(2, messagePage.getTotalPages(), "Should have 2 pages with size 1");
        assertEquals("Message 1", messagePage.getContent().getFirst().getContent(), "First message should match");
    }

    @Test
    void testGetMessageById_found() {
        // Given
        Message savedMessage = messageService.saveMessage("Test message");

        // When
        Message foundMessage = messageService.getMessageById(savedMessage.getId());

        // Then
        assertNotNull(foundMessage, "Message should be found");
        assertEquals(savedMessage.getId(), foundMessage.getId(), "ID should match");
        assertEquals("Test message", foundMessage.getContent(), "Content should match");
    }

    @Test
    void testGetMessageById_notFound() {
        // When
        Message foundMessage = messageService.getMessageById(999L);

        // Then
        assertNull(foundMessage, "Should return null for non-existent ID");
    }
}