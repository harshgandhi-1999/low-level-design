package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.restaurant.Menu;
import hard.fooddeliverysystem.models.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {

    private static volatile RestaurantService INSTANCE = null;
    private final List<Restaurant> restaurants;

    private RestaurantService() {
        restaurants = new ArrayList<>();
    }

    public static RestaurantService getInstance() {
        if (INSTANCE == null) {
            synchronized (RestaurantService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RestaurantService();
                }
            }
        }

        return INSTANCE;
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return this.restaurants;
    }

    public Menu getRestaurantMenu(Restaurant restaurant) {
        return restaurant.getMenu();
    }
}
