package com.example.demo.controller;


import com.example.demo.controller.dto.ItemDTO;
import com.example.demo.repository.model.Item;
import com.example.demo.service.implementations.BorrowItemImpl;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for item. Implements BorrowItem, ItemService
 */
@RestController
@RequestMapping("/lib")
public class ItemController {
    private final ItemService itemService;
    private final BorrowItemImpl borrowItemImpl;

    public ItemController(@Qualifier("item") ItemService itemService, BorrowItemImpl borrowItemImpl) {
        this.itemService = itemService;
        this.borrowItemImpl = borrowItemImpl;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItemList() {
        List<Item> itemList = itemService.getAll();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @GetMapping("/itemId/{itemId}")
    @ResponseBody
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        Item item = itemService.getById(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{author}")
    public void getItemsByAuthor(@PathVariable String author) {
        Optional<List<ItemDTO>> itemList = itemService.getItemsByAuthor(author);
    }

    @GetMapping("/borrow/{itemId}/{personId}")
    public void borrowItem(@PathVariable("itemId") Long itemId, @PathVariable("personId") Long personId) {
        borrowItemImpl.borrowMethod(itemId, personId);
    }
    @GetMapping("/unborrow/{itemId}/{personId}")
    public void unBorrowItem(@PathVariable("itemId") Long itemId, @PathVariable("personId") Long personId) {
        borrowItemImpl.unBorrowMethod(itemId, personId);
    }

    @PostMapping("/createbook")
    public ResponseEntity<Long> saveItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.save(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") Long personId,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email) {
        Item updateItem = itemService.update(personId, name, email);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable("itemId") Long itemId) {
        itemService.deleteById(itemId);
        return new ResponseEntity<>(HttpStatus.OK);}
    @DeleteMapping
    public ResponseEntity<?> deleteAllItems() {
        itemService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
