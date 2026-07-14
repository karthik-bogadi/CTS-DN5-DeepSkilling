package org.karthik.mockitoexercises.repository;

import org.karthik.mockitoexercises.model.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<student,Integer> {
}
