package com.maximyasn.util;

import com.maximyasn.dao.BookDao;
import com.maximyasn.entity.Book;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        Book book = (Book) target;

        if(book.getId() == 0 && bookDao.show(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "Book with this name already exists");
        }
    }
}
