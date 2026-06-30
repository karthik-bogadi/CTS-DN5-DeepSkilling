package org.karthik.springdatajpa.repository;

import org.karthik.springdatajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    
}
