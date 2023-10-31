package com.maximyasn.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 100, message = "Name length must be between 1 and 100")
    private String name;

    @NotEmpty(message = "Author must not be empty")
    @Size(min = 1, max = 100, message = "Author's name length must be between 1 and 100")
    private String author;

    @NotEmpty(message = "Year must not be empty")
    private Integer year;

    private Person person;
}
