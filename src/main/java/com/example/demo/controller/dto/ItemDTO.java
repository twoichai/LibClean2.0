package com.example.demo.controller.dto;

import com.example.demo.repository.model.Person;
import com.example.demo.repository.model.enums.TypeOfItem;
import lombok.*;


import java.time.LocalDate;

/**
 * DTO for the [Item] entity
 */
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String author;
    private String titleOfItem;
    private TypeOfItem typeOfItem;
    private LocalDate dateOfBorrowing;
    private Boolean isAvailable;
    private String imageUrl;
    private PersonDTO person;
}

