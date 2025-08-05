package medium.notificationsystemlld.scheduler;

import medium.notificationsystemlld.entity.Notification;
import medium.notificationsystemlld.queue.NotificationQueue;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final NotificationQueue queue;

    public NotificationScheduler(NotificationQueue queue) {
        this.queue = queue;
    }

    public void schedule(Notification notification, Instant triggerAt) {
        long delay = Duration.between(Instant.now(), triggerAt).toMillis();
        scheduler.schedule(() -> {
            System.out.println("Scheduled retry at midnight: " + notification);
            notification.setRetryCount(0); // reset retry count
            queue.enqueue(notification);
        }, delay, TimeUnit.MILLISECONDS);
    }
}
