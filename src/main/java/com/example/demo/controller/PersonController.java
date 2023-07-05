package com.example.demo.controller;

import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.repository.model.Item;
import com.example.demo.repository.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.service.implementations.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/***
 * ToDo: RestController for handling user data for the admin or the user itself
 * right now it is handling all users don't matter which role
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    /**
     * Autowiring [PersonController] with the [PersonServiceImpl]
     * @param personService
     */
    public PersonController(@Qualifier("admin") PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<PersonDTO> getPersonList() {
        return personService.getAll(); }

    @GetMapping("/{personId}")
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        Person person = personService.getById(personId);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.save(personDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePerson(@PathVariable("personId") Long personId,
                                               @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String surname,

                                             @RequestParam(required = false) String email){
        Person updatePerson = personService.update(personId, name, surname, email);
        return new ResponseEntity<>(updatePerson, HttpStatus.OK);
    }
    // ToDo: ResponseEntity

    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePersonById(@PathVariable("personId")Long personId) {
        personService.deleteById(personId);
        return new ResponseEntity<>(HttpStatus.OK);}
    @DeleteMapping
    public ResponseEntity<?> deleteAllPersons(){
        personService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
