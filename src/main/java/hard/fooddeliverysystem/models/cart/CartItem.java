package hard.fooddeliverysystem.models.cart;

import hard.fooddeliverysystem.models.restaurant.Restaurant;

public class CartItem {
    private FoodItem foodItem;
    private int quantity;
    private Restaurant restaurant;


    public CartItem(FoodItem foodItem, int quantity, Restaurant restaurant) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.restaurant = restaurant;
    }


    public FoodItem getFood() {
        return foodItem;
    }

    public void setFood(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
