package com.example.demo.service.implementations;

import com.example.demo.repository.exceptions.ItemNotAvailableException;
import com.example.demo.repository.exceptions.ItemNotFoundException;
import com.example.demo.repository.exceptions.UnsuccessfulOperation;
import com.example.demo.repository.model.Item;
import com.example.demo.repository.model.Person;
import com.example.demo.service.ItemService;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void borrowMethod(long itemId, long personId) throws ItemNotFoundException {
        Person person;
        Item item;
        try {
            person = personService.getById(personId);
            item = itemService.getById(itemId);
            item.setPerson(person);
            item.setIsAvailable(false);
            item.setDateOfBorrowing(LocalDate.now());
            itemService.updateIntern(item);
            log.info("Item was borrowed");

        }catch (ItemNotFoundException ex){
            log.info("Item with id {} not found", itemId);
            return;
        }
    }
    public void unBorrowMethod(long itemId, long personId) throws UnsuccessfulOperation {
        Item item;
        try {
            item = itemService.getById(itemId);
            item.setIsAvailable(true);
            item.setDateOfBorrowing(null);
            item.setPerson(null);
            itemService.updateIntern(item);
            log.info("item with the id: " + itemId + " was successfully given back. By user with id: " + personId);
        }catch (UnsuccessfulOperation ex){
            log.info("Item could not be given back");
            return;
        }

    }
}
