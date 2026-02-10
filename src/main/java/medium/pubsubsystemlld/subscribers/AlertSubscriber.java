package medium.pubsubsystemlld.subscribers;

import medium.pubsubsystemlld.entities.Message;

public class AlertSubscriber implements Subscriber{
    private final String id;

    public AlertSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("[Alert][ID]: " + id + " Message: Inside alert");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[Alert][ID]: " + id + " Message: " + message.toString());
    }
}
