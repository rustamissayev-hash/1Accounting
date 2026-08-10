package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NodeEntity("JournalEntry")
public class JournalEntry {

    @Id
    private String id;

    @Property
    private LocalDate entryDate;

    @Property
    private String debitAccountCode;

    @Property
    private String creditAccountCode;

    @Property
    private BigDecimal amount;

    @Property
    private String currencyCode;

    @Property
    private String description;

    @Property
    private String documentId;

    @Property
    private boolean posted = false;

    @Property
    private LocalDateTime postedAt;

    @Property
    private LocalDateTime createdAt;
}
