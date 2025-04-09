package com.bank.payment.mq.routing.controller;

import com.bank.payment.mq.routing.model.Partner;
import com.bank.payment.mq.routing.service.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/partners")
@CrossOrigin("*")
public class PartnerController {
    private final PartnerService service;

    public PartnerController(PartnerService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Partner addNewPartner(@RequestBody Partner partner) {
        return service.addPartner(partner);
    }

    @GetMapping
    public Page<Partner> getAllPartners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var pageable = PageRequest.of(page, size);
        return service.getAllPartners(pageable);
    }

    @DeleteMapping("/{alias}")
    public void deletePartner(@PathVariable String alias) {
        service.deletePartner(alias);
    }
}