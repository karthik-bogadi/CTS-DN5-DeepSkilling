public class Calculator {
    int a;
    int b;
    private static Calculator obj;

    private Calculator(){
        
    }
    public int sum(){
        return a+b;
    }

    public static Calculator getInstance(){
        if(obj==null){
            obj=new Calculator();
        }
        return obj;
    }
}
