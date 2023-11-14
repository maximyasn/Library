package com.maximyasn.repositories;

import com.maximyasn.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWith(String title);

    Optional<Book> findByName(String name);
}
