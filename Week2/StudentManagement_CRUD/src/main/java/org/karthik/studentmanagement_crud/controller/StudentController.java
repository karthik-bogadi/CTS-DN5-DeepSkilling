package org.karthik.studentmanagement_crud.controller;

import org.karthik.studentmanagement_crud.model.Student;
import org.karthik.studentmanagement_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(){
        return "Welcome to the student management system";
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public String addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return "Student added successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.delete(id);
        return "deleted successfully";
    }
    @PutMapping("/{id}")
    public String update(@PathVariable int id,@RequestBody Student student){
        studentService.update(id,student);
        return "Updated successfully";
    }
}
