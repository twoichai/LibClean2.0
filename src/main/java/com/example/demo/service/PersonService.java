package com.example.demo.service;

import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.repository.exceptions.UserNotFoundException;
import com.example.demo.repository.model.Person;


import java.util.List;
import java.util.Optional;

/**
 * interface with all needed methods which are implemented in [PersonServiceIml]
 */

public interface PersonService {

    Long save(PersonDTO personDTO);

    Person update(Long personID, String name, String surname, String email);

    boolean deleteAll();
    boolean deleteById(Long personId);

    Person getById(Long personId) throws UserNotFoundException;
    List<PersonDTO> getAll();

}
