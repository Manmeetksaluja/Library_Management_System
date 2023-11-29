package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity<String> addauthor(@RequestBody Author author){
             String result= authorService.addauthor(author);
             return new ResponseEntity<>(result, HttpStatus.OK);
    }
   @GetMapping("/findAllauthorsname")
   public List<String> getAllauthorsname(){
             return authorService.getAllAuthorname();
   }
   @GetMapping("/getauthorbyid/{id}")
   public ResponseEntity getauthor(@PathVariable ("id") Integer id){
       try{
              Author author=authorService.getauthorbyid(id);
              return new ResponseEntity(author , HttpStatus.OK);
       }
       catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
       }
   }
   @GetMapping("/getbooknamelist")
    public ResponseEntity getbooknamelist(@RequestParam ("authorId") Integer authorId) throws Exception {
          List<String> booknames=authorService.getbooknamelist(authorId);
          return new ResponseEntity(booknames , HttpStatus.OK);
   }

}
