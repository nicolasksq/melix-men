package com.melix.men.integrationTests;

import com.melix.men.MenApplication;
import com.melix.men.model.MutantCounter;
import com.melix.men.repository.MutantCounterRepository;
import mockObjects.MutantCounterMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MenApplication.class)
@AutoConfigureMockMvc
public class HumanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantCounterRepository mutantCounterRepository;

    @Test
    public void statsIntegrationTest() throws Exception {

        MutantCounter mutantCounterMock  = MutantCounterMock.getMutantCounterHumanIncrementedMock();
        when(mutantCounterRepository.save(any())).thenReturn(mutantCounterMock);

        MvcResult mvcResult = mockMvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count_mutant_dna").value(mutantCounterMock.getCount_mutant_dna()))
                .andExpect(jsonPath("$.count_human_dna").value(mutantCounterMock.getCount_human_dna()))
                .andExpect(jsonPath("$.ratio").value(mutantCounterMock.getRatio()))
                .andReturn();

        verify(mutantCounterRepository, times(1)).save(any());

    }
}
