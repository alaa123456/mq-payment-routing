package com.bank.payment.mq.routing.service;

import com.bank.payment.mq.routing.model.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerService {
    Partner addPartner(Partner partner);
    Page<Partner> getAllPartners(Pageable pageable);
    void deletePartner(String alias);

}
