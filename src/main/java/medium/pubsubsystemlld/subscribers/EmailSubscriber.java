package medium.pubsubsystemlld.subscribers;

import medium.pubsubsystemlld.entities.Message;

public class EmailSubscriber implements Subscriber{

    private final String id;

    public EmailSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("[Email][ID]: " + id +  " Message: " + message.toString());
    }
}
