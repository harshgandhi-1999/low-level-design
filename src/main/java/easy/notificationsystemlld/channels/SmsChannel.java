package easy.notificationsystemlld.channels;

import easy.notificationsystemlld.entity.Notification;

public class SmsChannel implements NotificationChannel{

    @Override
    public void send(Notification notification) {
        // Use Sms Client to send this notification
        System.out.println("SMS sent to contact: " + notification.getUser().getContact() + ", message: " + notification.getMessage().getContent());
    }
}
