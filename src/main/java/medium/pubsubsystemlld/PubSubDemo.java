package medium.pubsubsystemlld;

import medium.pubsubsystemlld.entities.Message;
import medium.pubsubsystemlld.publishers.Publisher;
import medium.pubsubsystemlld.publishers.SimplePublisher;
import medium.pubsubsystemlld.service.PubSubService;
import medium.pubsubsystemlld.subscribers.AlertSubscriber;
import medium.pubsubsystemlld.subscribers.EmailSubscriber;
import medium.pubsubsystemlld.subscribers.Subscriber;

import java.util.UUID;

public class PubSubDemo {

    public static void main(String[] args) {
//        1. The Pub-Sub system should allow publishers to publish messages to specific topics.
//        2. Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
//        3. The system should support multiple publishers and subscribers.
//        4. Messages should be delivered to all subscribers of a topic in real-time.
//        5. The system should handle concurrent access and ensure thread safety.
//        6. The Pub-Sub system should be scalable and efficient in terms of message delivery.

        PubSubService pubSubService = PubSubService.getInstance();

        pubSubService.createPushTopic("topic1");

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


        Publisher pubSubTopicPublisher = new SimplePublisher("Publisher1", pubSubService);
        pubSubTopicPublisher.publish("topic1", new Message("1", "abcd"));

        pubSubService.shutdown();


        // New requirements:
        // 1. Message ordering inside topic.
        // 2. kafka like parition from which multiple consumers consume same messages.

        Publisher kafkaPartitionPublisher = new SimplePublisher("Publisher2", pubSubService);
        Subscriber consumer1 = new EmailSubscriber("11");
        Subscriber consumer2 = new EmailSubscriber("12");

        pubSubService.createStoredTopic("topic2");
        pubSubService.addSubscriber("topic2", consumer1);
        pubSubService.addSubscriber("topic2", consumer2);


        Thread publisherThread = new Thread(() -> {
            int counter = 1;
            while (counter<20) {
                Message message = new Message(
                        UUID.randomUUID().toString(),
                        "Order-" + counter++
                );

                kafkaPartitionPublisher.publish("topic2", message);

                try {
                    Thread.sleep(500); // 1 second interval
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });


        Thread consumerThread1 = new Thread(() -> {
            while (true) {
                Message message = pubSubService.poll("topic2", "11");

                if (message != null) {
                    consumer1.onMessage(message);
                }

                try {
                    Thread.sleep(1000); // polling interval
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread consumerThread2 = new Thread(() -> {
            while (true) {
                Message message = pubSubService.poll("topic2", "12");

                if (message != null) {
                    consumer2.onMessage(message);
                }

                try {
                    Thread.sleep(1500); // polling interval
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        publisherThread.start();
        consumerThread1.start();
        consumerThread2.start();

    }
}
