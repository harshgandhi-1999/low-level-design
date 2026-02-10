package medium.pubsubsystemlld.subscribers;

import medium.pubsubsystemlld.entities.Message;

public interface Subscriber {
    String getId();
    void onMessage(Message message);
}
