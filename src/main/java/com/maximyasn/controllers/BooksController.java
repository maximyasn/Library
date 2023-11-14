package com.maximyasn.controllers;

import com.maximyasn.entity.Book;
import com.maximyasn.entity.Person;
import com.maximyasn.services.BookService;
import com.maximyasn.services.PersonService;
import com.maximyasn.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    private final PersonService personService;

    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookService bookService, PersonService personService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam(value = "sort_by_age", required = false) boolean sortByYear,
                        Model model) {

        if(page == null || books_per_page == null) {
            model.addAttribute("books", bookService.findAll(sortByYear));
        } else {
            model.addAttribute("books", bookService.findWithPagination(page, books_per_page, sortByYear));
        }

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
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
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

        bookService.update(id, book);
        return "redirect:/books";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model) {
        model.addAttribute("book", bookService.findById(id));
        Person owner = bookService.getOwner(id);
        if(owner != null) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("personList", personService.findAll());
        }
        return "books/show";
    }

    @PatchMapping("/{id}/set-person")
    public String setPerson(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person) {
        bookService.setPersonById(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @GetMapping("/search-for")
    public String checkForBooksSearch(@RequestParam("prefix") String prefix, Model model) {
        List<Book> checkedBooks = bookService.findBooksByNamePrefix(prefix);
        model.addAttribute("checkedBooks", checkedBooks);
        return "books/search";
    }

}
