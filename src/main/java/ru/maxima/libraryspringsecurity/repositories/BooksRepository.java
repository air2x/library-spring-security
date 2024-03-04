package ru.maxima.libraryspringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.libraryspringsecurity.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
