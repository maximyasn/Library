package com.maximyasn.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name length must be between 2 and 30 characters")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Year can't be less than 1900")
    @Max(value = 2020, message = "Year can't be greater than 2020")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> books;
}
