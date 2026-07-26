package hard.fooddeliverysystem.models.restaurant;

import java.util.List;

public class SearchRestaurantByName implements RestaurantSearchStrategy {

    private final String restaurantName;

    public SearchRestaurantByName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .filter(r -> r.getName()
                        .toLowerCase()
                        .contains(restaurantName.toLowerCase()))
                .toList();
    }
}
