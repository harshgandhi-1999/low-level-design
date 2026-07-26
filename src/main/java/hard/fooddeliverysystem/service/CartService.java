package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.cart.Cart;
import hard.fooddeliverysystem.models.cart.CartItem;

import java.util.HashMap;

public class CartService {

    private static volatile CartService INSTANCE = null;

    private final HashMap<String, Cart> userCartMap;

    private CartService() {
        this.userCartMap = new HashMap<>();
    }

    public static CartService getInstance() {
        if (INSTANCE == null) {
            synchronized (CartService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CartService();
                }
            }
        }

        return INSTANCE;
    }


    public void addToCart(String userId, CartItem cartItem) {
        if (userCartMap.get(userId) == null) {
            userCartMap.put(userId, new Cart());
        }
        userCartMap.get(userId).addItem(cartItem);
        ;
    }

    public void clearCart(String userId) {
        userCartMap.getOrDefault(userId, new Cart()).clearCart();
    }


    public Cart getCart(String userId) {
        return userCartMap.getOrDefault(userId, new Cart());
    }

}
