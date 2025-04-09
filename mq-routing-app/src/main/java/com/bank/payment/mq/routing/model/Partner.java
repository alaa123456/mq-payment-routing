package com.bank.payment.mq.routing.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;
    private String type;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    private String application;
    @Enumerated(EnumType.STRING)
    private ProcessedFlowType processedFlowType;
    private String description;

    public enum Direction { INBOUND, OUTBOUND }
    public enum ProcessedFlowType { MESSAGE, ALERTING, NOTIFICATION }
}