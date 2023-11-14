package com.maximyasn.services;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import com.maximyasn.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = bookRepository.findById(id).orElse(null);

        book.setId(id);
        if (bookToBeUpdated != null) {
            book.setPerson(bookToBeUpdated.getPerson());
        }
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll(boolean sortByYear) {
        if(sortByYear) {
            return bookRepository.findAll(Sort.by("year"));
        }
        return bookRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if(sortByYear) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        }
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }



    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void setPersonById(int book_id, Person person) {
        bookRepository.findById(book_id).ifPresent(book -> {
            book.setPerson(person);
            book.setPickUpTime(LocalDateTime.now());
        });
    }


    public Person getOwner(int bookId) {
        return bookRepository.findById(bookId).map(Book::getPerson).orElse(null);
    }

    @Transactional
    public void releaseBook(int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> {
            book.setPerson(null);
            book.setPickUpTime(null);
        });
    }

    public List<Book> findBooksByNamePrefix(String prefix) {
        return bookRepository.findByNameStartingWith(prefix);
    }

    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

}
