package de.svenbuhre.harrypotterapi.service;

import de.svenbuhre.harrypotterapi.model.HarryPotterCharacter;
import de.svenbuhre.harrypotterapi.repository.HarryPotterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HarryPotterApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private HarryPotterRepository harryPotterRepository;

    @InjectMocks
    private HarryPotterApiService harryPotterApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPeople_FromDatabase() {
        HarryPotterCharacter character = new HarryPotterCharacter();
        character.setName("Harry Potter");
        when(harryPotterRepository.findAll()).thenReturn(Collections.singletonList(character));
        when(harryPotterRepository.count()).thenReturn(1L);

        List<HarryPotterCharacter> people = harryPotterApiService.getPeople();

        assertEquals(1, people.size());
        assertEquals("Harry Potter", people.getFirst().getName());
        verify(harryPotterRepository, times(1)).findAll();
    }

    @Test
    void testGetPeople_FromApi() {
        HarryPotterCharacter character = new HarryPotterCharacter();
        character.setName("Hermione Granger");
        HarryPotterCharacter[] characters = {character};
        when(harryPotterRepository.count()).thenReturn(0L);
        when(restTemplate.getForObject(anyString(), eq(HarryPotterCharacter[].class)))
                .thenReturn(characters);

        List<HarryPotterCharacter> people = harryPotterApiService.getPeople();

        assertEquals(1, people.size());
        assertEquals("Hermione Granger", people.getFirst().getName());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(HarryPotterCharacter[].class));
    }
}
