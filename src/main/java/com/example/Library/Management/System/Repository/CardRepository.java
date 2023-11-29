package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Librarycard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Librarycard, Integer> {
//in repository layer we are just using hibernate class and using its functions

}
