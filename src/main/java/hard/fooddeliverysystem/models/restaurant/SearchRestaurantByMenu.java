package hard.fooddeliverysystem.models.restaurant;

import java.util.List;

public class SearchRestaurantByMenu implements RestaurantSearchStrategy {

    private final String menuItemName;

    public SearchRestaurantByMenu(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> restaurantList) {
        return null;
    }
}
