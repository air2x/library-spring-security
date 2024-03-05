package ru.maxima.libraryspringsecurity.services;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doSomeAdminStuff() {
        System.out.println("yes");
    }
}
