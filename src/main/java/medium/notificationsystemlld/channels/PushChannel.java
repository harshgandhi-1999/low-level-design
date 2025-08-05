package medium.notificationsystemlld.channels;

import medium.notificationsystemlld.entity.Notification;

public class PushChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {
        // Use Push channel to send notification
        System.out.println("Push notification sent to user: " + notification.getUser().getUsername());
    }
}
