package com.library.LibraryManagement.service;

import com.library.LibraryManagement.dto.BorrowRequest;
import com.library.LibraryManagement.model.Book;
import com.library.LibraryManagement.model.BorrowedBook;
import com.library.LibraryManagement.model.Student;
import com.library.LibraryManagement.repository.BookRepository;
import com.library.LibraryManagement.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final StudentService studentService;

    @Autowired
    public BorrowService(BorrowedBookRepository borrowedBookRepository,
                         BookRepository bookRepository,
                         StudentService studentService) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookRepository = bookRepository;
        this.studentService = studentService;
    }

    @Transactional
    public BorrowedBook borrowBook(BorrowRequest request) {
        Student student = studentService.getStudentByStudentId(request.getStudentId());
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if book is available
        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        // Decrease available quantity
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        // Create borrowed record
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setStudent(student);
        borrowedBook.setBook(book);
        borrowedBook.setIssueDate(LocalDate.now());
        borrowedBook.setDueDate(LocalDate.now().plusDays(15)); // 15 days initial period
        borrowedBook.setStatus("BORROWED");
        borrowedBook.setExtended(false);

        return borrowedBookRepository.save(borrowedBook);
    }

    @Transactional
    public void extendBook(Long borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new RuntimeException("Borrowed book record not found"));

        // Check if book is already extended
        if (borrowedBook.getExtended()) {
            throw new RuntimeException("Book has already been extended");
        }

        // Check if book is not yet returned
        if (!"BORROWED".equals(borrowedBook.getStatus())) {
            throw new RuntimeException("Cannot extend a book that is not currently borrowed");
        }

        // Extend the book
        borrowedBook.extend();
        borrowedBookRepository.save(borrowedBook);
    }

    @Transactional
    public void returnBook(Long borrowedBookId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new RuntimeException("Borrowed book record not found"));

        // Update book quantity
        Book book = borrowedBook.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);

        // Calculate fine
        double fine = borrowedBook.calculateFine();

        // Update borrowed record
        borrowedBook.setReturnDate(LocalDate.now());
        borrowedBook.setStatus("RETURNED");
        borrowedBookRepository.save(borrowedBook);

        // You can add fine recording logic here if you want to store fines in a separate table
        if (fine > 0) {
            System.out.println("Fine calculated for student " + borrowedBook.getStudent().getStudentId() + ": ₹" + fine);
            // TODO: Store fine in a fines table if needed
        }
    }

    public List<BorrowedBook> getBorrowedBooks(String studentId) {
        Student student = studentService.getStudentByStudentId(studentId);
        return borrowedBookRepository.findByStudentAndStatus(student, "BORROWED");
    }

    public long getTotalBorrowedCount(String studentId) {
        Student student = studentService.getStudentByStudentId(studentId);
        return borrowedBookRepository.countByStudentAndStatus(student, "BORROWED");
    }

    public double getTotalFines(String studentId) {
        Student student = studentService.getStudentByStudentId(studentId);
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByStudentAndStatus(student, "BORROWED");

        return borrowedBooks.stream()
                .mapToDouble(BorrowedBook::calculateFine)
                .sum();
    }
}