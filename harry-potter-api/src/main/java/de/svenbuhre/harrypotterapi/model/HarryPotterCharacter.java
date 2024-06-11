package de.svenbuhre.harrypotterapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
public class HarryPotterCharacter {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
}
