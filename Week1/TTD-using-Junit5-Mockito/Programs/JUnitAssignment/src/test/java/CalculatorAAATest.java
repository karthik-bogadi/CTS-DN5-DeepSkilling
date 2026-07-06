

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorAAATest {

    Calculator calculator;

    @Before
    public void setUp() {

        System.out.println("Setup");

        calculator = new Calculator();

    }

    @After
    public void tearDown() {

        System.out.println("Teardown");

    }

    @Test
    public void testAddition() {

        // Arrange
        int a = 10;
        int b = 20;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(30, result);

    }

}