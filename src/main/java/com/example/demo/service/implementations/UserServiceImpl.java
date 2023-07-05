package com.example.demo.service.implementations;

import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.repository.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements PersonService {
    @Override
    public Long save(PersonDTO personDTO) {
        return null;
    }

    @Override
    public void update(Long personID, String name, String email) {

    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean deleteById(Long personId) {
        return false;
    }

    @Override
    public Optional<Person> getById(Long personId) {
        return Optional.empty();
    }

    @Override
    public List<PersonDTO> getAll() {
        return null;
    }
}
