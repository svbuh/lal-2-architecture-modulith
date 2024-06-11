package de.svenbuhre.harrypotterapi.repository;

import de.svenbuhre.harrypotterapi.model.HarryPotterCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarryPotterRepository extends JpaRepository<HarryPotterCharacter, Long> {
}