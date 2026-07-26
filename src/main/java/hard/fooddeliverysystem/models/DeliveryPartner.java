package hard.fooddeliverysystem.models;

import java.util.UUID;

public class DeliveryPartner {
    private final String id;
    private String name;
    private boolean available;

    public DeliveryPartner(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.available = true;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }
}
