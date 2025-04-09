package com.bank.payment.mq.routing.service;

import com.bank.payment.mq.routing.model.Partner;
import com.bank.payment.mq.routing.repository.PartnerRepository;
import com.bank.payment.mq.routing.service.impl.PartnerServiceImpl;
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
class PartnerServiceTest {

    @Autowired
    private PartnerRepository partnerRepository;

    private PartnerServiceImpl partnerService;

    @BeforeEach
    void setUp() {
        partnerService = new PartnerServiceImpl(partnerRepository);
    }

    @Test
    void testAddPartner() {
        // Given
        Partner partner = new Partner();
        partner.setAlias("partner1");
        partner.setType("type1");
        partner.setDirection(Partner.Direction.INBOUND);
        partner.setApplication("app1");
        partner.setProcessedFlowType(Partner.ProcessedFlowType.MESSAGE);
        partner.setDescription("Test partner");

        // When
        Partner savedPartner = partnerService.addPartner(partner);

        // Then
        assertEquals("partner1", savedPartner.getAlias(), "Alias should match");
        assertEquals("type1", savedPartner.getType(), "Type should match");
        assertEquals(Partner.Direction.INBOUND, savedPartner.getDirection(), "Direction should match");
        assertEquals("app1", savedPartner.getApplication(), "Application should match");
        assertEquals(Partner.ProcessedFlowType.MESSAGE, savedPartner.getProcessedFlowType(), "ProcessedFlowType should match");
        assertEquals("Test partner", savedPartner.getDescription(), "Description should match");
    }

    @Test
    void testGetAllPartners() {
        // Given
        Partner partner1 = createPartner("partner1", "type1", Partner.Direction.INBOUND,
                "app1", Partner.ProcessedFlowType.MESSAGE, "Partner 1");
        partnerService.addPartner(partner1);

        Partner partner2 = createPartner("partner2", "type2", Partner.Direction.OUTBOUND,
                "app2", Partner.ProcessedFlowType.NOTIFICATION, "Partner 2");
        partnerService.addPartner(partner2);

        // When
        Pageable pageable = PageRequest.of(0, 1);
        Page<Partner> partnerPage = partnerService.getAllPartners(pageable);

        // Then
        assertEquals(2, partnerPage.getTotalElements(), "Total elements should be 2");
        assertEquals(1, partnerPage.getNumberOfElements(), "Page should contain 1 element");
        assertEquals(0, partnerPage.getNumber(), "Should be page 0");
        assertEquals(2, partnerPage.getTotalPages(), "Should have 2 pages with size 1");
    }


    private Partner createPartner(String alias, String type, Partner.Direction direction,
                                  String application, Partner.ProcessedFlowType flowType, String description) {
        Partner partner = new Partner();
        partner.setAlias(alias);
        partner.setType(type);
        partner.setDirection(direction);
        partner.setApplication(application);
        partner.setProcessedFlowType(flowType);
        partner.setDescription(description);
        return partner;
    }
}