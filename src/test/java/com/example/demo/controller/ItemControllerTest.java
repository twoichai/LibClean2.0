package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.controller.dto.RestResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.model.Item;
import com.example.demo.repository.model.Person;
import com.example.demo.repository.model.enums.PersonRole;
import com.example.demo.repository.model.enums.TypeOfItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createItemTest() throws Exception {
        // given
        MvcResult mvcResult = mvc.perform(post("/lib/createbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonTestItem())
                )
                .andExpect(status().isCreated())
                .andReturn();

        RestResponse response = parseResponse(mvcResult, RestResponse.class);
        Long itemId = Long.parseLong(response.getResult());
        assertThat(itemId).isGreaterThanOrEqualTo(1);

        // then
        Item item = itemRepository.findById(itemId).orElse(null);
        assertThat(item).isNotNull();
        assertThat(item.getAuthor()).isEqualTo("John");
        assertThat(item.getTitleOfItem()).isEqualTo("Book of the books");
        assertThat(item.getTypeOfItem()).isEqualTo(TypeOfItem.BOOK);
    }


    private <T> T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

    private String getJsonTestItem() {
        String author = "Peter Dinclage";
        String titleOfItem = "Book of the books";
        String typeOfItem = TypeOfItem.BOOK.toString();
        String localDate = "2023-07-11";
        Boolean isAvailable = true;
        String imageUrl = "book.jpeg";

        return """
                {
                    "author": "%s",
                    "titleOfItem": "%s",
                    "typeOfItem": "%s",
                    "localDate":"%s",
                    "isAvailable": "%s",
                    "imageUrl": "%s"
                }
                """.formatted(author, titleOfItem, typeOfItem, localDate, isAvailable, imageUrl);
    }
}