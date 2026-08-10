package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity("Role")
public class Role {

    @Id
    private String id;

    @Property
    private String name;

    @Property
    private String description;
}
