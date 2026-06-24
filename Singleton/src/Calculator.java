public class Calculator {

    int a;
    int b;

    private static Calculator obj;

    private Calculator() {
    }

    public static Calculator getInstance() {
        if(obj == null) {
            obj = new Calculator();
        }
        return obj;
    }

    public int sum() {
        return a + b;
    }
}