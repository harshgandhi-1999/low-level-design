package hard.fooddeliverysystem.models.restaurant;

import hard.fooddeliverysystem.models.cart.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<FoodItem> foodItems;

    public Menu() {
        this.foodItems = new ArrayList<>();
    }

    public Menu(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "foodItems=" + foodItems +
                '}';
    }
}
