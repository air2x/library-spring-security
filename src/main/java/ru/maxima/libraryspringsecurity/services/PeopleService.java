package ru.maxima.libraryspringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Person> findAllPeople() {
        return peopleRepository.findAll();
    }

    public Person findOnePerson(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void updatePerson(int id, Person updatePerson) {
        updatePerson.setId(id);
        updatePerson.setYearOfBirth(updatePerson.getYearOfBirth());
        updatePerson.setFullName(updatePerson.getFullName());
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}
