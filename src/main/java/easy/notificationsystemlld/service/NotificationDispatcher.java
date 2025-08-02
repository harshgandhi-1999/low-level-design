package easy.notificationsystemlld.service;

import easy.notificationsystemlld.entity.Notification;
import easy.notificationsystemlld.channels.ChannelType;
import easy.notificationsystemlld.channels.NotificationChannel;
import easy.notificationsystemlld.factory.ChannelFactory;

import java.util.List;

public class NotificationDispatcher {

    private final ChannelPreferenceService channelPreferenceService;

    public NotificationDispatcher(ChannelPreferenceService preferenceService) {
        this.channelPreferenceService = preferenceService;
    }

    public void dispatch(Notification notification) {
        List<ChannelType> channels = channelPreferenceService.getPreferredChannels(notification.getUser().getId());

        for (ChannelType channelType : channels) {
            NotificationChannel channel = ChannelFactory.getChannel(channelType);

            channel.send(notification);
        }
    }
}
