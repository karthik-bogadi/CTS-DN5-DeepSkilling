package org.karthik.sonar_integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sonar {
    int number;
    @GetMapping("/sonar")
    public String sonarIntegration(){
        return "First Project With SonarQube";
    }
    @GetMapping("/test")
    public String sum(){
        String name = null;

        System.out.println(name.length());
        String password = "admin123";
        int marks = 90;
        int a=10;
        int b=20;
        int c=a+b;
        if(true){
            return "Sum is"+c;
        }
        return "sum not calculated";
    }
}
