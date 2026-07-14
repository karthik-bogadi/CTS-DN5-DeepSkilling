package org.karthik.mockitoexercises.service;

import org.karthik.mockitoexercises.model.student;
import org.karthik.mockitoexercises.repository.StudentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {

    static final Logger logger= (Logger) LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentRepository studentRepository;
    public Optional<student> getStudent(int id){
        logger.info("Repositor calls");
        return studentRepository.findById(id);
    }
}
