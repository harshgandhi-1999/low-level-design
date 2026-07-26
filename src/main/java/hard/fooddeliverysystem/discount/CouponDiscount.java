package hard.fooddeliverysystem.discount;

import hard.fooddeliverysystem.models.cart.Cart;

public class CouponDiscount implements Discount {

    private String couponCode;
    private int minCartValue;
    private int flatDiscount;

    public CouponDiscount(String couponCode, int minCartValue, int flatDiscount) {
        this.couponCode = couponCode;
        this.minCartValue = minCartValue;
        this.flatDiscount = flatDiscount;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.calculateBaseTotal() >= minCartValue;
    }

    @Override
    public DiscountResult apply(Cart cart) {
        return new DiscountResult(flatDiscount, "Coupon: " + couponCode);
    }
}