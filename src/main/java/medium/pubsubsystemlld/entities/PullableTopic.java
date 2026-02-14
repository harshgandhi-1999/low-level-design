package medium.pubsubsystemlld.entities;

public interface PullableTopic {
    Message poll(String subscriberId);
}
