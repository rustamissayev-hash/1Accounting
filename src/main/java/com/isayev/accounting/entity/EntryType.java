package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.time.LocalDateTime;

@Data
@NodeEntity("EntryType")
public class EntryType {

    @Id
    private String id;

    @Property
    private String code;

    @Property
    private String name;

    @Property
    private String description;

    @Property
    private String debitAccountCode;

    @Property
    private String creditAccountCode;

    @Property
    private boolean active = true;

    @Property
    private LocalDateTime createdAt;
}
