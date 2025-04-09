package com.bank.payment.mq.routing.service.impl;


import com.bank.payment.mq.routing.model.Partner;
import com.bank.payment.mq.routing.repository.PartnerRepository;
import com.bank.payment.mq.routing.service.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository repository;

    public PartnerServiceImpl(PartnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Partner addPartner(Partner partner) {
        return repository.save(partner);
    }

    @Override
    public Page<Partner> getAllPartners(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deletePartner(String alias) {
        repository.deleteById(alias);
    }
}