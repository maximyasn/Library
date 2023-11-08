package com.maximyasn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name length must be between 1 and 100")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author must not be empty")
    @Size(min = 1, max = 100, message = "Author's name length must be between 1 and 100")
    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private Integer year;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
