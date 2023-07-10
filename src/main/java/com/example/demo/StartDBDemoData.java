package com.example.demo;

import com.example.demo.controller.dto.ItemDTO;
import com.example.demo.controller.dto.PersonDTO;
import com.example.demo.repository.model.enums.PersonRole;
import com.example.demo.repository.model.enums.TypeOfItem;
import com.example.demo.service.ItemService;
import com.example.demo.service.PersonService;
import com.example.demo.service.implementations.BorrowItemImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StartDBDemoData {
    private final ItemService itemService;
    private final PersonService personService;

    private final BorrowItemImpl borrowItem;

    public StartDBDemoData(@Qualifier("item") ItemService itemService,
                           @Qualifier("admin") PersonService personService, BorrowItemImpl borrowItem) {
        this.itemService = itemService;
        this.personService = personService;
        this.borrowItem = borrowItem;

    }

    public void initData() {
        PersonDTO person1 = PersonDTO.builder()
                .name("Paul")
                .surname("Rose")
                .age(20).email("paulrose@mail.de")
                .dob(LocalDate.of(2003, 7, 23))
                .personRole(PersonRole.USER)
                .build();

        PersonDTO person2 = PersonDTO.builder()
                .name("Illya")
                .surname("Benyuk")
                .age(21).email("illyabenyuk@mail.de")
                .dob(LocalDate.of(2002, 3, 2))
                .personRole(PersonRole.ADMIN)
                .build();
        PersonDTO person3 = PersonDTO.builder()
                .name("Peter")
                .surname("Müller")
                .age(27).email("petermuellerk@mail.de")
                .dob(LocalDate.of(2000, 5, 13))
                .personRole(PersonRole.USER)
                .build();

        ItemDTO item1 = ItemDTO.builder()
                .author("Quentin Tarantino")
                .titleOfItem("Pulp Fiction")
                .typeOfItem(TypeOfItem.MOVIE)
                .dateOfBorrowing(null) //not needed
                .isAvailable(true)
                .imageUrl("pulp_fiction.jpg")
                .build();

        ItemDTO item2 = ItemDTO.builder()
                .author("Joanne. K. Rowling")
                .titleOfItem("Harry Potter and the Philosophers Stone")
                .typeOfItem(TypeOfItem.BOOK)
                .dateOfBorrowing(null) //not needed
                .isAvailable(true)
                .imageUrl("harry_potter_1.jpg")
                .build();

        ItemDTO item3 = ItemDTO.builder()
                .author("Andrew Stanton, Pete Docter")
                .titleOfItem("WALL-E")
                .typeOfItem(TypeOfItem.MOVIE)
                .dateOfBorrowing(null) //not needed
                .isAvailable(true)
                .imageUrl("walle_e.jpg")
                .build();

        ItemDTO item4 = ItemDTO.builder()
                .author("F. Scott Fitzgerald")
                .titleOfItem("The Great Gatsby")
                .typeOfItem(TypeOfItem.AUDIOBOOK)
                .dateOfBorrowing(null) //not needed
                .isAvailable(true)
                .imageUrl("the_great_gatsby.jpg")
                .build();

        Long savedPersonId1 = personService.save(person1);
        Long savedPersonId2 = personService.save(person2);
        Long savedPersonId3 = personService.save(person3);
        Long savedItemId1 = itemService.save(item1);
        Long savedItemId2 = itemService.save(item2);
        Long savedItemId3 = itemService.save(item3);
        Long savedItemId4 = itemService.save(item4);
        borrowItem.borrowMethod(savedItemId1, savedPersonId1);
        borrowItem.borrowMethod(savedItemId2, savedPersonId1);
        borrowItem.borrowMethod(savedItemId4, savedPersonId3);
        borrowItem.borrowMethod(savedItemId3, savedPersonId3);
        borrowItem.unBorrowMethod(savedItemId1,savedPersonId1);

    }
}
