package medium.notificationsystemlld.service;

import medium.notificationsystemlld.entity.Notification;
import medium.notificationsystemlld.queue.NotificationQueue;
import medium.notificationsystemlld.scheduler.NotificationScheduler;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RetryHandler {
    private static final int MAX_RETRIES = 3;
    private final NotificationQueue notificationQueue;
    private final NotificationScheduler scheduler;

    public RetryHandler(NotificationQueue notificationQueue, NotificationScheduler scheduler) {
        this.notificationQueue = notificationQueue;
        this.scheduler = scheduler;
    }

    public void handleRetry(Notification notification) {
        int retries = notification.getRetryCount();
        if (retries < MAX_RETRIES) {
            notification.setRetryCount(retries + 1);
            System.out.println("Retrying (" + notification.getRetryCount() + "): " + notification);
            notificationQueue.enqueue(notification); // retry immediately
        } else {
            // Schedule for midnight
            Instant midnight = getNextMidnight();
            notification.setScheduledTime(midnight);
            System.out.println("Max retries exceeded. Scheduled for midnight: " + notification);
            scheduler.schedule(notification, midnight);
        }
    }

    private Instant getNextMidnight() {
        ZonedDateTime midnight = ZonedDateTime.now(ZoneId.systemDefault())
                .plusDays(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        return midnight.toInstant();
    }
}
