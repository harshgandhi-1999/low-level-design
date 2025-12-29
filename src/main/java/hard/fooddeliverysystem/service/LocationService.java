package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.Address;
import hard.fooddeliverysystem.models.restaurant.Restaurant;

import java.util.List;

public interface LocationService {

    List<Restaurant> findNearbyRestaurants(
            List<Restaurant> restaurants,
            Address userLocation,
            double maxDistanceInKm
    );
}
