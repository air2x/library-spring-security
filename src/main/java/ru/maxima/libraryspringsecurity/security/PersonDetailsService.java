package ru.maxima.libraryspringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.services.PersonService;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonService personService;

    @Autowired
    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findNyUserName(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(person) ;
    }
}
