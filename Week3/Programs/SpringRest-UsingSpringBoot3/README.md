# Student Management REST API

A simple Student Management REST API built using Spring Boot.  
This project was developed to practice Spring Boot, REST APIs, Spring Data JPA, validation, exception handling, and MySQL integration.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation
- Spring Boot DevTools

## Project Structure

```text
src/main/java/org/karthik/springrestusingspringboot3

├── controller
│   └── StudentController.java
│
├── service
│   └── StudentService.java
│
├── repository
│   └── StudentRepository.java
│
├── model
│   └── Student.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── StudentNotFoundException.java
│   └── StudentAlreadyExistsException.java
│
└── SpringRestUsingSpringBoot3Application.java