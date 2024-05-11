package com.vvs.auto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
/**
 * Employé
 */
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String matricule;
    @Column
    private String name;
    @Column(name="recrutement_date")
    private LocalDate recrutementDate;
    @OneToMany(mappedBy = "employee")
    private List<Vehicule> vehicules;
}
