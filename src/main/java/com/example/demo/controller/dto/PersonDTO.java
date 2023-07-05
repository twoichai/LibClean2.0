package com.example.demo.controller.dto;

import com.example.demo.repository.model.enums.PersonRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO for the [Person] entity
 */
@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dob;
    private String email;
    private Integer age;
    private PersonRole personRole;




}
