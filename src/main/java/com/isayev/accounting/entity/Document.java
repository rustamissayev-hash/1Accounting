package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity("Document")
public class Document {

    @Id
    private String id;

    @Property
    private String documentNumber;

    @Property
    private LocalDate documentDate;

    @Property
    private String documentType;

    @Property
    private String status;

    @Property
    private String description;

    @Property
    private BigDecimal amount;

    @Property
    private String currencyCode;

    @Property
    private String counterpartyName;

    @Property
    private String counterpartyBin;

    @Property
    private String ecdsaSignature;

    @Property
    private LocalDateTime signedAt;

    @Property
    private String signedBy;

    @Property
    private boolean posted = false;

    @Property
    private LocalDateTime postedAt;

    @Property
    private LocalDateTime createdAt;

    @Property
    private String createdBy;

    @Relationship(type = "HAS_ENTRY", direction = Relationship.Direction.OUTGOING)
    private Set<JournalEntry> journalEntries = new HashSet<>();
}
