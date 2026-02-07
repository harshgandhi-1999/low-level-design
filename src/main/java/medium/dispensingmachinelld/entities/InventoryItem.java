package medium.dispensingmachinelld.entities;

public class InventoryItem {
    private final String id;
    private ItemType itemType;
    private int quantity;

    public InventoryItem(String id, ItemType itemType, int quantity) {
        this.id = id;
        this.itemType = itemType;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
