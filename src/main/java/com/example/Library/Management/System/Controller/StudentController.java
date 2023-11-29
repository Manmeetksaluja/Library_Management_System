package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addstudent(@RequestBody Student student){
           String res= studentService.addstudent(student);
          return new ResponseEntity(res , HttpStatus.OK);
    }

}
