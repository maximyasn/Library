package com.maximyasn.dao;

import com.maximyasn.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Book book) {
        jdbcTemplate.update("insert into project1.public.book(name, author, person_id)" +
                                 " values (?, ?, ?)", book.getName(), book.getAuthor(), book.getPerson().getId());
    }


    public void update(int id, Book book) {
        jdbcTemplate.update("update project1.public.book set name=?, author=?, person_id=?" +
                                 " where id = ?", book.getName(), book.getAuthor(), book.getPerson().getId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from project1.public.book where id = ?", id);
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from project1.public.book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from project1.public.book where id = ?",
                new BeanPropertyRowMapper<>(Book.class), id).get(0);
    }

    public Optional<Book> show(String name) {
        return jdbcTemplate.query("select * from project1.public.book where id = ?",
                new BeanPropertyRowMapper<>(Book.class), name)
                .stream()
                .findAny();
    }
}
