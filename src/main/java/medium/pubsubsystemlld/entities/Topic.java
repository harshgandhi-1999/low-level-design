package medium.pubsubsystemlld.entities;

import medium.pubsubsystemlld.subscribers.Subscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Topic implements TopicObservable {

    private final String id;
    private final String name;
    private final Set<Subscriber> subscribers;

    public Topic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        subscribers = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public boolean removeSubscriber(Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    @Override
    public void broadcast(Message message) {
        for(Subscriber subscriber : subscribers){
            subscriber.onMessage(message);
        }
    }
}
