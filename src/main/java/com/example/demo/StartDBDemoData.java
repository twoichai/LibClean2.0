package com.example.demo;

import com.example.demo.controller.dto.ItemDTO;
import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.repository.model.enums.PersonRole;
import com.example.demo.repository.model.enums.TypeOfItem;
import com.example.demo.service.ItemService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class StartDBDemoData {
    private final ItemService itemService;
    private final PersonService personService;

    public StartDBDemoData(@Qualifier("item") ItemService itemService,
                           @Qualifier("admin") PersonService personService) {
        this.itemService = itemService;
        this.personService = personService;}

    public void initData() {
        PersonDTO person1 = PersonDTO.builder()
                .name("Paul").surname("Rose")
                .age(20).email("paulrose@mail.de")
                .dob(LocalDate.of(2003, 7, 23))
                .personRole(PersonRole.USER)
                .build();
        PersonDTO person2 = PersonDTO.builder()
                .name("Illya").surname("Benyuk")
                .age(21).email("illyabenyuk@mail.de")
                .dob(LocalDate.of(2002, 3, 2))
                .personRole(PersonRole.ADMIN)
                .build();
        PersonDTO person3 = PersonDTO.builder()
                .name("Peter").surname("Müller")
                .age(27).email("petermuellerk@mail.de")
                .dob(LocalDate.of(2000, 5, 13))
                .personRole(PersonRole.USER)
                .build();

        ItemDTO item1 = ItemDTO.builder()
                .author("Quentin Tarantino")
                .titleOfItem("Pulp Fiction")
                .typeOfItem(TypeOfItem.MOVIE)
                .dateOfBorrowing(LocalDate.of(2023, 3, 23))
                .isAvailable(false)
                .person(person1)
                .imageUrl("C:\\Users\\A200161453\\Projects\\LibClean\\Lib-main\\UniLibrary\\demo\\src\\main\\resources\\images\\movie_1.jpg")
                .build();

        ItemDTO item2 = ItemDTO.builder()
                .author("Joanne. K. Rowling")
                .titleOfItem("Harry Potter and the Philosophers Stone")
                .typeOfItem(TypeOfItem.BOOK)
                .dateOfBorrowing(null)
                .isAvailable(true)
                .imageUrl("C:\\Users\\A200161453\\Projects\\LibClean\\Lib-main\\UniLibrary\\demo\\src\\main\\resources\\images\\Harry_Potter_and_the_Philosophers_Stone_Book_Cover.jpg")
                .build();

        personService.save(person1);
        personService.save(person2);
        personService.save(person3);
        itemService.save(item1);
        itemService.save(item2);


    }
}
