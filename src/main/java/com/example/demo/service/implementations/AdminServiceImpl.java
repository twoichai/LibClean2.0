package com.example.demo.service.implementations;

import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.exceptions.EmailAlreadyExists;
import com.example.demo.repository.exceptions.UserNotFoundException;
import com.example.demo.repository.model.Person;
import com.example.demo.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service(value = "admin")
@Slf4j
public class AdminServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    public AdminServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public Long save(PersonDTO personDTO) throws EmailAlreadyExists {
        Person person = personMapper.personDTOToPerson(personDTO);
        Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (personByEmail.isPresent()) {
            log.info("email is taken, can't create a new user");
            throw new EmailAlreadyExists("email is taken. Please contact admin or try again");
        } else {
            person = personRepository.save(person);
            log.info("Saving new user with the id "+ person.getId() + " into the data base {person}");
            return person.getId();
        }// ToDo: Exceptions
    }

    @Transactional
    @Override
    public Person update(Long personId, String name,String surname, String email) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new UserNotFoundException("person with id " + personId
                        + " does not exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(person.getName(), name)) {
            log.info("user with the id: " + personId + " was updated");
            person.setName(name);
        }
        if (surname != null &&
                name.length() > 0 &&
                !Objects.equals(person.getSurname(), surname)) {
            log.info("user with the id: " + personId + " was updated");
            person.setSurname(surname);
        }

        if (email != null &&
                email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if (personOptional.isPresent()) {
                log.info("email is taken, can't update user with the id " + personId);
                throw new IllegalStateException("email taken");
            }
        }return person;
    }
    @Override
    public boolean deleteAll() {
        personRepository.deleteAll();
        log.info("Flush... The data base {person} has no entries anymore");
        return true;
    }

    @Override
    public boolean deleteById(Long personId) throws UserNotFoundException {
        boolean exists = personRepository.existsById(personId);
        if (!exists) {
            throw new UserNotFoundException("There is no user with id " + personId + " in our system");
        } else {
            personRepository.deleteById(personId);
            return true;
        }
    }

    @Override
    public Person getById(Long personId) throws UserNotFoundException{
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isPresent()){
            log.info("A person with the id " + personId + " was given");
            return optionalPerson.get();
        }else {
            throw new UserNotFoundException("User with id " + personId + " was not found ;(");
        }

    }

    @Override
    public List<PersonDTO> getAll() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (Person person : personList) {
            PersonDTO temp = personMapper.personToPersonDTO(person);
            personDTOList.add(temp);
        }
        log.info("List of persons was printed");
        return personDTOList;

    }
}

