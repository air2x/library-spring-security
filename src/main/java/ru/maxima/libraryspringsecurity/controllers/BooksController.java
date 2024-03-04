package ru.maxima.libraryspringsecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.libraryspringsecurity.model.Book;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.services.BooksService;
import ru.maxima.libraryspringsecurity.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", booksService.findAllBooks());
        return "view-with-all-books";
    }

    @PostMapping("/{id}/assign")
    public String assignABook(@PathVariable("id") int id,
                              @ModelAttribute("person") Person person) {
        booksService.assignABook(id, person);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOneBook(id));
        model.addAttribute("people", peopleService.findAllPeople());
        return "view-with-book-by-id";
    }

    @PostMapping("/{id}/freeTheBook")
    public String freeTheBook(@PathVariable("id") int id) {
        booksService.freeTheBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "view-to-create-new-book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-to-create-new-book";
        }
        booksService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOneBook(id));
        return "view-to-edit-book";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "view-to-edit-book";
        }
        booksService.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.deleteBook(id);
        return "redirect:/books";
    }
}
