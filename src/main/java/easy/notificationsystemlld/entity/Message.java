package easy.notificationsystemlld.entity;

import static easy.notificationsystemlld.Utils.generateUniqueId;

public class Message {
    private final String id;
    private final String content;

    public Message(String content) {
        this.id = generateUniqueId();
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
