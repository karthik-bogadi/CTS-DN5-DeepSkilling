package org.karthik.junit_implementation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;
import org.karthik.junit_implementation.service.TestingApp;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class TestingAppTest {

    TestingApp testingApp;

    @BeforeAll
    static void init(){
        System.out.println("generally this method is used for database connection so that every time we don't need to connect to DB");
    }

    @BeforeEach
    void setUp(){
        testingApp=new TestingApp();
        System.out.println("Executed before each Testcase and object created");
    }
    @Test
    void testWithEquals(){

        assertEquals(30,testingApp.add());
    }

    @ParameterizedTest
    @ValueSource(ints={21,45,89})
    void testWithTrue(int age){

        assertTrue(testingApp.checkEligibility(age));
    }

    @Test
    @Disabled("This Test is Temporarily stopped")
    void testWithFalse(){

        assertFalse(testingApp.checkEligibility(18));
    }

    @Test
    void testWithNotNull(){

        assertNotNull(testingApp.getStudentByID(20));
    }

    @Test
    @Disabled
    void testWithNull(){

        assertNull(testingApp.getStudentByID(30));
    }

    @Test
    void testWithThrows(){
        assertThrows(RuntimeException.class,
                ()->testingApp.divide(10,0));
    }

    @Test
    void testWithThrows2(){
        RuntimeException exception=assertThrows(RuntimeException.class,
                ()->testingApp.getStudentByID(29));
        assertEquals("Student Not Found",exception.getMessage());
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Executed after each testcase");
    }

    @AfterAll
    static void destroy(){
        System.out.println("Cleanup all the resources");
    }
}
