package medium.pubsubsystemlld.entities;

import medium.pubsubsystemlld.subscribers.Subscriber;

public interface TopicObservable {
    void addSubscriber(Subscriber subscriber);
    boolean removeSubscriber(Subscriber subscriber);
    void broadcast(Message message);
}
