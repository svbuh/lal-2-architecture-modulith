package de.svenbuhre.starwarsapi.repository;

import de.svenbuhre.starwarsapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
