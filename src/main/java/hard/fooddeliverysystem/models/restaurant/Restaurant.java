package hard.fooddeliverysystem.models.restaurant;

import hard.fooddeliverysystem.models.Address;

public class Restaurant {

    private String id;
    private String name;
    private String description;
    private Address address;
    private Menu menu;

    public Restaurant(String id, String name, String description, Address address, Menu menu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.menu = menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", menu=" + menu +
                '}';
    }
}
