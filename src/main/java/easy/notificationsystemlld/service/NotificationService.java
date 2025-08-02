package easy.notificationsystemlld.service;

import easy.notificationsystemlld.entity.Notification;
import easy.notificationsystemlld.channels.ChannelType;
import easy.notificationsystemlld.channels.NotificationChannel;
import easy.notificationsystemlld.factory.ChannelFactory;

import java.util.List;

public class NotificationService {

    private final ChannelPreferenceService channelPreferenceService;

    public NotificationService(ChannelPreferenceService preferenceService) {
        this.channelPreferenceService = preferenceService;
    }

    public void sendNotification(String userId, Notification notification) {
        List<ChannelType> channels = channelPreferenceService.getPreferredChannels(userId);

        for(ChannelType channelType : channels){
            NotificationChannel channel = ChannelFactory.getChannel(channelType);

            channel.send(notification);
        }
    }


}
