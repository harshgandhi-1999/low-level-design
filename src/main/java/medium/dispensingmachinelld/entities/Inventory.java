package medium.dispensingmachinelld.entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, InventoryItem> items;

    public Inventory() {
        items = new HashMap<>();
    }

    public void addItem(String key, InventoryItem item){
        items.put(key, item);
    }

    public InventoryItem getItem(String key){
        return items.get(key);
    }

    public Map<String, InventoryItem> getItems() {
        return items;
    }

    public void setItems(Map<String, InventoryItem> items) {
        this.items = items;
    }
}
