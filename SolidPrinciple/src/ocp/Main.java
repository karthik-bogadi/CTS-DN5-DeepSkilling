package ocp;

public class Main {
    public static void main(String[] args){
        NotificationService obj=new MobileNotification();
        obj.sendOtp();
        obj.sendTransactionDetails();

        NotificationService obj2=new EmailNotification();
        obj2.sendOtp();
        obj2.sendTransactionDetails();
    }
}
