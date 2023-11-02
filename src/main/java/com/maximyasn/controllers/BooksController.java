package com.maximyasn.controllers;

import com.maximyasn.dao.BookDao;
import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import com.maximyasn.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDao;

    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDao bookDao, BookValidator bookValidator) {
        this.bookDao = bookDao;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDao.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDao.update(id, book);
        return "redirect:/books";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("book", bookDao.show(id));
        Optional<Person> owner = bookDao.getOwner(id);
        if(owner.isPresent()) {
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("personList", bookDao.getPersonList());
        }
        return "books/show";
    }

    @PatchMapping("/{id}/set-person")
    public String setPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person) {
        System.out.println(person);
        bookDao.setPerson(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookDao.releaseBook(id);
        return "redirect:/books/" + id;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);

        return "redirect:/books";
    }

}
