package com.library.LibraryManagement.service;

import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> searchBooks(String query) {
        return bookRepository
                .findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public Book createBook(Book book) {
        book.setAvailableQuantity(book.getQuantity());
        return bookRepository.save(book);
    }

    public void initializeBooks() {
        if (bookRepository.count() == 0) {
            List<Book> books = Arrays.asList(
                    new Book(null, "Engineering Mathematics", "B.S. Grewal", 5, 5),
                    new Book(null, "Data Structures", "Sahni", 4, 4),
                    new Book(null, "Operating System", "Galvin", 6, 6),
                    new Book(null, "Computer Networks", "Kurose", 3, 3),
                    new Book(null, "DBMS", "Korth", 7, 7),
                    new Book(null, "Java Programming", "James Gosling", 5, 5),
                    new Book(null, "Python", "Guido", 4, 4),
                    new Book(null, "C Programming", "Dennis Ritchie", 6, 6),
                    new Book(null, "Software Engg", "Pressman", 5, 5),
                    new Book(null, "Compiler Design", "Aho", 2, 2),
                    new Book(null, "Microprocessor", "Gaonkar", 3, 3),
                    new Book(null, "Digital Logic", "Morris Mano", 4, 4),
                    new Book(null, "AI", "Russell", 5, 5),
                    new Book(null, "ML", "Tom Mitchell", 6, 6),
                    new Book(null, "Cloud", "Buyya", 3, 3),
                    new Book(null, "Cyber Security", "Stallings", 5, 5),
                    new Book(null, "Big Data", "Viktor", 2, 2),
                    new Book(null, "Data Mining", "Han", 4, 4),
                    new Book(null, "Web Tech", "Jeffrey", 6, 6),
                    new Book(null, "Spring", "Craig Walls", 5, 5),
                    new Book(null, "React", "Jordan", 3, 3),
                    new Book(null, "System Design", "Alex Xu", 4, 4),
                    new Book(null, "Linux", "Love", 6, 6),
                    new Book(null, "IoT", "Arshdeep", 2, 2),
                    new Book(null, "Blockchain", "Narayanan", 5, 5),
                    new Book(null, "Embedded", "Mazidi", 3, 3),
                    new Book(null, "AI Modern", "Russell", 4, 4),
                    new Book(null, "ML Adv", "Mitchell", 2, 2),
                    new Book(null, "Cloud Native", "Sam", 3, 3),
                    new Book(null, "Data Science", "Field", 5, 5)
            );
            bookRepository.saveAll(books);
        }
    }
}