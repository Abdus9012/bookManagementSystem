//business layer logic code of this project
package com.example.bookManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookManagementSystem.model.Book;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public String addBooks(Book book){
        books.add(book);
        return "Book added successfully";
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public String deleteBooks(int id){
        books.removeIf(book -> book.getId() == id);
        return "Book deleted successfully";
    }

}
