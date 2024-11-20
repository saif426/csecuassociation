package com.dev.csecu.service;

import com.dev.csecu.entity.Student;
import com.dev.csecu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

   public void saveMember(Student student)
   {
       this.studentRepository.save(student);
   }


    public boolean authenticate(long id, String password) {
        Student student = studentRepository.findStudentById(id);
        Student student1=studentRepository.findStudentBypassword(password);
        if(student1==null || student==null)
        {
            return false;
        }
            if(id==student.getId() && password.equals(student1.getPassword()))
            {
                return true;
            }
        return false;
    }
}

