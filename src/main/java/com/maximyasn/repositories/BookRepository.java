package com.maximyasn.repositories;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByName(String name);
    List<Book> findByPerson(Person person);

    @Modifying
    @Query("update Book b set b.person = :person where b.id = :bookId")
    void setPersonByBookId(@Param("bookId") int bookId,
                           @Param("person") Person person);


    @Query("select b.person from Book b where b.id=:bookId")
    Optional<Person> findPersonByBookId(@Param("bookId") int bookId);

    @Modifying
    @Query("update Book b set b.person = null where b.id = :bookId")
    void releaseBookByBookId(@Param("bookId") int bookId);
}
