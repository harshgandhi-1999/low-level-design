package medium.pubsubsystemlld.entities;

import medium.pubsubsystemlld.subscribers.Subscriber;

public interface Topic {
    String getId();
    String getName();
    void addSubscriber(Subscriber subscriber);
    boolean removeSubscriber(Subscriber subscriber);
    void publish(Message message);

    default void shutdown() {
        // default no-op
    }
}
