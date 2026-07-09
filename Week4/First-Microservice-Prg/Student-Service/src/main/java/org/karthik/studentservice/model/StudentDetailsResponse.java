package org.karthik.studentservice.model;

public class StudentDetailsResponse {

    private int id;
    private String name;
    private Department department;

    public StudentDetailsResponse(int id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }
}
