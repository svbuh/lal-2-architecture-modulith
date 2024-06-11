package de.svenbuhre.starwarsapi.service;

import de.svenbuhre.starwarsapi.model.Person;
import de.svenbuhre.starwarsapi.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsApiService {
    private static final String SWAPI_BASE_URL = "https://swapi.dev/api/";
    private final RestTemplate restTemplate;

    private final PersonRepository personRepository;

    public StarWarsApiService(@Qualifier("starWarsRestTemplate") RestTemplate restTemplate, PersonRepository personRepository) {
        this.restTemplate = restTemplate;
        this.personRepository = personRepository;
    }

    @PostConstruct
    @Transactional
    public void initDatabase() {
        if (personRepository.count() == 0) {
            personRepository.saveAll(getPeople());
        }
    }

    public List<Person> getPeople() {
        if (personRepository.count() > 0) {
            return personRepository.findAll();
        }
        return fetchAllPages(SWAPI_BASE_URL + "people/", PersonResponse.class, BaseApiResponse::getResults);
    }

    private <T> List<T> fetchAllPages(
            String initialUrl,
            Class<? extends BaseApiResponse<T>> responseType, Function<BaseApiResponse<T>, List<T>> resultsExtractor) {
        List<T> allResults = new ArrayList<>();
        String url = initialUrl;

        while (url != null) {
            BaseApiResponse<T> response = restTemplate.getForObject(url, responseType);
            if (response != null && resultsExtractor.apply(response) != null) {
                allResults.addAll(resultsExtractor.apply(response));
                url = response.getNext();
            } else {
                url = null;
            }
        }

        return allResults;
    }

    static class PersonResponse extends BaseApiResponse<Person> {}

    @Setter
    @Getter
    private abstract static class BaseApiResponse<T> {
        private List<T> results;
        private String next;

    }
}
