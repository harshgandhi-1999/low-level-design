package hard.fooddeliverysystem.discount;

import hard.fooddeliverysystem.models.cart.Cart;

public interface Discount {
    boolean isApplicable(Cart cart);
    DiscountResult apply(Cart cart);
}
