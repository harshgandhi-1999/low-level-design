package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.Address;
import hard.fooddeliverysystem.models.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class GeoLocationService implements LocationService{

    // Geo indexing or Quad trees can be used for efficient searching for all nearby locations


    @Override
    public List<Restaurant> findNearbyRestaurants(List<Restaurant> restaurants, Address userLocation, double maxDistanceInKm) {
        List<Restaurant> result = new ArrayList<>();

        for (Restaurant r : restaurants) {
//            double distance = DistanceCalculator.distanceInKm(
//                    latitude, longitude,
//                    r.getAddress().getLatitude(), r.getAddress().getLongitude());

            double distance = 1.0;
            if (distance <= maxDistanceInKm) {
                result.add(r);
            }
        }
        return result;
    }
}
