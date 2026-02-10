package medium.dispensingmachinelld.entities;

public class AddOn {
    private final String id;
    private String name;
    private String description;
    private AddOnType addOnType;
    private double basePrice;


    public AddOn(String id, String name, String description, AddOnType addOnType, double basePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.addOnType = addOnType;
        this.basePrice = basePrice;
    }

    public String getId() {
        return id;
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

    public AddOnType getAddOnType() {
        return addOnType;
    }

    public void setAddOnType(AddOnType addOnType) {
        this.addOnType = addOnType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
