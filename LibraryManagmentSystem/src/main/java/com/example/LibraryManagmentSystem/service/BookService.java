package com.example.LibraryManagmentSystem.service;

import com.example.LibraryManagmentSystem.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();
    private List<Book> reservations = new ArrayList<>();

    public BookService() {
        books.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", true));
        books.add(new Book(2L, "1984", "George Orwell", "9780451524935", true));
        books.add(new Book(3L, "To Kill a Mockingbird", "Harper Lee", "9780061120084", true));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId() == (id))
                .findFirst()
                .orElse(null);
    }

    public void reserveBook(Long id) {
        Book book = getBookById(id);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            reservations.add(book);
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(Long id, Book book) {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setIsbn(book.getIsbn());
        }
    }

    public List<Book> getReservations() {
        return reservations;
    }

    public void approveReservation(Long id, boolean approve) {
        Book book = getBookById(id);
        if (book != null && !book.isAvailable()) {
            if (approve) {
                book.setAvailable(true);
            } else {
            }
            reservations.remove(book);
        }
    }
}
