package easy.notificationsystemlld;

import easy.notificationsystemlld.channels.ChannelType;
import easy.notificationsystemlld.entity.Message;
import easy.notificationsystemlld.entity.Notification;
import easy.notificationsystemlld.entity.NotificationType;
import easy.notificationsystemlld.entity.User;
import easy.notificationsystemlld.queue.NotificationQueue;
import easy.notificationsystemlld.service.ChannelPreferenceService;
import easy.notificationsystemlld.service.NotificationDispatcher;
import easy.notificationsystemlld.worker.NotificationWorker;

import java.util.List;

public class Main {

    public static void main(String[] args) {

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


        Notification notification1 = new Notification(NotificationType.ORDER_PLACED, user1, new Message("Your order has been placed"));
        Notification notification2 = new Notification(NotificationType.DAILY_DIGEST, user2, new Message("Study this tech blog"));

        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(channelPreferenceService);

        NotificationQueue queue = new NotificationQueue();
        NotificationWorker notificationWorker = new NotificationWorker(queue, notificationDispatcher);

        queue.enqueue(notification1);
        queue.enqueue(notification2);

        new Thread(notificationWorker).start();
    }
}
