package com.example.demo.repository.model;

import com.example.demo.repository.model.enums.TypeOfItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
/**
 * Entity which represents an item you can rent with different types and details
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    // id | author| titleOfItem | typeOfItem | dateOfBorrowing | isAvailable | imageUrl | user_id [Person table]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String titleOfItem;
    @Enumerated(EnumType.STRING)
    private TypeOfItem typeOfItem;
    private LocalDate dateOfBorrowing;
    private Boolean isAvailable;
    private String imageUrl;
    /**
     * Item is the master, since the person_id is joined to the item table
     */
    @ManyToOne
    @JoinColumn(name = "user_borrowed_id")
    private Person person;

}
