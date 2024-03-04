package ru.maxima.libraryspringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.repositories.PeopleRepository;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findNyUserName(String name) {
        return peopleRepository.findByUserName(name);
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }
}
