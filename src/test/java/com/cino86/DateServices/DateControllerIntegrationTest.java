package com.cino86.DateServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cino86.DateServices.model.DataInput;
import com.cino86.DateServices.model.DataResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

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

    @Test
    public void testCalcolaData() throws Exception {
        DataInput dataInput = new DataInput();
        dataInput.setDataPartenza(LocalDate.of(2024, 7, 7));
        dataInput.setDeltaGiorni(10);
        dataInput.setTipoDelta("lavorativi");
        dataInput.setCalendariFestivi(List.of("Milano"));

        mockMvc.perform(post("/api/date/calcolaData")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DataResponse(LocalDate.of(2024, 7, 19)))));
    }
}