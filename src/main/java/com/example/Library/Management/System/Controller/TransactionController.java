package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Service.TransactionService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
      @Autowired
      private TransactionService transactionService;
      @PostMapping("/issuebook/{bookId}/{cardno}")
      private ResponseEntity issuebook(@PathVariable ("bookId") Integer bookId , @PathVariable ("cardno") Integer cardno){
               try{
                    String result = TransactionService.issuebook(bookId , cardno);
                    return new ResponseEntity(result , HttpStatus.OK);
               }catch (Exception e){
                     return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
               }
      }
    @PostMapping("/returnbook/{bookId}/{cardno}")
    private ResponseEntity returnbook(@PathVariable ("bookId") Integer bookId , @PathVariable ("cardno") Integer cardno){
        try{
            String result = TransactionService.returnbook(bookId , cardno);
            return new ResponseEntity(result , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
