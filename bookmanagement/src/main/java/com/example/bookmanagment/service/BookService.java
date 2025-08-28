package com.example.bookmanagment.service;

import com.example.bookmanagment.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private static final List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBookById(int id){
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean updateBook(int id, Book updatedBook){
        Book existingBook = getBookById(id);
        if(existingBook != null){
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteBook(int id){
        return books.stream().toList().removeIf(book -> book.getId().equals(id));
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book ->book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksCheaperThan(double price){
        return books.stream()
                .filter(book -> book.getPrice() < price)
                .toList();
    }
}
