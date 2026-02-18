package com.library.LibraryManagement.service;

import com.library.LibraryManagement.dto.LoginRequest;
import com.library.LibraryManagement.dto.SignupRequest;
import com.library.LibraryManagement.model.Student;
import com.library.LibraryManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student signup(SignupRequest request) {
        // Validate Student ID - must be between 10-20 characters (alphanumeric)
        if (request.getStudentId() == null || request.getStudentId().trim().isEmpty()) {
            throw new RuntimeException("Student ID is required");
        }

        String studentId = request.getStudentId().trim();

        // ✅ CHANGED: Allow 10-20 characters instead of exactly 10
        if (studentId.length() < 10 || studentId.length() > 20) {
            throw new RuntimeException("Student ID must be between 10-20 characters");
        }

        if (!studentId.matches("^[a-zA-Z0-9]{10,20}$")) {
            throw new RuntimeException("Student ID must contain only letters and numbers (no spaces or special characters)");
        }

        // Check if student ID already exists
        if (studentRepository.existsByStudentId(studentId)) {
            throw new RuntimeException("Student ID already exists");
        }

        // Check if email already exists
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setStudentId(studentId);
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword()); // In production, hash this!

        return studentRepository.save(student);
    }

    public Student login(LoginRequest request) {
        String identifier = request.getStudentId().trim(); // This will actually be email now

        if (identifier == null || identifier.isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        Student student;

        // Check if input looks like an email
        if (identifier.contains("@")) {
            // Login with email
            student = studentRepository.findByEmail(identifier)
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        } else {
            // Fallback: try student ID
            if (identifier.length() < 10 || identifier.length() > 20) {
                throw new RuntimeException("Invalid email or student ID format");
            }
            student = studentRepository.findByStudentId(identifier)
                    .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        }

        if (!student.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return student;
    }

    public Student getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}