package org.karthik.springrestusingspringboot3.controller;
import jakarta.validation.Valid;
import org.karthik.springrestusingspringboot3.model.Student;
import org.karthik.springrestusingspringboot3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll(){
        List<Student> student= studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/students")
    public ResponseEntity<String> save(@Valid @RequestBody Student student){
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @PutMapping("/students")
    public ResponseEntity<String> update(@RequestBody Student student){
        studentService.update(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        studentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id){
        Student student=studentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/students/name/{name}")
    public ResponseEntity<List<Student>> getByName(@PathVariable String name){
        List<Student> student=studentService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
