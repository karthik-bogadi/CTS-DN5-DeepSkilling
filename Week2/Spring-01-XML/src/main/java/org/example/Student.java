package org.example;

public class Student {
    private final Laptop laptop;

    public Student(Laptop laptop) {

        this.laptop = laptop;
    }

    public void study(){
        System.out.println("Student is studying");
        laptop.currentWork();
    }
}
