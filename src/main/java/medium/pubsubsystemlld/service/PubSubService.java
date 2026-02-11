package medium.pubsubsystemlld.service;

import medium.pubsubsystemlld.entities.Message;
import medium.pubsubsystemlld.entities.Topic;
import medium.pubsubsystemlld.subscribers.Subscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Broker
public class PubSubService {

    private static final PubSubService INSTANCE = new PubSubService();
    private final Map<String, Topic> topicRegistry;  // Assuming topic name are uniques

    private PubSubService() {
        topicRegistry = new HashMap<>();
    }

    public static PubSubService getInstance() {
        return INSTANCE;
    }

    public void createTopic(String topicName){
        topicRegistry.putIfAbsent(topicName, new Topic(topicName));
        System.out.println("Topic " + topicName + " created");
    }

    public Set<String> listTopics() {
        return topicRegistry.keySet();
    }

    public Topic getTopic(String topicName){
        Topic topic = topicRegistry.get(topicName);
        if(topicRegistry.get(topicName)==null){
            throw new IllegalArgumentException("Topic not found: " + topicName);
        }

        return topic;
    }

    public void addSubscriber(String topicName, Subscriber subscriber){
        Topic topic = topicRegistry.get(topicName);
        if(topic == null){
            throw new IllegalArgumentException("Topic not found: " + topicName);
        }
        topic.addSubscriber(subscriber);
        System.out.println("Subscriber '" + subscriber.getId() + "' subscribed to topic: " + topicName);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if (topic != null)
            topic.removeSubscriber(subscriber);
        System.out.println("Subscriber '" + subscriber.getId() + "' unsubscribed from topic: " + topicName);
    }

    public void publish(String topicName, Message message) {
        System.out.println("Publishing message to topic: " + topicName);
        Topic topic = topicRegistry.get(topicName);
        if (topic == null) throw new IllegalArgumentException("Topic not found: " + topicName);
        topic.broadcast(message);
    }

    public void shutdown() {
        for (Topic topic : topicRegistry.values()) {
            topic.shutdown();
        }
    }
}
