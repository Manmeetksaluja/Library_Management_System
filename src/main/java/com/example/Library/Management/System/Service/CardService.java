package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entities.Librarycard;
import com.example.Library.Management.System.Entities.Student;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
     @Autowired
    public CardRepository cardRepository;
     @Autowired
     public StudentRepository studentRepository;
     public Librarycard generatedcard(){
         Librarycard card=new Librarycard();
         //before saving anything to database we need to first set its attribute
         //this information is that we need at initial stage ,and other attributes like name would be added when card will be associated with some student
         card.setCardStatus(CardStatus.NEW);
         card=cardRepository.save(card);
         return card;
     }
     public String associatewithtudent(Integer studentId , Integer cardno){
         //i have pk of both the tables
         //i need the entities to set the attributes
         //aisai smjho ki aapne ek khali card utha liya hai jiska card number cardno hai
         Optional<Student> studentOptional = studentRepository.findById(studentId);
         Student student = studentOptional.get();
         //aisai socho ki aapke pass ek student khada hai jo apna naya lib card banvaana chahata hai uski studenid jo hai vo studentId hai
         Optional<Librarycard> librarycardOptional=cardRepository.findById(cardno);
         Librarycard librarycard=librarycardOptional.get();
         //now set the information
         librarycard.setCardStatus(CardStatus.ACTIVE);
         //ab studentid se aapne student ki info le li hai
         librarycard.setNameoncard(student.getName());
         librarycard.setStudentvariable(student);
         //ab student mai bhi set kerna padega library card ,tabhi to dono databases mai consistancy aayegi
         student.setLibrarycard(librarycard);
         //here we are saving the student repository because it is bidirectional mapping and student is the parent one ,to agr student mai save hoga to library mai apne aap save ho jayega
//so we do not need to libcard entity explicitly
         studentRepository.save(student);
         return "card with" + cardno +"has been associated to student with " + studentId;
     }
}
