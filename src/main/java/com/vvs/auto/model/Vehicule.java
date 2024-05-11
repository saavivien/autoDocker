package com.vvs.auto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vehicule")
/**
 * Véhicule
 */
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String marque;
    private String model;
    @Column(name="annee_production")
    private LocalDate anneeProduction;
    @ManyToOne
    @JsonIgnore
    private Employee employee;
}
