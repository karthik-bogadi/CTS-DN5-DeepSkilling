//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Calculator ob1=Calculator.getInstance();
        Calculator ob2=Calculator.getInstance();

        ob1.a=10;
        ob1.b=5;

        ob2.a=7;
        ob2.b=3;
        System.out.println(ob1.sum());
        System.out.println(ob2.sum());

    }
}