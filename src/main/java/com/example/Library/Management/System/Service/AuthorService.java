package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
public String addauthor(Author author){
    authorRepository.save(author);
    return "Author has been added succesfully";
}
public List<String> getAllAuthorname(){
    List<Author> authorslist=authorRepository.findAll();
    Optional<Author> optionalAuthor= authorRepository.findById(1);
    Author author=optionalAuthor.get();
    List<String> authorsName=new ArrayList<>();
    for(Author a:authorslist){
        authorsName.add(a.getAuthorName());//my getauthorname getter is not working here
    }
    return authorsName;
}
public Author getauthorbyid(Integer id) throws Exception{
    //optional is used when there is posssiblity that we have a value or we do not have a value
    Optional<Author> authorOptional = authorRepository.findById(id);
    if(!authorOptional.isPresent()){
        //throws exception
        //ispresent function is checking if the value is null or not
        throw new Exception("This entry is invalid");
    }
    Author author=authorOptional.get();
    return author;
}
    public List<String> getbooknamelist(Integer authorId) throws Exception{
        //first we will find the author entity from the authorid
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author ID is invalid");
        }
        Author author=authorOptional.get();
        List<String> booklistnames=new ArrayList<>();
        List<Book> books = author.getBookList();
        for(Book book:books){
            booklistnames.add(book.getBookName());
        }
        return  booklistnames;
    }
}
