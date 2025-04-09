package com.bank.payment.mq.routing.repository;


import com.bank.payment.mq.routing.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, String> {}