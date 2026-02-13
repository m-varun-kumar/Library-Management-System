package com.library.LibraryManagement.dto;

import lombok.Data;

@Data
public class BorrowRequest {
    private Long bookId;
    private String studentId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
