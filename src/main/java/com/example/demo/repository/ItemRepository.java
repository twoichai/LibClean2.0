package com.example.demo.repository;

import com.example.demo.repository.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * [ItemRepo] with JpaRepo for all the DB operations
 */
public interface ItemRepository extends JpaRepository <Item, Long> {

    @Query("SELECT i FROM Item  i WHERE i.titleOfItem =?1")
    Item findItemByTitleOfItem(String titleOfItem);
    @Query("SELECT i FROM Item  i WHERE i.author =?1")
    Item findItemByAuthor(String author);
    List <Item> findAllByAuthor(String author);

}
