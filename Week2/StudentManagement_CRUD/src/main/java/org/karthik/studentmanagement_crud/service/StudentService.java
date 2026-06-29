package org.karthik.studentmanagement_crud.service;

import org.karthik.studentmanagement_crud.model.Student;
import org.karthik.studentmanagement_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void delete(int id){
        studentRepository.delete(id);
    }

    public void update(int id,Student student){
        studentRepository.update(id,student);
    }
}
