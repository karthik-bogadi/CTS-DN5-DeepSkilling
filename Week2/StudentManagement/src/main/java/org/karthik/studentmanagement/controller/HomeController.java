package org.karthik.studentmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "Hello Spring Boot";
    }
    @GetMapping("/student")

    public String study(){
        return "student is studying";
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable  int id){
        return "student with id"+" "+id;
    }

    @GetMapping("/square")
    public String getSquare(@RequestParam int number){
        return "square is"+number*number;
    }
}
