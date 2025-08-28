package com.example.bookmanagment.controller;

import com.example.bookmanagment.service.BookService;
import com.example.bookmanagment.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Book added successfully";
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Integer id, @RequestBody Book book) {
        boolean updated = bookService.updateBook(id, book);
        return updated ? "Book updated successfully" : "Book not found";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted ? "Book deleted successfully" : "Book not found";
    }

    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/filter")
    public List<Book> getBooksCheaperThan(@RequestParam double price) {
        return bookService.getBooksCheaperThan(price);
    }
}
