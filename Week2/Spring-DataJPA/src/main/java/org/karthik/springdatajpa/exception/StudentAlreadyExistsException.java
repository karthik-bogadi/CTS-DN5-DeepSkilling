package org.karthik.springdatajpa.exception;

public class StudentAlreadyExistsException extends RuntimeException{

    public StudentAlreadyExistsException(String message){
        super(message);
    }
}
