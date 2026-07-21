package org.karthik.jwt_working.controller;

import org.karthik.jwt_working.dto.RegisterRequest;
import org.karthik.jwt_working.model.User;
import org.karthik.jwt_working.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String addStudent(@RequestBody RegisterRequest request){
        User user=new User();
        user.setId(request.getId());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole().toUpperCase());
        userService.register(user);
        return "Registered Successfully";
    }
    @GetMapping("/students")
    public String getStudents(){

        return "Student List";
    }

    @PostMapping("/students")
    public String addStudent(){

        return "Student Added";
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(){

        return "Deleted";
    }
}
