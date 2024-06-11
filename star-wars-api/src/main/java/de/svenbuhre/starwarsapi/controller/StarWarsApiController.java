package de.svenbuhre.starwarsapi.controller;

import de.svenbuhre.starwarsapi.model.Person;
import de.svenbuhre.starwarsapi.service.StarWarsApiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StarWarsApiController {

    private final StarWarsApiService starWarsApiService;

    @GetMapping("api/starwars/people")
    public List<Person> getPeople() {
        return starWarsApiService.getPeople();
    }
}
