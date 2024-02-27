package com.example.pifinity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Long numTel;
    private LocalDate birthDate;
    private String Nationnality;

  //  @ToString.Exclude
  //  @OneToMany(cascade = CascadeType.ALL,mappedBy="user")
  //  private Set<Partner> Partners;

}
