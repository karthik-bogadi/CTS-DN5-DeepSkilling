package org.karthik.springdatajpa.service;

import org.karthik.springdatajpa.model.Student;
import org.karthik.springdatajpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    public StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }
    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    public Optional<Student> findById(int id){
        return studentRepository.findById(id);
    }
    public void deleteById(int id){
        studentRepository.deleteById(id);
    }
}

