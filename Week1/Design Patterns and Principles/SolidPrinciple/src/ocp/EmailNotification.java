package ocp;

public class EmailNotification implements NotificationService{

    @Override
    public void sendOtp() {
        System.out.println("OTP through Email");
    }

    @Override
    public void sendTransactionDetails() {
        //Email related logic
    }
}
