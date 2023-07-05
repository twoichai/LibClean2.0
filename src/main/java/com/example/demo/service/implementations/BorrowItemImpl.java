package com.example.demo.service.implementations;

import com.example.demo.repository.exceptions.ItemNotAvailableException;
import com.example.demo.repository.exceptions.ItemNotFoundException;
import com.example.demo.repository.model.Item;
import com.example.demo.repository.model.Person;
import com.example.demo.service.ItemService;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to manage the borrowing of items
 */
@Slf4j
@Service
public class BorrowItemImpl {
    private final ItemService itemService;
    private final PersonService personService;


    // inj service of items + persons
    public BorrowItemImpl(@Qualifier("item") ItemService itemService, @Qualifier("admin") PersonService personService) {
        this.itemService = itemService;
        this.personService = personService;
    }

    /**
     * borrow items with
     *
     * @param itemId   of the item
     * @param personId of the person which borrows
     *                 connects
     */
    public void borrowMethod(long itemId, long personId) throws ItemNotAvailableException {
        Optional<Person> person = personService.getById(personId);
        Item item;
        try {
            item = itemService.getById(itemId);
            Person personLoaded = person.get();
            item.setPerson(personLoaded);
            item.setIsAvailable(false);
            itemService.updateIntern(item);
            log.info("Item was borrowed");

        }catch (ItemNotFoundException ex){
            log.info("Item with id {} not found", itemId);
            return ;
        }



    }
}
