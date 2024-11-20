package com.dev.csecu.repository;

import com.dev.csecu.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

Student findStudentById(long id);
Student findStudentBypassword(String password);



}
