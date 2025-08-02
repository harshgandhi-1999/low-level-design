package easy.notificationsystemlld.entity;

import static easy.notificationsystemlld.Utils.generateUniqueId;

public class User {
    private final String id;
    private final String username;
    private final String email;
    private final String contact;

    public User(String username, String email, String contact) {
        this.id = generateUniqueId();
        this.username = username;
        this.email = email;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}
