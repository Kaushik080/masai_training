package com.example.LibraryManagmentSystem.controller;

import com.example.LibraryManagmentSystem.model.Book;
import com.example.LibraryManagmentSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<String> getAllUsers() {
        return List.of("admin", "librarian", "student", "guest");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reports")
    public String getAdminReports() {
        return "Admin reports data here.";
    }
}

