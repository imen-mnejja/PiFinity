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

public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long formationid;
    private String sujet;
    @Enumerated(EnumType.STRING)
    private TypeFormation type;
    private String titre;
    private String categorie;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Partner partner ;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,mappedBy="formation")
    private Set<Evaluation> Evaluations;
}
