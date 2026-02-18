package com.library.LibraryManagement.dto;

import lombok.Data;

@Data
public class BorrowRequest {
    private String bookId;
    private String studentId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
