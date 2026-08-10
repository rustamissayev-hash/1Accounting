package com.isayev.accounting.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity("User")
public class User {

    @Id
    private String id;

    @Property
    private String username;

    @Property
    private String email;

    @Property
    private String fullName;

    @Property
    private String passwordHash;

    @Property
    private String ecdsaPublicKey;

    @Property
    private boolean active = true;

    @Property
    private LocalDateTime createdAt;

    @Property
    private String language;

    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private Set<Role> roles = new HashSet<>();

    @Relationship(type = "WORKS_IN", direction = Relationship.Direction.OUTGOING)
    private Organization organization;
}
