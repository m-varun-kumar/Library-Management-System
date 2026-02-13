package com.library.LibraryManagement.controller;

import com.library.LibraryManagement.dto.BorrowRequest;
import com.library.LibraryManagement.model.BorrowedBook;
import com.library.LibraryManagement.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin(origins = "*")
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            BorrowedBook borrowedBook = borrowService.borrowBook(request);
            return ResponseEntity.ok(borrowedBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            borrowService.returnBook(id);
            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<BorrowedBook>> getBorrowedBooks(@PathVariable String studentId) {
        return ResponseEntity.ok(borrowService.getBorrowedBooks(studentId));
    }

    @GetMapping("/count/{studentId}")
    public ResponseEntity<Long> getBorrowedCount(@PathVariable String studentId) {
        return ResponseEntity.ok(borrowService.getTotalBorrowedCount(studentId));
    }

    @PutMapping("/extend/{id}")
    public ResponseEntity<?> extendBook(@PathVariable Long id) {
        try {
            borrowService.extendBook(id);
            return ResponseEntity.ok("Book extended successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
