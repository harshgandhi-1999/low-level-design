package medium.notificationsystemlld.factory;

import medium.notificationsystemlld.channels.*;

public class ChannelFactory {

    public static NotificationChannel getChannel(ChannelType channelType) {

        return switch (channelType) {
            case EMAIL -> new EmailChannel();
            case SMS -> new SmsChannel();
            case PUSH_NOTIFICATION -> new PushChannel();
            case IN_APP, WHATSAPP -> null;
        };
    }
}
