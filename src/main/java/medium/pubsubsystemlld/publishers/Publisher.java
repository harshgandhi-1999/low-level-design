package medium.pubsubsystemlld.publishers;

import medium.pubsubsystemlld.entities.Message;

public interface Publisher {

    String getId();
    void publish(String topicName, Message message);
}
