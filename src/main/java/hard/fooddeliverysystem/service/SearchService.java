package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.restaurant.Restaurant;
import hard.fooddeliverysystem.models.restaurant.RestaurantSearchStrategy;

import java.util.List;

public class SearchService {

    private static volatile SearchService INSTANCE = null;

    private SearchService() {
    }

    public static SearchService getInstance() {
        if (INSTANCE == null) {
            synchronized (SearchService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SearchService();
                }
            }
        }

        return INSTANCE;
    }

    // search restaurants
    public List<Restaurant> search(RestaurantSearchStrategy restaurantSearchStrategy) {
        return restaurantSearchStrategy.filter(RestaurantService.getInstance().getAllRestaurants());
    }
}
