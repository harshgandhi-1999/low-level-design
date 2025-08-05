package medium.notificationsystemlld.channels;


import medium.notificationsystemlld.entity.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}
