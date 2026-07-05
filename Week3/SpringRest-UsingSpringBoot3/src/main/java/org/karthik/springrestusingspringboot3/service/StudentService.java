package org.karthik.springrestusingspringboot3.service;

import org.karthik.springrestusingspringboot3.exception.StudentAlreadyExistsException;
import org.karthik.springrestusingspringboot3.exception.StudentNotFoundException;
import org.karthik.springrestusingspringboot3.model.Student;
import org.karthik.springrestusingspringboot3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAll(){

        return studentRepository.findAll();
    }

    public void save(Student student){
        int id=student.getId();
        if(studentRepository.existsById(id)){
            throw new StudentAlreadyExistsException("Student with ID Already exists");
        }
        studentRepository.save(student);
    }

    public void deleteById(int id){
        studentRepository.deleteById(id);
    }
    public void update(Student student){
        studentRepository.save(student);
    }

    public Student findById(int id){
        return studentRepository.findById(id).
                orElseThrow(()->new StudentNotFoundException("student not found Thrown by Developer"));
    }

    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
}
