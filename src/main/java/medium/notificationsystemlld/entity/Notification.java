package medium.notificationsystemlld.entity;

import java.time.Instant;

import static medium.notificationsystemlld.Utils.generateUniqueId;

public class Notification implements Comparable<Notification>{

    private final String id;
    private final NotificationType notificationType;
    private NotificationPriority notificationPriority;
    private final User user;
    private final Message message;

    private int retryCount = 0;
    private Instant scheduledTime = Instant.now(); // default = immediate

    public Notification(NotificationType notificationType, User user, Message message) {
        this.id = generateUniqueId();
        this.notificationType = notificationType;
        this.user = user;
        this.notificationPriority = NotificationPriority.MEDIUM;
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

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Instant getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Instant scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @Override
    public int compareTo(Notification other) {
        // higher priority first
        return this.notificationPriority.ordinal() - other.notificationPriority.ordinal();
    }
}
