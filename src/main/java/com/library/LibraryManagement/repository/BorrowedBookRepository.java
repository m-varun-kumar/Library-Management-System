package com.library.LibraryManagement.repository;

import com.library.LibraryManagement.model.BorrowedBook;
import com.library.LibraryManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    List<BorrowedBook> findByStudentAndStatus(Student student, String status);
    List<BorrowedBook> findByStudent(Student student);
    long countByStudentAndStatus(Student student, String status);
}
