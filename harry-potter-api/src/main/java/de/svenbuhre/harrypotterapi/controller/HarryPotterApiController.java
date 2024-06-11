package de.svenbuhre.harrypotterapi.controller;

import de.svenbuhre.harrypotterapi.model.HarryPotterCharacter;
import de.svenbuhre.harrypotterapi.service.HarryPotterApiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HarryPotterApiController {

    private final HarryPotterApiService harryPotterApiService;

    @GetMapping("api/harrypotter/people")
    public List<HarryPotterCharacter> getPeople() {
        return harryPotterApiService.getPeople();
    }
}
