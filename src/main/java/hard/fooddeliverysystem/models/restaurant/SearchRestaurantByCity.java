package hard.fooddeliverysystem.models.restaurant;

import java.util.List;

public class SearchRestaurantByCity implements RestaurantSearchStrategy{

    private final String city;

    public SearchRestaurantByCity(String city) {
        this.city = city;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(r -> r.getAddress().getCity().equalsIgnoreCase(city))
                .toList();
    }
}
