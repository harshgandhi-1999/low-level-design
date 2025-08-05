package medium.notificationsystemlld.channels;


import medium.notificationsystemlld.entity.Notification;

public class EmailChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {
        // use email client to send this notification
        System.out.println("Email sent to: " + notification.getUser().getEmail() + ", message: " + notification.getMessage().getContent());
    }
}
