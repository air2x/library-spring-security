package ru.maxima.libraryspringsecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть не меньше 2 и не больше 50 символов")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Год рождения не может быть меньше 1900 г.")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> books;

    @Column(name = "username")
    @Size(min = 2, max = 50, message = "Логин не должен быть меньше 2")
    private String username;

    @Column(name = "password")
    @Size(min = 2, max = 500, message = "Пароль не должен быть меньше 2")
    private String password;

    @Column(name = "role")
    private String role;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth, String username) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.username = username;
    }
}
