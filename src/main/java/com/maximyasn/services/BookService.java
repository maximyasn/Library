package com.maximyasn.services;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import com.maximyasn.repositories.BookRepository;
import com.maximyasn.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Transactional
    public void setPersonById(int book_id, Person person) {
        bookRepository.setPersonByBookId(book_id, person);
    }

    public List<Person> getPersonList() {
        return personRepository.findAll();
    }

    public Optional<Person> getOwner(int bookId) {
        return bookRepository.findPersonByBookId(bookId);
    }

    @Transactional
    public void releaseBook(int bookId) {
        bookRepository.releaseBookByBookId(bookId);
    }

    public List<Book> findBooksByNamePrefix(String prefix) {
        return bookRepository.findBookByNameStartingWith(prefix);
    }
}
