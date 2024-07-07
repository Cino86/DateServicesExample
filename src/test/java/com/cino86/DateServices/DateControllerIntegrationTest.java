package com.cino86.DateServices;

import com.cino86.DateServices.DateServicesApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cino86.DateServices.model.DataInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = DateServicesApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testCalcolaData() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        DataInput dataInput = new DataInput();
        dataInput.setDataPartenza(LocalDate.of(2024, 7, 7));
        dataInput.setDeltaGiorni(10);
        dataInput.setTipoDelta("lavorativi");
        dataInput.setCalendariFestivi(List.of("Milano"));

        mockMvc.perform(post("/api/date/calcolaData")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataInput)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"dataCalcolata\": \"2024-07-19\"}"));
    }
}