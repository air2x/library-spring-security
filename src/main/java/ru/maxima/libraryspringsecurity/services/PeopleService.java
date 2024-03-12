package ru.maxima.libraryspringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Person> findAllPeople() {
        return peopleRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Person findOnePerson(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updatePerson(int id, Person updatePerson) {
        updatePerson.setId(id);
        updatePerson.setYearOfBirth(updatePerson.getYearOfBirth());
        updatePerson.setFullName(updatePerson.getFullName());
        peopleRepository.save(updatePerson);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}
