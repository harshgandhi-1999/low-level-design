package easy.notificationsystemlld.channels;


import easy.notificationsystemlld.entity.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}
