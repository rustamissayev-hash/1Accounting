package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NodeEntity("TaxPolicy")
public class TaxPolicy {

    @Id
    private String id;

    @Property
    private String name;

    @Property
    private LocalDate validFrom;

    @Property
    private LocalDate validTo;

    @Property
    private boolean active = true;

    @Property
    private String taxType;

    @Property
    private double taxRate;

    @Property
    private String reportFormCode;

    @Property
    private String reportFormName;

    @Property
    private String reportFrequency;

    @Property
    private LocalDateTime createdAt;

    @Property
    private String createdBy;
}
