package com.example.demo.service.implementations;

import com.example.demo.controller.dto.ItemDTO;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.exceptions.ItemAlreadyExists;
import com.example.demo.repository.exceptions.ItemNotFoundException;
import com.example.demo.repository.model.Item;
import com.example.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service(value = "item")
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }
    @Override
    public Long save(ItemDTO itemDTO) throws ItemAlreadyExists {
        try {
            Item item = itemMapper.itemDTOToItem(itemDTO);
            Optional<Item> itemByName = Optional.ofNullable(itemRepository.findItemByTitleOfItem(item.getTitleOfItem()));
            if (itemByName.isPresent()) {
                log.info("Item already exists, can't create new one");
                throw new ItemAlreadyExists("Item already exists, can't create new one");
            } else
                item = itemRepository.save(item);
            log.info("Saving new item into the data base {item}");
            return item.getId();
        } catch (Exception e){
            log.error("An error occurred while saving the item ", e.getMessage());
            return null;
        }
    }

    @Override
    public Item update(Long itemId, String title, String author) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id " + itemId + " does not exist"));
        if(title != null &&
        title.length() > 0 &&
        !Objects.equals(item.getTitleOfItem(), title)){
            log.info("Item with the id: " + itemId + " was updated");
            item.setTitleOfItem(title);
        }
        if(author != null &&
                title.length() > 0 &&
                !Objects.equals(item.getAuthor(), author)){
            log.info("Item with the id: " + itemId + " was updated");
            item.setAuthor(author);
        }
        return item;
    }

    @Override
    public void updateIntern(Item item) {
        itemRepository.save(item);
    }

    @Override
    public boolean deleteAll() {
        itemRepository.deleteAll();
        log.info("Flush... The data base {item} has no entries anymore");
        return true;
    }

    @Override
    public boolean deleteById(Long itemId) throws ItemNotFoundException {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists) {
            throw new ItemNotFoundException("There is no item with id " + itemId + " in our system");
        } else {
            itemRepository.deleteById(itemId);
            return true;
        }
    }
    @Override
    public Optional<List<ItemDTO>> getItemsByAuthor(String author) {
        List<Item> itemList = itemRepository.findAllByAuthor(author);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList){
            ItemDTO temp = itemMapper.itemToItemDTO(item);
            itemDTOList.add(temp);
        }
        log.info("[DTO] List of items by author was printed");
        return Optional.of(itemDTOList);
    }

    @Override
    public Optional<Optional<Item>> getItemsByTitle(String title) {
        Optional<Item> itemList = Optional.ofNullable(itemRepository.findItemByTitleOfItem(title));

        log.info("[DTO] List of items by title was printed");
        return Optional.of(itemList);
    }

    @Override
    public Item getById(Long itemId) throws ItemNotFoundException {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            log.info("An item with the id " + itemId + " was given");
        return optionalItem.get();
        } else {
            throw new ItemNotFoundException("Item with id " + itemId + " was not found ;("  );
        }
        // String.format("text %1$s", object)
    }

    @Override
    public List<Item> getAll() {

        return itemRepository.findAll();
    }

}
