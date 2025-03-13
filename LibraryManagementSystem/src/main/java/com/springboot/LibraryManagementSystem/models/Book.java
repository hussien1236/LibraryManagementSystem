package com.springboot.LibraryManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;

    @OneToMany(mappedBy = "book")
    private List<BorrowingRecord> borrowingRecords;
    public Book(){}
    public Book(long id, String title, String author, int publicationYear, String isbn){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }
    public Long getId() {
        return id;
    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

//    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
//        this.borrowingRecords = borrowingRecords;
//    }
}
