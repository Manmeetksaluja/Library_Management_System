package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enum.Transactionstatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Enumerated(value = EnumType.STRING)
    private Transactionstatus transactionstatus;

    private Date returndate;
    private Integer fine;
    //created on will tell the issuedate
    @CreationTimestamp
    private Date createdon;//handles by spring automatically
    private Date lastmodifieson;
    //connect to book (transaction is the child table)
    @ManyToOne
    @JoinColumn
    private Book book;
    //connect to librarycard as well
    @ManyToOne
    @JoinColumn
    private Librarycard librarycard;
}
