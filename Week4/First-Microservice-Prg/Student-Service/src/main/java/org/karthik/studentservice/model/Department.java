package org.karthik.studentservice.model;

public class Department {

    private int id;
    private String deptName;

    public Department(int id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public String getDeptName() {
        return deptName;
    }
}
