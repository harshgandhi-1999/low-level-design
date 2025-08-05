package medium.notificationsystemlld.service;

import medium.notificationsystemlld.channels.ChannelType;
import medium.notificationsystemlld.channels.NotificationChannel;
import medium.notificationsystemlld.entity.Notification;
import medium.notificationsystemlld.factory.ChannelFactory;

import java.util.List;

public class NotificationDispatcher {

    private final ChannelPreferenceService channelPreferenceService;
    private final RetryHandler retryHandler;

    public NotificationDispatcher(ChannelPreferenceService preferenceService, RetryHandler retryHandler) {
        this.channelPreferenceService = preferenceService;
        this.retryHandler = retryHandler;
    }

    public void dispatch(Notification notification) {
        List<ChannelType> channels = channelPreferenceService.getPreferredChannels(notification.getUser().getId());

        boolean success = false;

        for (ChannelType channelType : channels) {
            NotificationChannel channel = ChannelFactory.getChannel(channelType);
            try {
                // we can also use completable future for handling async api call result
                channel.send(notification);
                success = true;
                break;
            } catch (Exception e) {
                System.err.println("Failed to send via " + channelType + ": " + e.getMessage());
            }
        }

        // if the request has failed then it will retry
        if (!success) {
            retryHandler.handleRetry(notification);
        }
    }
}
