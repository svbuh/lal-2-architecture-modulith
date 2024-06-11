package de.svenbuhre.summary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class SummaryController {
    private final WebClient webClient = WebClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/api-data")
    public String getApiData(Model model) {
        Mono<String> starWarsDataMono = webClient.get()
                .uri("http://localhost:8082/api/starwars/people")
                .retrieve()
                .bodyToMono(String.class);

        Mono<String> harryPotterDataMono = webClient.get()
                .uri("http://localhost:8081/api/harrypotter/people")
                .retrieve()
                .bodyToMono(String.class);

        String starWarsData = starWarsDataMono.block();
        String harryPotterData = harryPotterDataMono.block();

        List<Map<String, Object>> starWarsList = parseJson(starWarsData);
        List<Map<String, Object>> harryPotterList = parseJson(harryPotterData);

        model.addAttribute("starWarsData", starWarsList);
        model.addAttribute("harryPotterData", harryPotterList);

        return "apiData";
    }

    private List<Map<String, Object>> parseJson(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
