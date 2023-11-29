package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.Librarycard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.Transactionstatus;
import com.example.Library.Management.System.Exceptions.*;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
@Service
public class TransactionService {
    @Autowired
    private static BookRepository bookRepository;
    @Autowired
    private static CardRepository cardRepository;
    @Autowired
    private static TransactionRepository transactionRepository;
    private static final Integer Max_Limit_Of_Books_Can_Be_Issued=3;
    private static final Integer Fine_Per_Day=5;
    public static String issuebook(Integer bookId, Integer cardno) throws Exception{
        Transaction transaction=new Transaction();
        //initially set all the transaction status as pending ,iske baad we have option either the issued or failure
        transaction.setTransactionstatus(Transactionstatus.Pending);
        //validation
              //valid bookid
        Optional<Book> bookOptional=bookRepository.findById(bookId);//bookid is not working here
        if(!bookOptional.isPresent()){
            throw new BookNotFoundException("This BookId is invalid");
        }
        Book book=bookOptional.get();
        if(!book.getIsAvailable()){
            throw new BookNotAvailableException("Book is Unavailable");
        }
            //valid cardno
        Optional<Librarycard> librarycardOptional=cardRepository.findById(cardno);
        if(!librarycardOptional.isPresent()){
            throw new LibraryCardInvalidException("This card is not valid");
        }
        Librarycard librarycard=librarycardOptional.get();
            //valid card status
        if(!librarycard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("This card is not Active");
        }
            //check if the number of books are within the limit
        if(librarycard.getNoofbooksissued()==Max_Limit_Of_Books_Can_Be_Issued){
            throw new MaximumLimitBooksException(Max_Limit_Of_Books_Can_Be_Issued + "is the maximum limit of books that can be issued");
        }
           //This is successcase
        transaction.setTransactionstatus(Transactionstatus.COMPLETED);
        librarycard.setNoofbooksissued(librarycard.getNoofbooksissued()+1);
        book.setIsAvailable(false);
            //add foreign keys
        transaction.setBook(book);
        transaction.setLibrarycard(librarycard);
            //saving all the entities
        book.getTransactionList().add(transaction);
        librarycard.getTransactionList().add(transaction);
        //save the entry u made
       // bookRepository.save(book);
        //cardRepository.save(librarycard);
        //above mentioned method of saving both the parents is the wrong way because it is saving twice which is wrong
        //so only save child table and all will be set
        transactionRepository.save(transaction);
        return "The book with " + bookId + "has been issued to card number" + cardno;

    }
    public static String returnbook(Integer bookId, Integer cardno){
        Book book= bookRepository.findById(bookId).get();
        Librarycard librarycard=cardRepository.findById(cardno).get();
        Transaction transaction=transactionRepository.findTransactionByBookAndLibrarycardAndTransactionstatus(book , librarycard,Transactionstatus.ISSUED);
        Date issuedate=transaction.getCreatedon();
        //now to find that for how many days was book kept nt student
        //this will be done using inbuilt function
        long milliseconds=Math.abs(System.currentTimeMillis()-issuedate.getTime());
        long days= TimeUnit.DAYS.convert(milliseconds , TimeUnit.MILLISECONDS);
        int fineamount=0;
         if(days>15){
             fineamount= Math.toIntExact((days - 15) * Fine_Per_Day);
         }
         //creating the obj
         Transaction newtransaction = new Transaction();
         //setting the variables
         newtransaction.setTransactionstatus(Transactionstatus.COMPLETED);
         newtransaction.setReturndate(new Date());
         newtransaction.setFine(fineamount);
         //set the foreign key
         newtransaction.setBook(book);
         newtransaction.setLibrarycard(librarycard);
         book.setIsAvailable(true);
         librarycard.setNoofbooksissued(librarycard.getNoofbooksissued()-1);
         //concept of bidirectional mapping
         book.getTransactionList().add(transaction);
         librarycard.getTransactionList().add(transaction);
         transactionRepository.save(transaction);
         return "book has been returned";
    }
}
