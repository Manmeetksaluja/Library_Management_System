package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.Librarycard;
import com.example.Library.Management.System.Enum.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
     //this internally creates a sql query
     List<Book> findBookByGenre(Genre genre);
}
