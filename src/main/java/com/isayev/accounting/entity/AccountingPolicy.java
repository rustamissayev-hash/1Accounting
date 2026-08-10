package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity("AccountingPolicy")
public class AccountingPolicy {

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
    private String currencyCode;

    @Property
    private String language;

    @Property
    private String accountingStandard;

    @Property
    private LocalDateTime createdAt;

    @Property
    private String createdBy;

    @Relationship(type = "HAS_ACCOUNT", direction = Relationship.Direction.OUTGOING)
    private Set<ChartOfAccounts> chartOfAccounts = new HashSet<>();

    @Relationship(type = "HAS_ENTRY_TYPE", direction = Relationship.Direction.OUTGOING)
    private Set<EntryType> entryTypes = new HashSet<>();
}
