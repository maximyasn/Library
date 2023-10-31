package com.maximyasn.entity;


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
public class Person {

    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name length must be between 2 and 30 characters")
    private String fullName;

    @Min(value = 1900, message = "Year can't be less than 1900")
    @Max(value = 2020, message = "Year can't be greater than 2020")
    @NotEmpty(message = "Year must not be empty")
    private Integer yearOfBirth;

    private List<Book> books;
}
