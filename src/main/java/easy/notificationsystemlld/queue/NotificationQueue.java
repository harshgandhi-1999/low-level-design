package easy.notificationsystemlld.queue;

import easy.notificationsystemlld.entity.Notification;

import java.util.concurrent.PriorityBlockingQueue;

public class NotificationQueue {

    // for thread safe we are using this blocking queue - it internally manages locking
    private final PriorityBlockingQueue<Notification> queue = new PriorityBlockingQueue<>();

    public void enqueue(Notification notification) {
        queue.put(notification);
    }

    public Notification dequeue() throws InterruptedException {
        // this take methods will wait till the queue is empty
        return queue.take();
    }
}
