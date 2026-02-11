package medium.pubsubsystemlld.publishers;

import medium.pubsubsystemlld.entities.Message;
import medium.pubsubsystemlld.service.PubSubService;

public class SimplePublisher implements Publisher{

    private final String id;
    private final PubSubService pubSubService;

    public SimplePublisher(String id, PubSubService pubSubService) {
        this.id = id;
        this.pubSubService = pubSubService;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void publish(String topicName, Message message) {
        System.out.println("[Publisher][ID]: " + id + " [TOPIC]: " + topicName + " [MESSAGE]: " + message.getContent());
        pubSubService.publish(topicName, message);
        // DONT DO direct PubSubService.getInstance().publish(). this will be tightly coupled
    }
}
