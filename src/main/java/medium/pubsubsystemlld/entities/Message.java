package medium.pubsubsystemlld.entities;

import java.time.Instant;

public class Message {

    private final String id;
    private String content;
    private Instant timestamp;

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
