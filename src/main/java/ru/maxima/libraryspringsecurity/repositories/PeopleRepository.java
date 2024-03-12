package ru.maxima.libraryspringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.libraryspringsecurity.model.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}
