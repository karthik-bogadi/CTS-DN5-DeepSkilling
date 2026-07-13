package org.karthik.junit_implementation.service;

import org.karthik.junit_implementation.model.Student;

public class TestingApp {
    int a=10;
    int b=20;
    public int add(){
        return a+b;
    }

    public boolean checkEligibility(int age){

        return age>18;
    }

    public Student getStudentByID(int id){
        if(id==20){
            return new Student(20,"karthik");
        }
        throw new RuntimeException("Student Not Found");
    }

     public int divide(int a, int b){
        return a/b;
    }
}
