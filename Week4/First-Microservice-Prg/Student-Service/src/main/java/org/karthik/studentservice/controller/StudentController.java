package org.karthik.studentservice.controller;

import org.karthik.studentservice.model.Department;
import org.karthik.studentservice.model.Student;
import org.karthik.studentservice.model.StudentDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/students/{id}/details")
    public StudentDetailsResponse getStudent(@PathVariable int id){
        Student student=new Student(id,"karthik",1);
        Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"
                        + student.getDepartmentId(),
                Department.class);


        return new StudentDetailsResponse(
                student.getId(),
                student.getName(),
                department
        );
    }
}
