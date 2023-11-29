package com.example.Library.Management.System.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity//this is the structure that will be reflected in database
@Table(name = "student")//this class will have the table whose name is student
@Getter
@Setter
@AllArgsConstructor//for all constructors
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;//This will behave as a primary key
    private String name;
    private int age;
    @Column(name="contactno" , unique = true , nullable = false)//it is used to change the name and add some properties like unique and many more
    private String mobilenumber;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "should be a valid email address")
    private String emailId;


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Librarycard getLibrarycard() {
        return librarycard;
    }

    public void setLibrarycard(Librarycard librarycard) {
        this.librarycard = librarycard;
    }
    //this is parent table
    //this is bidirectional mapping

    @OneToOne(mappedBy = "studentvariable" , cascade = CascadeType.ALL)//whatever different types of methods are there ,just cascade all types of methods to child class
    private Librarycard librarycard;
}
