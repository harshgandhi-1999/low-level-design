package hard.fooddeliverysystem.discount;

import hard.fooddeliverysystem.models.cart.Cart;

public class FoodItemDiscount implements Discount {

    private String foodItemId;
    private int percentage;

    public FoodItemDiscount(String foodItemId, int percentage) {
        this.foodItemId = foodItemId;
        this.percentage = percentage;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.containsFoodItem(foodItemId);
    }

    @Override
    public DiscountResult apply(Cart cart) {
        int discount = cart.calculateFoodDiscount(foodItemId, percentage);
        return new DiscountResult(discount, "Food item discount");
    }
}