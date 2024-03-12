package ru.maxima.libraryspringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.libraryspringsecurity.model.Book;
import ru.maxima.libraryspringsecurity.model.Person;
import ru.maxima.libraryspringsecurity.repositories.BooksRepository;
import ru.maxima.libraryspringsecurity.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Book findOneBook(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void saveBook(Book book) {
        booksRepository.save(book);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void updateBook(int id, Book updateBook) {
        updateBook.setId(id);
        updateBook.setNameOfBook(updateBook.getNameOfBook());
        updateBook.setAuthorOfBook(updateBook.getAuthorOfBook());
        updateBook.setYearOfWritingBook(updateBook.getYearOfWritingBook());
        booksRepository.save(updateBook);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void assignABook(int bookId, Person person) {
        Book book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
        if (peopleRepository.findById(person.getId()).isEmpty()) {
            peopleRepository.save(person);
        }
        book.setOwner(person);
        booksRepository.save(book);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void freeTheBook(int bookId) {
        Book book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
        book.setOwner(null);
        booksRepository.save(book);
    }
}
