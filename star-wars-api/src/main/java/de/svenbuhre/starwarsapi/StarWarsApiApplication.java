package de.svenbuhre.starwarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.svenbuhre.starwarsapi.repository")
@EntityScan(basePackages = "de.svenbuhre.starwarsapi.model")
public class StarWarsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarWarsApiApplication.class, args);
    }

    @Bean(name = "starWarsRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}