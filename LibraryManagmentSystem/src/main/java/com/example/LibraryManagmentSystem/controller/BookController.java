package com.example.LibraryManagmentSystem.controller;

import com.example.LibraryManagmentSystem.model.Book;
import com.example.LibraryManagmentSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    public List<Book> listBooks() {
        return bookService.getAllBooks();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/{id}")
    public Book viewBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{id}/reserve")
    public void reserveBook(@PathVariable Long id) {
        bookService.reserveBook(id);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/reservations")
    public List<Book> viewReservations() {
        return bookService.getReservations();
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping("/reservations/{id}/approve")
    public void approveReservation(@PathVariable Long id, @RequestParam boolean approve) {
        bookService.approveReservation(id, approve);
    }
}
