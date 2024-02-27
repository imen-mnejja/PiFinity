package com.example.pifinity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor

public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long evalid;
    private String description;

    private int rating; // Nombre d'étoiles attribuées (de 1 à 5)
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Formation formation ;
}
