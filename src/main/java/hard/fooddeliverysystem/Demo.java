package hard.fooddeliverysystem;

import hard.fooddeliverysystem.models.Address;
import hard.fooddeliverysystem.models.User;
import hard.fooddeliverysystem.models.cart.Cart;
import hard.fooddeliverysystem.models.cart.CartItem;
import hard.fooddeliverysystem.models.cart.FoodItem;
import hard.fooddeliverysystem.models.order.Order;
import hard.fooddeliverysystem.models.payment.CreditCardPaymentStrategy;
import hard.fooddeliverysystem.models.payment.PaymentStrategy;
import hard.fooddeliverysystem.models.restaurant.Menu;
import hard.fooddeliverysystem.models.restaurant.Restaurant;
import hard.fooddeliverysystem.service.CartService;
import hard.fooddeliverysystem.service.FoodOrderService;
import hard.fooddeliverysystem.service.RestaurantService;

public class Demo {

    public static void main(String[] args) {
        // Functional Requirements

        // 1. User signup/login
        // 2. Search restaurants based on location
        // 3. Search restaurants based on food item user has searched (location already selected)
        // 4. List all the food items for a particular restaurant
        // 5. Add items to cart
        // 6. Place order
        // 7. Make payment

        // Create a user
        User user1 = new User("abc@gmail.com", "abc");
        Address address = new Address("123", "abcd", "city1", 1234, 5678);

        Menu menu = new Menu();
        FoodItem f1 = new FoodItem("f1", "burger", "burger", "abcd", 50);
        FoodItem f2 = new FoodItem("f2", "sandwich", "sandwich", "abcd", 80);
        FoodItem f3 = new FoodItem("f3", "pasta", "pasta", "abcd", 100);
        FoodItem f4 = new FoodItem("f4", "noodles", "noodles", "abcd", 70);

        menu.addFoodItem(f1);
        menu.addFoodItem(f2);
        menu.addFoodItem(f3);
        menu.addFoodItem(f4);
        Restaurant r1 = new Restaurant("r1", "r1 restaurant", "r1 description", address, menu);

        RestaurantService restaurantService = RestaurantService.getInstance();
        restaurantService.addRestaurant(r1);

//        for (Restaurant restaurant : restaurantService.getAllRestaurants()) {
//            System.out.println(restaurant.toString());
//        }

        CartItem cartItem1 = new CartItem(f1, 2, r1);
        CartItem cartItem2 = new CartItem(f2, 1, r1);
        CartService.getInstance().addToCart(user1.getId(), cartItem1);
        CartService.getInstance().addToCart(user1.getId(), cartItem2);

        FoodOrderService orderService = FoodOrderService.getInstance();
        // optional: configure pricing
        orderService.getPricingService().setTaxRatePercent(5.0);
        orderService.getPricingService().setBaseDeliveryFee(25);

        Cart cart = CartService.getInstance().getCart(user1.getId());
        PaymentStrategy creditCardPaymentStrategy = new CreditCardPaymentStrategy();
        Order order = orderService.createOrder(user1, r1, cart, creditCardPaymentStrategy);

        String bill = orderService.generateBill(user1, order.getId());
        System.out.println(bill);
    }
}
