package easy.notificationsystemlld.worker;

import easy.notificationsystemlld.entity.Notification;
import easy.notificationsystemlld.queue.NotificationQueue;
import easy.notificationsystemlld.service.NotificationDispatcher;

public class NotificationWorker implements Runnable {

    private final NotificationQueue notificationQueue;
    private final NotificationDispatcher notificationDispatcher;

    public NotificationWorker(NotificationQueue notificationQueue, NotificationDispatcher notificationDispatcher) {
        this.notificationQueue = notificationQueue;
        this.notificationDispatcher = notificationDispatcher;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Notification notification = notificationQueue.dequeue();
                notificationDispatcher.dispatch(notification);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
