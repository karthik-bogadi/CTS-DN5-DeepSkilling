public class CalculatorTest {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        int result = c.add(10, 20);

        if (result == 30) {
            System.out.println("Test Passed");
            System.out.println("Result = " + result);
        } else {
            System.out.println("Test Failed");
            System.out.println("Expected = 30, Actual = " + result);
        }
    }
}