package medium.notificationsystemlld;

import medium.notificationsystemlld.channels.ChannelType;
import medium.notificationsystemlld.entity.*;
import medium.notificationsystemlld.queue.NotificationQueue;
import medium.notificationsystemlld.scheduler.NotificationScheduler;
import medium.notificationsystemlld.service.ChannelPreferenceService;
import medium.notificationsystemlld.service.NotificationDispatcher;
import medium.notificationsystemlld.service.RetryHandler;
import medium.notificationsystemlld.worker.NotificationWorker;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Requirements:
        // 1. any service can send different type of notification events
        // 2. There can be different priority for notification
        // 3. It should support different notification channels like email, sms, whatsapp.
        // 4. There should be channel preferences for each user. Ex: user want only email and sms for notifications.
        // 5. If the notification is unable to send then it should do retries.
        // 6. Schedule all the failed (after doing certain amount of retried) notifications for midnight.

        // create users first
        User user1 = new User("harsh", "abc@gmail.com", "123");
        User user2 = new User("rahul", "def@gmail.com", "456");
        User user3 = new User("ajay", "ghi@gmail.com", "789");
        User user4 = new User("ankit", "jkl@gmail.com", "135");

        ChannelPreferenceService channelPreferenceService = new ChannelPreferenceService();
        channelPreferenceService.setUserPreferences(user1.getId(), List.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.PUSH_NOTIFICATION));
        channelPreferenceService.setUserPreferences(user2.getId(), List.of(ChannelType.EMAIL));
        channelPreferenceService.setUserPreferences(user3.getId(), List.of(ChannelType.SMS));
        channelPreferenceService.setUserPreferences(user4.getId(), List.of(ChannelType.PUSH_NOTIFICATION));


        // create notifications
        Notification notification1 = new Notification(NotificationType.ORDER_PLACED, user1, new Message("Your order has been placed"));
        Notification notification2 = new Notification(NotificationType.DAILY_DIGEST, user2, new Message("Study this tech blog"));

        notification1.setNotificationPriority(NotificationPriority.HIGH);


        // now setting up retry handler, scheduler, notification queue, notification worker and notification dispatcher

        NotificationQueue queue = new NotificationQueue();
        NotificationScheduler scheduler = new NotificationScheduler(queue);
        RetryHandler retryHandler = new RetryHandler(queue, scheduler);
        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(channelPreferenceService, retryHandler);

        // send the notification to event queue
        queue.enqueue(notification1);
        queue.enqueue(notification2);

        NotificationWorker notificationWorker = new NotificationWorker(queue, notificationDispatcher);

        // Notification Worker: now consumer will consume the notifications from event queue which will be internally sent to channel for sending
        // high priority notification will be pulled first
        new Thread(notificationWorker).start();
    }
}
