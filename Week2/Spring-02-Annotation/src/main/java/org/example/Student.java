package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Autowired
    private Laptop laptop;
    public void study(){
        System.out.println("student is studying");
        laptop.currentTask();
    }

}
