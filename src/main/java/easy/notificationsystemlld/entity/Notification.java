package easy.notificationsystemlld.entity;

import static easy.notificationsystemlld.Utils.generateUniqueId;

public class Notification {

    private final String id;
    private final NotificationType notificationType;
    private NotificationPriority notificationPriority;
    private final User user;
    private final Message message;

    public Notification(NotificationType notificationType, User user, Message message) {
        this.id = generateUniqueId();
        this.notificationType = notificationType;
        this.user = user;
        this.notificationPriority = NotificationPriority.LOW;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public User getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }

    public NotificationPriority getNotificationPriority() {
        return notificationPriority;
    }

    public void setNotificationPriority(NotificationPriority notificationPriority) {
        this.notificationPriority = notificationPriority;
    }
}
