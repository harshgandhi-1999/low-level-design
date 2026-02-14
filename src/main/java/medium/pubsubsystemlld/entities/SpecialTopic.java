package medium.pubsubsystemlld.entities;

import medium.pubsubsystemlld.subscribers.Subscriber;

import java.util.*;

public class SpecialTopic implements Topic, PullableTopic{

    // this topic works similar to kafka topic.
    // we have 1 partition which will contain the message
    private final String id;
    private final String name;
    private final Set<Subscriber> subscribers;
    private final List<Message> messages;
    private final Map<String, Integer> subscriberOffsets;

    public SpecialTopic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.subscribers = new HashSet<>();
        this.messages = new ArrayList<>();
        this.subscriberOffsets = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized void publish(Message message) {
        messages.add(message);
    }

    @Override
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
        subscriberOffsets.put(subscriber.getId(), 0);
    }

    @Override
    public boolean removeSubscriber(Subscriber subscriber){
        return subscribers.remove(subscriber);
//        subscriberOffsets.remove(subscriber.getId());
    }


    @Override
    public synchronized Message poll(String subscriberId) {
        Integer offset = subscriberOffsets.get(subscriberId);
        if (offset == null) {
            throw new IllegalArgumentException("Subscriber not registered");
        }

        if (offset >= messages.size()) {
            return null; // no new messages
        }

        Message message = messages.get(offset);
        subscriberOffsets.put(subscriberId, offset + 1);

        System.out.println(
                "[POLL] [Subscriber: " + subscriberId +
                        "] [CurrentOffset: " + offset +
                        "] [MessageId: " + message.getId() +
                        "] [Content: " + message.getContent() +
                        "] [NextOffset: " + (offset + 1) + "]"
        );

        return message;
    }


    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Map<String, Integer> getSubscriberOffsets() {
        return subscriberOffsets;
    }
}
