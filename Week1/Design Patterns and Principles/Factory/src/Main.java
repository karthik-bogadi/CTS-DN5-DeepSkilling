public class Main {

    public static void main(String[] args) {

        Notification notification =
                NotificationFactory.getNotification("Sms");

        assert notification != null;
        notification.send();
    }
}