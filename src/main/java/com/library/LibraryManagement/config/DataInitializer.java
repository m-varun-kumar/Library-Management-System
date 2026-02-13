package com.library.LibraryManagement.config;

import com.library.LibraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public DataInitializer(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        bookService.initializeBooks();
        System.out.println("✅ Database initialized with books!");
    }
}
