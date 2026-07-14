package org.karthik.mockitoexercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.karthik.mockitoexercises.model.student;
import org.karthik.mockitoexercises.repository.StudentRepository;
import org.karthik.mockitoexercises.service.StudentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class testWithMockito {

    @Mock
    StudentRepository studentRepository;


    @InjectMocks
    StudentService studentService;

    @Test
    void shouldReturnStudentIfExists(){

        student stu=new student(101,"karthik");

        when(studentRepository.findById(101)).thenReturn(Optional.of(stu));
        Optional<student> result=studentService.getStudent(101);
        assertEquals("karthik",result.get().getName());
        verify(studentRepository).findById(101);
    }

}
