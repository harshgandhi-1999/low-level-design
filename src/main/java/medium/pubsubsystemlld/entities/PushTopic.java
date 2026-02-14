package medium.pubsubsystemlld.entities;

import medium.pubsubsystemlld.subscribers.Subscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PushTopic implements Topic {

    private final String id;
    private final String name;
    private final Set<Subscriber> subscribers;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public PushTopic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        subscribers = new HashSet<>();
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
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public boolean removeSubscriber(Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    @Override
    public synchronized void publish(Message message) {
        //        for(Subscriber subscriber : subscribers){
//            subscriber.onMessage(message);
//        }
        // Using executor service to publish messages asynchronously
        // This ensures a slow or failing subscriber does not block publishing or other subscribers
        for(Subscriber subscriber : subscribers){
            executorService.submit(() -> {
                try {
                    subscriber.onMessage(message);
                } catch (Exception e) {
                    System.out.println("Failed to deliver message to subscriber: "
                            + subscriber.getId());
                }
            });
        }
    }

    public void shutdown() {
//        executorService should be shut down during application shutdown to avoid thread leaks.
//        I’d expose a shutdown hook from the service layer to clean up topic executors
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

//        Q: Will shutdownNow immediately kill threads?
//        “No, it sends an interrupt signal.
//        Threads must cooperate by handling interruption.”
    }

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }
}
