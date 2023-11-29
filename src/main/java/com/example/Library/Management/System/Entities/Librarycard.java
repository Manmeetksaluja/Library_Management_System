package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Librarycard {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)//this will autogenerate teh cardnumber which is the primary key
    private Integer cardno;
 @Enumerated(value = EnumType.STRING)//tells that the data member is of type enum , and that enum is of type strings
 private CardStatus cardStatus;
 private String nameoncard;
private Integer noofbooksissued;
    public Integer getCardno() {
        return cardno;
    }

    public void setCardno(Integer cardno) {
        this.cardno = cardno;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getNameoncard() {
        return nameoncard;
    }

    public void setNameoncard(String nameoncard) {
        this.nameoncard = nameoncard;
    }

    public Student getStudentvariable() {
        return studentvariable;
    }

    public void setStudentvariable(Student studentvariable) {
        this.studentvariable = studentvariable;
    }

    /*
     Library card needs to be connected to student table
      */
    //this is child table
    //this is unidirectional mapping
    @OneToOne//type of mapping
    @JoinColumn //to denote the entity to which we are connecting
    //primary key of student entity is added to this library card table as a foreign key
    private Student studentvariable; //object of student
//Now connect to transaction
    @OneToMany(mappedBy = "librarycard" , cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
}
