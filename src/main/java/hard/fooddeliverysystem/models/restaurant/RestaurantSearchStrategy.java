package hard.fooddeliverysystem.models.restaurant;

import java.util.List;

public interface RestaurantSearchStrategy {

    List<Restaurant> filter(List<Restaurant> restaurantList);
}
