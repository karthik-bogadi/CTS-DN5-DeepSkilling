package org.karthik.springrestusingspringboot3.repository;

import org.karthik.springrestusingspringboot3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByName(String name);
}
