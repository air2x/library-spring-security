package ru.maxima.libraryspringsecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.services.PersonService;
import ru.maxima.libraryspringsecurity.validation.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator validator;
    private final PersonService service;

    @Autowired
    public AuthController(PersonValidator validator, PersonService service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String giveRegistrationPage(@ModelAttribute Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("personFromPage") @Valid Person person,
                                      BindingResult bindingResult) {
        validator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        service.save(person);
        return "redirect:/auth/login";
    }
}
