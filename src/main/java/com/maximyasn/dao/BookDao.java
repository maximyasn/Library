//package com.maximyasn.dao;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
//public class BookDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    public void save(Book book) {
//        jdbcTemplate.update("insert into project1.public.book(name, author, year)" +
//                                 " values (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
//    }
//
//
//    public void update(int id, Book book) {
//        jdbcTemplate.update("update project1.public.book set name=?, author=?, year=?" +
//                                 " where id = ?", book.getName(), book.getAuthor(), book.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from project1.public.book where id = ?", id);
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("select * from project1.public.book",
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("select * from project1.public.book where id = ?",
//                new BeanPropertyRowMapper<>(Book.class), id).get(0);
//    }
//
//    public Optional<Book> show(String name) {
//        return jdbcTemplate.query("select * from project1.public.book where name = ?",
//                new BeanPropertyRowMapper<>(Book.class), name)
//                .stream()
//                .findAny();
//    }
//
//    public void setPerson(int book_id, Person person) {
//        jdbcTemplate.update("update project1.public.book set person_id = ? where id = ?",
//                person.getId(), book_id);
//    }
//
//    public List<Person> getPersonList() {
//        return jdbcTemplate.query("select * from project1.public.person",
//                new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Optional<Person> getOwner(int book_id) {
//        return jdbcTemplate.query("select p.* from project1.public.book b join project1.public.person p on b.person_id=p.id where b.id=?",
//                new BeanPropertyRowMapper<>(Person.class), book_id).stream().findAny();
//    }
//
//    public void releaseBook(int book_id) {
//        jdbcTemplate.update("update project1.public.book set person_id = null where id = ?",
//                book_id);
//    }
//}
