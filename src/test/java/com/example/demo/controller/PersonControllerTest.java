package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.controller.dto.RestResponse;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.model.Person;
import com.example.demo.repository.model.enums.PersonRole;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MvcResult;
import java.io.UnsupportedEncodingException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonRepository personRepository;

    @Qualifier("admin")
    @Autowired
    private PersonService personService;


    @Test
    void createPersonTest() throws Exception {
        // given
        MvcResult mvcResult = mvc.perform(post("/persons/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonTestPerson())
                )
                .andExpect(status().isCreated())
                .andReturn();

        RestResponse response = parseResponse(mvcResult, RestResponse.class);
        Long personId = Long.parseLong(response.getResult());
        assertThat(personId).isGreaterThanOrEqualTo(1);

        // then
        Person person = personRepository.findById(personId).orElse(null);
        assertThat(person).isNotNull();
        assertThat(person.getName()).isEqualTo("John");
        assertThat(person.getAge()).isEqualTo(23);
        assertThat(person.getPersonRole()).isEqualTo(PersonRole.USER);
    }


    @Test
    void getPersonWithCorrectIdTest() throws Exception {
        mvc
                .perform(get("/persons/getById/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("email@mail.com"));
    }

    @Test
    void getPersonById() {
    }


    @Test
    void deletePersonByIdTest() {
    }

    @Test
    void deleteAllPersonsTest() {
    }

    private <T> T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

    private String getJsonTestPerson() {
        String name = "John";
        String surname = "Doe";
        String localDate = "2023-07-11";
        String email = "email@mail.com";
        Integer age = 23;
        String personRole = PersonRole.USER.toString();
        return """
                {
                    "name": "%s",
                    "surname": "%s",
                    "dob": "%s",
                    "email":"%s",
                    "age": "%d",
                    "personRole": "%s"
                }
                """.formatted(name, surname, localDate, email, age, personRole);
    }

}


