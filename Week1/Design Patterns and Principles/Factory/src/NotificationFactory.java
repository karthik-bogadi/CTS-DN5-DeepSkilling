public class NotificationFactory {

    public static Notification getNotification(String type) {

        if(type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        }

        if(type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        }

        return null;
    }
}