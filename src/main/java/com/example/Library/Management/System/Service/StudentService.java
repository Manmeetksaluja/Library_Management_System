package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired //used to make beans (objects)
    private StudentRepository studentRepository;

    public String addstudent(Student student){
        studentRepository.save(student);
        return "Student has been saved to database";
    }

}
