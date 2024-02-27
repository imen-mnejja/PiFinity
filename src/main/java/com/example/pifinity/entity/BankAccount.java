package com.example.pifinity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public class BankAccount {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int RIB;

        private LocalDate DateCreation;

        private float solde;

        private String statusBank;

        private String currency;

    @JsonIgnore
        @ToString.Exclude
        @OneToOne(mappedBy="bankAccount")
        private Partner partner;

   /*

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy="bankAccount")
            private Set<Transaction> Transactions;*/

}
