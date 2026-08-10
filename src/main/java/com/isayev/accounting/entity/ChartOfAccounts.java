package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity("ChartOfAccounts")
public class ChartOfAccounts {

    @Id
    private String id;

    @Property
    private String code;

    @Property
    private String nameRu;

    @Property
    private String nameKz;

    @Property
    private String nameEn;

    @Property
    private String accountType;

    @Property
    private String category;

    @Property
    private boolean active = true;

    @Relationship(type = "HAS_SUBACCOUNT", direction = Relationship.Direction.OUTGOING)
    private Set<ChartOfAccounts> subAccounts = new HashSet<>();

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private ChartOfAccounts parentAccount;
}
