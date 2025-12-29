package hard.fooddeliverysystem.models.restaurant;

import hard.fooddeliverysystem.models.Address;
import hard.fooddeliverysystem.service.LocationService;

import java.util.List;

public class SearchRestaurantByProximity implements RestaurantSearchStrategy{

    private final LocationService locationService;
    private final Address userLocation;

    private final double maxDistance;

    public SearchRestaurantByProximity(LocationService locationService, Address userLocation, double maxDistance) {
        this.locationService = locationService;
        this.userLocation = userLocation;
        this.maxDistance = maxDistance;
    }

    @Override
    public List<Restaurant> filter(List<Restaurant> restaurantList) {
        return locationService.findNearbyRestaurants(restaurantList, userLocation, maxDistance);
    }
}
