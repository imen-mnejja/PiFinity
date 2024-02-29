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

public class Advertising implements Serializable {
        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long pubid;
        private String description;
        private String type;
        private String titre;
        @JsonIgnore
        @ToString.Exclude
        @OneToOne
        private Partner partner;
        @JsonIgnore
        @ToString.Exclude
        @OneToMany(cascade = CascadeType.ALL)
        private Set<Comment> Comments;
}
/*******/

