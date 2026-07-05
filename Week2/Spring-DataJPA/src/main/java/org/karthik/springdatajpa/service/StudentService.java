package org.karthik.springdatajpa.service;

import org.karthik.springdatajpa.exception.StudentAlreadyExistsException;
import org.karthik.springdatajpa.exception.StudentNotFoundException;
import org.karthik.springdatajpa.model.Student;
import org.karthik.springdatajpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    public StudentRepository studentRepository;

    public void save(Student student){
        int id=student.getId();
        if(studentRepository.existsById(id)){
            throw new StudentAlreadyExistsException("Student Already Exists handled By Karthik");
        }
        studentRepository.save(student);
    }
    public List<Student> findAll(){

        return studentRepository.findAll();
    }
    public Student findById(int id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found"));

    }
    public void deleteById(int id){

        studentRepository.deleteById(id);
    }
}

