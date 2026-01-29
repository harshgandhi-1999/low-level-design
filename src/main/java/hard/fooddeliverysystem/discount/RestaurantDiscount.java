package hard.fooddeliverysystem.discount;

import hard.fooddeliverysystem.models.cart.Cart;

public class RestaurantDiscount implements Discount {

    private String restaurantId;
    private int percentage;


    public RestaurantDiscount(String restaurantId, int percentage) {
        this.restaurantId = restaurantId;
        this.percentage = percentage;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.containsRestaurant(restaurantId);
    }

    @Override
    public DiscountResult apply(Cart cart) {
        int discount = cart.calculateRestaurantDiscount(restaurantId, percentage);
        return new DiscountResult(discount, "Restaurant discount");
    }
}