package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = DemoApplication.class)
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getItemById_Test() throws Exception {
        mockMvc.perform(get("/lib/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$")
                .isNotEmpty());
    }


}
