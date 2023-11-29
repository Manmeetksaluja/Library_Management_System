package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.Librarycard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enum.Transactionstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Integer> {
    Transaction findTransactionByBookAndLibrarycardAndTransactionstatus(Book book , Librarycard librarycard , Transactionstatus transactionStatus);
}
