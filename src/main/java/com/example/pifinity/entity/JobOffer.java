package com.example.pifinity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class JobOffer implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long jobid;
    private String titre;
    private String skills;
    private String description;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Partner partner ;

}
