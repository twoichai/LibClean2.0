package com.example.demo.repository.model;

import com.example.demo.repository.model.enums.PersonRole;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

/**
 * Entity which represents a Person which can rent/manage the online library system
 */
@Data
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class Person {

    // id | name | surname | dob | email | age | personRole | itemList [Item table]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;
    private String name;
    private String surname;
    private LocalDate dob;
    @NotNull(message = "cant be null")
    private String email;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private PersonRole personRole;
    @OneToMany(mappedBy = "person")

    private List<Item> itemList;




}

