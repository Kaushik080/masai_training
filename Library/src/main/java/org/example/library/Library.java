package org.example.library;

import java.util.List;

public class Library {
    private String name;
    private List<Book> books;
    private Member member;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void displayLibraryDetails() {
        System.out.println("Library: " + name);
        System.out.println("Member: " + member);
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
