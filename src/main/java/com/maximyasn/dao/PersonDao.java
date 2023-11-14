//package com.maximyasn.dao;
//
//import com.maximyasn.entity.Book;
//import com.maximyasn.entity.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("insert into project1.public.person(full_name, year_of_birth)" +
//                            " values(?, ?)", person.getFullName(), person.getYearOfBirth());
//    }
//
//    public void update(int id, Person person) {
//        jdbcTemplate.update("update project1.public.person set full_name=?, year_of_birth=?" +
//                            " where id=?", person.getFullName(), person.getYearOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from project1.public.person where id=?", id);
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("select * from project1.public.person",
//                new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("select * from project1.public.person where id = ?",
//                new BeanPropertyRowMapper<>(Person.class),
//                id).get(0);
//    }
//
//    public Optional<Person> show(String name) {
//        return jdbcTemplate.query("select * from project1.public.person where full_name = ?",
//                        new BeanPropertyRowMapper<>(Person.class),
//                        name).stream()
//                .findAny();
//    }
//
//    public List<Book> getPersonBooks(int person_id) {
//        return jdbcTemplate
//                .query("select * from project1.public.book where person_id=?",
//                        new BeanPropertyRowMapper<>(Book.class), person_id);
//    }
//}
