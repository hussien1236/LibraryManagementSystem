package com.springboot.LibraryManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Patron {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNb;

    @OneToMany(mappedBy = "patron")
    private List<BorrowingRecord> borrowingRecords;

    public Patron(Long id, String name, String email, String phoneNb) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNb = phoneNb;
    }
    public Patron(){}
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNb() {
        return phoneNb;
    }

    public void setPhoneNb(String phoneNb) {
        this.phoneNb = phoneNb;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }
//
//    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
//        this.borrowingRecords = borrowingRecords;
//    }
}
