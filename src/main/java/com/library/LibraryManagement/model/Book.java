package com.library.LibraryManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;   // CS001, MBA001

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    private String edition;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer availableQuantity;

    private String image;

    public Book() {

    }

    public Book(String id, String name, String author, String edition, Integer quantity, Integer availableQuantity, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}










//package com.library.LibraryManagement.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "books")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Book {
//
//    @Id
//    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String author;
//
//    @Column(name = "edition")
//    private String edition; // ✅ NEW FIELD ADDED
//
//    @Column(nullable = false)
//    private Integer quantity;
//
//    @Column(nullable = false)
//    private Integer availableQuantity;
//
//    @Column(name = "image")
//    private String image;
//
//    public Book() {
//    }
//
//    // ✅ UPDATED CONSTRUCTOR WITH EDITION
//    public Book(String id, String name, String author, String edition, Integer quantity, Integer availableQuantity) {
//        this.id = id;
//        this.name = name;
//        this.author = author;
//        this.edition = edition;
//        this.quantity = quantity;
//        this.availableQuantity = availableQuantity;
//    }
//
//    // ✅ BACKWARD COMPATIBILITY CONSTRUCTOR (without edition)
//    public Book(String id, String name, String author, Integer quantity, Integer availableQuantity) {
//        this(id, name, author, null, quantity, availableQuantity);
//    }
//
//    // Getters and Setters
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getEdition() {
//        return edition;
//    }
//
//    public void setEdition(String edition) {
//        this.edition = edition;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Integer getAvailableQuantity() {
//        return availableQuantity;
//    }
//
//    public void setAvailableQuantity(Integer availableQuantity) {
//        this.availableQuantity = availableQuantity;
//    }
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//}