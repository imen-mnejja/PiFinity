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
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long promoid;
    private String descrption;
    private String promo;
    @Enumerated(EnumType.STRING)
    private CategPromo categ;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    Partner partner ;

}
