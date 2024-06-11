package de.svenbuhre.harrypotterapi.controller;

import static org.mockito.Mockito.when;

import de.svenbuhre.harrypotterapi.model.HarryPotterCharacter;
import de.svenbuhre.harrypotterapi.service.HarryPotterApiService;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HarryPotterApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HarryPotterApiService harryPotterApiService;

    @Test
    void testGetPeople() throws Exception {
        HarryPotterCharacter character = new HarryPotterCharacter();
        character.setName("Harry Potter");
        when(harryPotterApiService.getPeople()).thenReturn(Collections.singletonList(character));

        mockMvc.perform(get("/api/harrypotter/people"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'Harry Potter'}]"));
    }
}
