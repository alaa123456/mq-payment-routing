package com.bank.payment.mq.routing.repository;


import com.bank.payment.mq.routing.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}