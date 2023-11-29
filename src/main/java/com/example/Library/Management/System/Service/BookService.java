package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public String addbook (Book book , Integer authorId) throws Exception{
        //authorid se author ki poori info nikal lenge
       //or book ki info to postman se hi mil jayegi kyuki book ka poora object hi aaya hai input mai
        //to author ki info nikalo author ki id se
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()){
               throw new AuthorNotFoundException("Author ID is invalid");
        }
        Author author=authorOptional.get();//to get author object
        book.setAuthor(author);
        author.getBookList().add(book);
        //now book n author both have been modified
        //now just add author entity and book will be automatically saved due to bidirectional mapping
        authorRepository.save(author);
        return "Book has been added to the DB";
    }
    public List<String> booksbygenre(Genre  genre){
        //other than using optional we can also use this ,bt using optional is a good practice
        List<Book> bookList=bookRepository.findBookByGenre(genre);
        List<String > booknames=new ArrayList<>();
        for(Book b:bookList){
            booknames.add(b.getBookName());
        }
        return booknames;
    }

}
