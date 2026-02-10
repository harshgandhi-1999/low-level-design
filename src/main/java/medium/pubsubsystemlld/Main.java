package medium.pubsubsystemlld;

import medium.pubsubsystemlld.entities.Message;
import medium.pubsubsystemlld.service.PubSubService;
import medium.pubsubsystemlld.subscribers.AlertSubscriber;
import medium.pubsubsystemlld.subscribers.EmailSubscriber;

public class Main {

    public static void main(String[] args) {
//        1. The Pub-Sub system should allow publishers to publish messages to specific topics.
//        2. Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
//        3. The system should support multiple publishers and subscribers.
//        4. Messages should be delivered to all subscribers of a topic in real-time.
//        5. The system should handle concurrent access and ensure thread safety.
//        6. The Pub-Sub system should be scalable and efficient in terms of message delivery.

        PubSubService pubSubService = PubSubService.getInstance();

        pubSubService.createTopic("topic1");

        pubSubService.addSubscriber("topic1", new AlertSubscriber("1"));
        pubSubService.addSubscriber("topic1", new EmailSubscriber("2"));
        pubSubService.addSubscriber("topic1", new AlertSubscriber("3"));
        pubSubService.addSubscriber("topic1", new EmailSubscriber("4"));
        pubSubService.addSubscriber("topic1", new AlertSubscriber("5"));
        pubSubService.addSubscriber("topic1", new EmailSubscriber("6"));
        pubSubService.addSubscriber("topic1", new AlertSubscriber("7"));
        pubSubService.addSubscriber("topic1", new EmailSubscriber("8"));
        pubSubService.addSubscriber("topic1", new AlertSubscriber("9"));
        pubSubService.addSubscriber("topic1", new EmailSubscriber("10"));

        System.out.println("----------------------------------------------------------");

        pubSubService.publish("topic1", new Message("1", "abcd"));

        pubSubService.shutdown();

    }
}
