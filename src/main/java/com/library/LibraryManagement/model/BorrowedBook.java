package com.library.LibraryManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "borrowed_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(nullable = false)
    private String status; // BORROWED, RETURNED

    @Column(name = "extended")
    private Boolean extended = false; // Track if book has been extended

    // Fine calculation constants
    private static final int INITIAL_BORROW_DAYS = 15;
    private static final int EXTENSION_DAYS = 10;
    private static final double FINE_PER_WEEK = 20.0;

    @PrePersist
    protected void onCreate() {
        if (issueDate == null) {
            issueDate = LocalDate.now();
        }
        if (dueDate == null) {
            dueDate = issueDate.plusDays(INITIAL_BORROW_DAYS); // 15 days borrowing period
        }
        if (status == null) {
            status = "BORROWED";
        }
        if (extended == null) {
            extended = false;
        }
    }

    // Calculate fine based on overdue days
    public double calculateFine() {
        if (dueDate == null) {
            return 0.0;
        }

        LocalDate today = returnDate != null ? returnDate : LocalDate.now();
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, today);

        if (daysOverdue <= 0) {
            return 0.0;
        }

        // Calculate weeks overdue (rounded up)
        long weeksOverdue = (daysOverdue + 6) / 7; // Ceiling division
        return weeksOverdue * FINE_PER_WEEK;
    }

    // Extend the book due date
    public void extend() {
        if (!extended && dueDate != null) {
            dueDate = dueDate.plusDays(EXTENSION_DAYS);
            extended = true;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getExtended() {
        return extended;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }
}