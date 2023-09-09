package ru.test.task.customercontacts.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true)
    @Setter(AccessLevel.PUBLIC)
    private String name;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Contact> contacts = new LinkedHashSet<>();

}