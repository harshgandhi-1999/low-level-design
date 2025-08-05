package medium.notificationsystemlld.worker;

import medium.notificationsystemlld.entity.Notification;
import medium.notificationsystemlld.queue.NotificationQueue;
import medium.notificationsystemlld.service.NotificationDispatcher;

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
                if(Thread.interrupted()){
                    break;
                }
            }
        }
    }
}
