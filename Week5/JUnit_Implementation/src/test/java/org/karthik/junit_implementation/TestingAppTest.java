package org.karthik.junit_implementation;

import org.junit.jupiter.api.Test;
import org.karthik.junit_implementation.service.TestingApp;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class TestingAppTest {

    TestingApp testingApp=new TestingApp();
    @Test
    void testWithEquals(){
        assertEquals(30,testingApp.add());
    }

    @Test
    void testWithTrue(){
        assertTrue(testingApp.checkEligibility(20));
    }

    @Test
    void testWithFalse(){
        assertFalse(testingApp.checkEligibility(18));
    }

    @Test
    void testWithNotNull(){
        assertNotNull(testingApp.getStudentByID(20));
    }

    @Test
    void testWithNull(){
        assertNull(testingApp.getStudentByID(30));
    }

    @Test
    void testWithThrows(){
        assertThrows(RuntimeException.class,
                ()->testingApp.divide(10,0));
    }


}
