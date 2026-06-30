package org.karthik.springdatajpa.controller;
import org.karthik.springdatajpa.model.Student;
import org.karthik.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    public StudentService studentService;
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
    @PostMapping("/students")
    public String addStudent(@RequestBody Student student){
        studentService.save(student);
        return "Added successfully";
    }
    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable int id){
        return studentService.findById(id);
    }

    @PutMapping("/students")
    public String update(@RequestBody Student student){
        studentService.save(student);
        return "updated successfully";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable int id){
        studentService.deleteById(id);
        return "Student deleted successfully";
    }
}
