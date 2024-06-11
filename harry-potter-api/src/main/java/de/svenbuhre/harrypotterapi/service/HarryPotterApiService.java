package de.svenbuhre.harrypotterapi.service;

import de.svenbuhre.harrypotterapi.model.HarryPotterCharacter;
import de.svenbuhre.harrypotterapi.repository.HarryPotterRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HarryPotterApiService {
    private static final String HP_API_BASE_URL = "https://hp-api.onrender.com/api";
    private final RestTemplate restTemplate;
    private final HarryPotterRepository harryPotterRepository;

    public HarryPotterApiService(@Qualifier("harryPotterRestTemplate") RestTemplate restTemplate, HarryPotterRepository harryPotterRepository) {
        this.restTemplate = restTemplate;
        this.harryPotterRepository = harryPotterRepository;
    }

    @PostConstruct
    @Transactional
    public void initDatabase() {
        if (harryPotterRepository.count() == 0) {
            harryPotterRepository.saveAll(getPeople());
        }
    }

    public List<HarryPotterCharacter> getPeople() {
        if (harryPotterRepository.count() > 0) {
            return harryPotterRepository.findAll();
        }
        return fetchAllCharacters();
    }

    private List<HarryPotterCharacter> fetchAllCharacters() {
        HarryPotterCharacter[] response = restTemplate.getForObject(HP_API_BASE_URL + "/characters", HarryPotterCharacter[].class);
        if (response != null) {
            return Arrays.asList(response);
        }
        return new ArrayList<>();
    }
}
