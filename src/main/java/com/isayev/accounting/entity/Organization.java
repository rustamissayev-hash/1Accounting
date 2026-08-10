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
@NodeEntity("Organization")
public class Organization {

    @Id
    private String id;

    @Property
    private String name;

    @Property
    private String bin;

    @Property
    private String countryCode;

    @Property
    private String address;

    @Property
    private String phone;

    @Property
    private String email;

    @Property
    private String taxRegistrationNumber;

    @Property
    private LocalDate registrationDate;

    @Property
    private boolean active = true;

    @Property
    private LocalDateTime createdAt;

    @Relationship(type = "HAS_POLICY", direction = Relationship.Direction.OUTGOING)
    private Set<AccountingPolicy> accountingPolicies = new HashSet<>();

    @Relationship(type = "HAS_TAX_POLICY", direction = Relationship.Direction.OUTGOING)
    private Set<TaxPolicy> taxPolicies = new HashSet<>();

    @Relationship(type = "HAS_ACCOUNT", direction = Relationship.Direction.OUTGOING)
    private Set<ChartOfAccounts> chartOfAccounts = new HashSet<>();
}
