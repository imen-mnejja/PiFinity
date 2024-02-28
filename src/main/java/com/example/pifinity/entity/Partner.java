package com.example.pifinity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Partner implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long partnerid;
    private String name;
    private String domain;
    private Integer nbtransaction;
    private Long beneficenet;
    private Long chiffredaffaires;
    private Long chargesfixes;

   @ToString.Exclude
    @OneToOne
   BankAccount bankAccount;

    @ToString.Exclude
    @OneToOne(mappedBy="partner")
    private Advertising advertising;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy="partner")
    private Set<Promotion> Promotions;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy="partner")
    private Set<JobOffer> JobOffers;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy="partner")
    private Set<Formation> Formations;
}
