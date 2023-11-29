package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity    //what is the structure of my book table
@Table     //if u don't write any name then your class name is your table's name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id // denotes primary
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private  String bookName;
    private  int  price;
    private int noofpages;
    private double rating;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Boolean isAvailable;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNoofpages() {
        return noofpages;
    }

    public void setNoofpages(int noofpages) {
        this.noofpages = noofpages;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn
    private Author author;
    //connecting to transaction
    //book is parent to transaction
    //here we are doing bi directional mapping
    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();

}

