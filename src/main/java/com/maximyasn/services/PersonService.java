package com.maximyasn.services;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import com.maximyasn.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
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

    @Transactional
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

    public List<Book> getPersonBooks(int id) {
        Optional<Person> person = personRepository.findById(id);

        if(person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book -> {
                long diffInMillis = Duration.between(book.getPickUpTime(), LocalDateTime.now()).toMillis();
                if(diffInMillis > 864000000)
                    book.setOverdue(true);
            });

            return person.get().getBooks();
        }
        return Collections.emptyList();
    }

}
