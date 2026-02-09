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
        System.out.println("[Alert] Message: " + message);
    }
}
