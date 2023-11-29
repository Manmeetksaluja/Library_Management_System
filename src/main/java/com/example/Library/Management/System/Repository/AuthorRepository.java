package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Integer>{
     //find the list of all the authors greater than or equal to particular age
    List<Author> findAuthorByAgeGreaterThanEqual(Integer age);
    //we can use it for multiple attributes
    //@Query(value="select ")
    List<Author> findAuthorByAgeGreaterThanEqualAndRatingsEquals(Integer age , double rating);

}
