package ocp;

public class MobileNotification implements NotificationService{
    @Override
    public void sendOtp() {
        System.out.println("OTP through Mobile");
    }

    @Override
    public void sendTransactionDetails() {
        System.out.println("Transaction receipt will be sent");
    }
}
