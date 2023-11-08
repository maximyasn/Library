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
public class PersonService {

    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }
    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByFullName(name);
    }

    public List<Book> getPersonBooks(Person person) {
        return bookRepository.findByPerson(person);
    }
}
