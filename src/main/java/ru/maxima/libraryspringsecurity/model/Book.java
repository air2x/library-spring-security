package ru.maxima.libraryspringsecurity.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Назавние не должно быть пустым")
    @Column(name = "name_of_book")
    private String nameOfBook;

    @NotEmpty(message = "Автор не должен быть пустым")
    @Column(name = "author_of_book")
    private String authorOfBook;

    @Column(name = "year_of_writing_book")
    private int yearOfWritingBook;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public Book(int id, String nameOfBook, String authorOfBook, int yearOfWritingBook, Person owner) {
        this.id = id;
        this.nameOfBook = nameOfBook;
        this.authorOfBook = authorOfBook;
        this.yearOfWritingBook = yearOfWritingBook;
        this.owner = owner;
    }
}
