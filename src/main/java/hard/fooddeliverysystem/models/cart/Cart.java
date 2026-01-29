package hard.fooddeliverysystem.models.cart;

import java.util.*;

public class Cart {
    private final String id;
    private final List<CartItem> items;
    private final Set<String> appliedCouponCodes;

    public Cart() {
        this.id = UUID.randomUUID().toString();
        this.items = new ArrayList<>();
        this.appliedCouponCodes = new HashSet<>();
    }

    // ---------- Cart Operations ----------

    public void addItem(CartItem cartItem) {
        Optional<CartItem> existingItem = items.stream()
                .filter(item ->
                        item.getFood().getId().equals(cartItem.getFood().getId()) &&
                                item.getRestaurant().getId().equals(cartItem.getRestaurant().getId())
                )
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
        } else {
            items.add(cartItem);
        }
    }

    public void removeItem(String foodItemId, String restaurantId) {
        items.removeIf(item ->
                item.getFood().getId().equals(foodItemId) &&
                        item.getRestaurant().getId().equals(restaurantId)
        );
    }

    public void clearCart() {
        items.clear();
        appliedCouponCodes.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // ---------- Coupon Handling ----------

    // TODO: need to test this
    public void applyCoupon(String couponCode) {
        appliedCouponCodes.add(couponCode);
    }

    public void removeCoupon(String couponCode) {
        appliedCouponCodes.remove(couponCode);
    }

    public Set<String> getAppliedCouponCodes() {
        return Collections.unmodifiableSet(appliedCouponCodes);
    }

    // ---------- Read Helpers (Used by PricingService) ----------

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean containsFoodItem(String foodItemId) {
        return items.stream()
                .anyMatch(item -> item.getFood().getId().equals(foodItemId));
    }

    public boolean containsRestaurant(String restaurantId) {
        return items.stream()
                .anyMatch(item -> item.getRestaurant().getId().equals(restaurantId));
    }

    // ---------- Base Price Calculation (NO DISCOUNT) ----------

    public int calculateBaseTotal() {
        return items.stream()
                .mapToInt(item ->
                        item.getFood().getPrice() * item.getQuantity()
                )
                .sum();
    }

    public int calculateFoodDiscount(String foodItemId, int percentage) {
        int discount = 0;

        for (CartItem item : items) {
            if (item.getFood().getId().equals(foodItemId)) {
                int itemTotal =
                        item.getFood().getPrice() * item.getQuantity();

                discount += (itemTotal * percentage) / 100;
            }
        }
        return discount;
    }

    public int calculateRestaurantDiscount(String restaurantId, int percentage) {
        int discount = 0;

        // todo: sinve this is a restaurant discount, we cannot add for every item.
        // also restaurant should not be inside cart item, it should be inside cart
        for (CartItem item : items) {
            if (item.getRestaurant().getId().equals(restaurantId)) {
                int itemTotal =
                        item.getFood().getPrice() * item.getQuantity();

                discount += (itemTotal * percentage) / 100;
            }
        }
        return discount;
    }

    // ---------- Getters ----------

    public String getId() {
        return id;
    }

}

