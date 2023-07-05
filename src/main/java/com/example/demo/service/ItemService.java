package com.example.demo.service;

import com.example.demo.controller.dto.ItemDTO;
import com.example.demo.repository.exceptions.ItemNotFoundException;
import com.example.demo.repository.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Long save(ItemDTO itemDTO);

    Item update(Long itemId, String name, String author);
    void updateIntern(Item item);
    boolean deleteAll();
    boolean deleteById(Long itemId);

    Optional<List<ItemDTO>> getItemsByAuthor(String author);
    Optional<Optional<Item>> getItemsByTitle(String title);
    Item getById(Long itemId) throws ItemNotFoundException;
    List<Item> getAll();
}
