package org.karthik.departmentservice.controller;

import org.karthik.departmentservice.model.Department;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable int id){
        return new Department(id,"Information Technology");
    }
}
